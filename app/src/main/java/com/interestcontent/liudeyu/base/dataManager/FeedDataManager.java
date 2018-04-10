package com.interestcontent.liudeyu.base.dataManager;

import android.text.TextUtils;
import android.util.SparseArray;

import com.example.commonlib.utils.Logger;
import com.google.gson.Gson;
import com.interestcontent.liudeyu.base.baseBeans.BaseRequest;
import com.interestcontent.liudeyu.base.baseBeans.FeedBaseBean;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.contents.news.beans.NewsIDataApiBean;
import com.interestcontent.liudeyu.contents.news.beans.NewsIDataRequest;
import com.interestcontent.liudeyu.contents.news.beans.NewsMyServerRequest;
import com.interestcontent.liudeyu.contents.news.beans.NewsMyserverDataBean;
import com.interestcontent.liudeyu.contents.news.newsUtil.NewsUrlUtils;
import com.interestcontent.liudeyu.contents.weibo.contents.comment.WeiboCommentRequet;
import com.interestcontent.liudeyu.contents.weibo.data.bean.WeiboBean;
import com.interestcontent.liudeyu.contents.weibo.data.bean.WeiboCommontBean;
import com.interestcontent.liudeyu.contents.weibo.data.bean.WeiboRequest;
import com.zhy.http.okhttp.OkHttpUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudeyu on 2018/1/22.
 */

public class FeedDataManager {
    private static final String TAG = "FeedDataManager";
    private static int WB_REQUEST_EVERY_PAGE_NUM = 15;
    private static int WB_MEMORY_STORGE_SAVE_TIME = 60 * 60 * 1000;
    private SparseArray<List<? extends FeedBaseBean>> feedRamCacheData = new SparseArray<>();
    private ACache mACache;
    private SparseArray<Integer> feedTabCurrentPageMap = new SparseArray<>();
    private SparseArray<BaseRequest> mfeedBaseRequestCacheData = new SparseArray<>(); // 由于feed流，有可能有过个array结构，缓存一个request结构是比较通用的
    private SparseArray<Boolean> newsItemNoMoreDataSet = new SparseArray<>();

    private FeedDataManager() {
        mACache = ACache.get(MyApplication.sApplication);
    }


    public static FeedDataManager getInstance() {
        return FeedDataManagerHolder.sFeedDataManager;
    }

