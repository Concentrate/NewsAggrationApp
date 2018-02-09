package com.interestcontent.liudeyu.base.dataManager;

import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.interestcontent.liudeyu.base.baseBeans.FeedBaseBean;
import com.interestcontent.liudeyu.base.baseBeans.BaseRequest;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.contents.news.beans.NewsTechnoBean;
import com.interestcontent.liudeyu.contents.news.beans.NewsTechoRequest;
import com.interestcontent.liudeyu.contents.weibo.contents.comment.WeiboCommentRequet;
import com.interestcontent.liudeyu.contents.weibo.data.bean.WeiboBean;
import com.interestcontent.liudeyu.contents.weibo.data.bean.WeiboCommontBean;
import com.interestcontent.liudeyu.contents.weibo.data.bean.WeiboRequest;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudeyu on 2018/1/22.
 */

public class FeedDataManager {

    private static int WB_REQUEST_EVERY_PAGE_NUM = 15;
    private static int WB_MEMORY_STORGE_SAVE_TIME = 60 * 60 * 1000;
    private SparseArray<List<? extends FeedBaseBean>> feedRamCacheData = new SparseArray<>();
    private ACache mACache;
    private SparseArray<Integer> feedTabCurrentPageMap = new SparseArray<>();
    private volatile boolean isLoadingWeiboData;
    private SparseArray<BaseRequest> mfeedBaseRequestCacheData = new SparseArray<>(); // 由于feed流，有可能有过个array结构，缓存一个request结构是比较通用的

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
    public List<NewsTechnoBean> getNewsTechListAtFirst(int itemTabKey, String url) {
        if (feedRamCacheData.get(itemTabKey) != null && !feedRamCacheData.get(itemTabKey).isEmpty()) {
            return (List<NewsTechnoBean>) feedRamCacheData.get(itemTabKey);
        }
        return getNewsTechListByNet(itemTabKey, url, true);
    }

    public List<NewsTechnoBean> getNewsTechListByNet(int itemTabKey, String url, boolean reflash) {
        try {
            if (reflash) {
                feedTabCurrentPageMap.put(ItemTab.NEWS_TECHNOLEGE, 0);
            }
            NewsTechoRequest request = getFeedRequestByNet(itemTabKey, url, Constants.NEWS_TECH_PARAMETER.PAGE_COUNT,
                    null, NewsTechoRequest.class);
            if (request != null) {
                return saveToListCache(itemTabKey, request.getData(), reflash);
            }
        } catch (Exception e) {

        }
        return new ArrayList<NewsTechnoBean>();
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
        }
        if (feedRamCacheData.get(itemTabKey) == null) {
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
