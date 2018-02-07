package com.interestcontent.liudeyu.base.dataManager;

import android.util.SparseArray;

import com.google.gson.Gson;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.contents.news.beans.NewsTechnoBean;
import com.interestcontent.liudeyu.contents.news.beans.NewsTechoRequest;
import com.interestcontent.liudeyu.contents.weibo.contents.comment.WeiboCommentRequet;
import com.interestcontent.liudeyu.contents.weibo.data.bean.FeedBaseBean;
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

    public List<WeiboBean> getWeiboListAtFirst(int itemTabKey, String url) throws Exception {
        if (feedRamCacheData.get(itemTabKey) != null && !feedRamCacheData.get(itemTabKey).isEmpty()) {
            return (List<WeiboBean>) feedRamCacheData.get(itemTabKey);
        }
        return getWeiboBeanListByNet(itemTabKey, url, false);
    }

    public List<WeiboBean> getWeiboBeanListByNet(int itemTabKey, String url, boolean isReflash) {
        try {
            WeiboRequest request = getWeiboFeedByNet(itemTabKey, url, Constants.WB_REQUEST_PARAMETER.PAGE, provideWeiboBeanBasicRequestParameter(), WeiboRequest.class);
            if (request != null) {
                return saveToCache(itemTabKey, request.getWeiboLists(), isReflash);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<WeiboBean>();
    }


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
            NewsTechoRequest request = getWeiboFeedByNet(itemTabKey, url, Constants.NEWS_TECH_PARAMETER.PAGE_COUNT,
                    null, NewsTechoRequest.class);
            if (request != null) {
                return saveToCache(itemTabKey, request.getData(), reflash);
            }
        } catch (Exception e) {

        }
        return new ArrayList<NewsTechnoBean>();
    }


    public List<WeiboCommontBean> getWeiboCommentListByNet(int itemTabKey, String url, Map<String, String> map, boolean isFirstTime) {
        if (isFirstTime) {
            feedTabCurrentPageMap.put(itemTabKey, 0);
        }
        try {
            WeiboCommentRequet requet = getWeiboFeedByNet(itemTabKey, url, Constants.WB_REQUEST_PARAMETER.PAGE, map, WeiboCommentRequet.class);
            return saveToCache(itemTabKey, requet.getWeiboCommontBeans(), isFirstTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<WeiboCommontBean>();
    }

    /**
     * @param pageRequestTag 这个是请求后端的page参数
     */
    private synchronized <T> T getWeiboFeedByNet(int itemTabKey, String url, String pageRequestTag, Map<String, String> paras, Class<T> tClass) throws Exception {
        int requestPage = 1;
        if (feedTabCurrentPageMap.get(itemTabKey) != null) {
            requestPage = feedTabCurrentPageMap.get(itemTabKey) + 1;
        }
        feedTabCurrentPageMap.put(itemTabKey, requestPage);
        Map<String, String> map = paras;
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(pageRequestTag, requestPage + "");
        String response = OkHttpUtils.get().url(url).params(map).build().execute().body().string();
        Gson gson = new Gson();
        T request = gson.fromJson(response, tClass);
        return request;
    }

    private synchronized <T extends FeedBaseBean> List<T> saveToCache(int itemTabKey, List<T> weiboLists, boolean isReflash) {
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

    public List<WeiboBean> reflashWeiboListByNet(int itemTabKey, String url) throws Exception {
        feedTabCurrentPageMap.put(itemTabKey, 0);
        return getWeiboBeanListByNet(itemTabKey, url, true);
    }

    private static class FeedDataManagerHolder {
        static FeedDataManager sFeedDataManager = new FeedDataManager();
    }


    /**
     * 返回唯一存储标记,凭itemTab key 和page
     */
    private String getUniqueStoreTag(int itemTabKey, int page) {
        return new StringBuilder().append(itemTabKey).append("**$$$###").append(page).toString();
    }


}