    private Map<String, String> provideWeiboBeanBasicRequestParameter() {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.WB_REQUEST_PARAMETER.ACCESS_TOKEN, SharePreferenceUtil.getStringPreference(
                MyApplication.sApplication, SpConstants.WEIBO_AUTHEN_TOKEN));
        map.put(Constants.WB_REQUEST_PARAMETER.TRIM_USER, 0 + "");
        map.put(Constants.WB_REQUEST_PARAMETER.SINGLE_PAGE_COUNT, WB_REQUEST_EVERY_PAGE_NUM + "");
        return map;
    }


    /**** weibo */
    public List<WeiboBean> getWeiboListAtFirst(int itemTabKey, String url) throws Exception {
        if (feedRamCacheData.get(itemTabKey) != null && !feedRamCacheData.get(itemTabKey).isEmpty()) {
            return (List<WeiboBean>) feedRamCacheData.get(itemTabKey);
        }
        return getWeiboBeanListByNet(itemTabKey, url, false);
    }

    public List<WeiboBean> getWeiboBeanListByNet(int itemTabKey, String url, boolean isReflash) {
        try {
            WeiboRequest request = getFeedRequestByNet(itemTabKey, url, Constants.WB_REQUEST_PARAMETER.PAGE, provideWeiboBeanBasicRequestParameter(), WeiboRequest.class);
            if (request != null) {
                return saveToListCache(itemTabKey, request.getWeiboLists(), isReflash);
            }
        } catch (Exception e) {
            Logger.d(TAG, e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<WeiboBean>();
    }

    public List<WeiboCommontBean> getWeiboCommentListByNet(int itemTabKey, String url, Map<String, String> map, boolean isFirstTime) {
        if (isFirstTime) {
            feedTabCurrentPageMap.put(itemTabKey, 0);
        }
        try {
            WeiboCommentRequet requet = getFeedRequestByNet(itemTabKey, url, Constants.WB_REQUEST_PARAMETER.PAGE, map, WeiboCommentRequet.class);
            return saveToListCache(itemTabKey, requet.getWeiboCommontBeans(), isFirstTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<WeiboCommontBean>();
    }

    public List<WeiboBean> reflashWeiboFeedListByNet(int itemTabKey, String url) throws Exception {
        feedTabCurrentPageMap.put(itemTabKey, 0);
        return getWeiboBeanListByNet(itemTabKey, url, true);
    }

    /****** news */

    public List<NewsMyserverDataBean> getMyServerDataNewsAtFirst(int itemTabKey, String url) {
        if (feedRamCacheData.get(itemTabKey) != null && !feedRamCacheData.get(itemTabKey).isEmpty()) {
            return (List<NewsMyserverDataBean>) feedRamCacheData.get(itemTabKey);
        }
        return getMyServerNewsByNet(itemTabKey, url, true);
    }

    public List<NewsMyserverDataBean> getMyServerNewsByNet(int itemTabKey, String url, boolean reflash) {
        if (reflash) {
            feedTabCurrentPageMap.put(itemTabKey, -1); // 服务器那边从0开始算，这里赋值-1，下面加了1
        }
        int page = feedTabCurrentPageMap.get(itemTabKey);
        try {
            NewsMyServerRequest request = getFeedRequestByNet(itemTabKey, url, Constants.NEWS_MYSERVER_PARAMETER.PAGE,
                    null, NewsMyServerRequest.class);
            return saveToListCache(itemTabKey, request.getData(), reflash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public List<NewsIDataApiBean> getIDataNewsListAtFirst(int itemTabKey, String url) {
        if (feedRamCacheData.get(itemTabKey) != null && !feedRamCacheData.get(itemTabKey).isEmpty()) {
            return (List<NewsIDataApiBean>) feedRamCacheData.get(itemTabKey);
        }
        return getIDataNewsListByNet(itemTabKey, url, true);
    }

    public List<NewsIDataApiBean> getIDataNewsListByNet(int itemTabKey, String url, boolean reflash) {
        try {
            // 这里用一些兼容逻辑，使得后台数据即使重复也能正常显示，这里逻辑有些乱了
            if (reflash) {
                feedTabCurrentPageMap.put(itemTabKey, 0);
                newsItemNoMoreDataSet.put(itemTabKey, false);
            }

            NewsIDataRequest request = null;
            if (newsItemNoMoreDataSet.get(itemTabKey) == null || !newsItemNoMoreDataSet.get(itemTabKey)) {
                request = getFeedRequestByNet(itemTabKey, url, Constants.NEWS_IDATAAPI_PARAMETER.PAGE_COUNT,
                        null, NewsIDataRequest.class);
            }
            if (request != null) {
                if (request.getPageToken() != null && request.isHasNext()) {
                    Integer nextPager = Integer.parseInt(request.getPageToken());
                    //因为下面又加了1，因为它每次返回了下一页的token值，下面逻辑又是自动加了1的
                    if (nextPager > 1) {
                        feedTabCurrentPageMap.put(itemTabKey, nextPager - 1);
                    }
                    return saveToListCache(itemTabKey, request.getData(), reflash);
                } else {
                    //这里说明没有数据了，为什么这样弄，也是因为后台请求返回的数据各种乱
                    newsItemNoMoreDataSet.put(itemTabKey, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<NewsIDataApiBean>();
    }


    /**
     * ，缓存或请求一个request结构体，这里就没有分页的分别了
     */
    public synchronized <T extends BaseRequest> T getRequest(boolean isReflash, int itemTabKey, String url, Class<T> tClass) throws Exception {
        T t = null;
        if (isReflash) {
            t = getFeedRequestByNet(itemTabKey, url, "", null, tClass);
            if (t == null) {
                throw new IllegalArgumentException("not data response");
            }
            mfeedBaseRequestCacheData.put(itemTabKey, t);
        } else {
            if (mfeedBaseRequestCacheData.get(itemTabKey) != null) {
                t = (T) mfeedBaseRequestCacheData.get(itemTabKey);
            } else {
                t = getFeedRequestByNet(itemTabKey, url, "", null, tClass);
                if (t == null) {
                    throw new IllegalArgumentException("not data response");
                }
                mfeedBaseRequestCacheData.put(itemTabKey, t);
            }
        }
        return (T) mfeedBaseRequestCacheData.get(itemTabKey);
    }


    /***** 通用*/

    /**
     * @param pageRequestTag 这个是请求后端的page参数
     */
    private synchronized <T> T getFeedRequestByNet(int itemTabKey, String url, String pageRequestTag, Map<String, String> paras, Class<T> tClass) throws Exception {
        int requestPage = 1;
        if (feedTabCurrentPageMap.get(itemTabKey) != null) {
            requestPage = feedTabCurrentPageMap.get(itemTabKey) + 1;
        }
        feedTabCurrentPageMap.put(itemTabKey, requestPage);
        Map<String, String> map = paras;
        if (map == null) {
            map = new HashMap<>();
        }
        if (!TextUtils.isEmpty(pageRequestTag)) {
            map.put(pageRequestTag, requestPage + "");
        }
        String response;
        if (!map.keySet().isEmpty()) {
            URL tmp = new URL(url);
            Logger.d(TAG, "request url is :" + NewsUrlUtils.combineParaAndUrl(url, map));
            response = OkHttpUtils.get().url(url).params(map).build().execute().body().string();
        } else {
            response = OkHttpUtils.get().url(url).build().execute().body().string();
        }
        Gson gson = new Gson();
        T request = gson.fromJson(response, tClass);
        return request;
    }

    private synchronized <T extends FeedBaseBean> List<T> saveToListCache(int itemTabKey, List<T> weiboLists, boolean isReflash) {
        if (isReflash) {
            feedRamCacheData.put(itemTabKey, weiboLists);
        } else if (feedRamCacheData.get(itemTabKey) == null) {
            feedRamCacheData.put(itemTabKey, weiboLists);
        } else {
            List<T> ablist = (List<T>) feedRamCacheData.get(itemTabKey);
            ablist.addAll(weiboLists);
        }
        return (List<T>) feedRamCacheData.get(itemTabKey);
    }


    private static class FeedDataManagerHolder {
        static FeedDataManager sFeedDataManager = new FeedDataManager();
    }


}
