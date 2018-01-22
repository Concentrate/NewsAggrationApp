package com.interestcontent.liudeyu.base.dataManager;

import android.util.SparseArray;

import com.google.gson.Gson;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboRequest;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudeyu on 2018/1/22.
 */

public class FeedDataManager {

    private static int WB_REQUEST_EVERY_PAGE_NUM = 15;
    private static int WB_MEMORY_STORGE_SAVE_TIME = 60 * 60 * 1000;
    private SparseArray<List<WeiboBean>> wbRamCache = new SparseArray<>();
    private volatile int secondCounter;
    private ACache mACache;
    private SparseArray<Integer> weiboTabCurrentPageMap=new SparseArray<>();

    private synchronized void resetCounter() {
        secondCounter = 0;
    }

    private FeedDataManager() {
        mACache = ACache.get(MyApplication.sApplication);
    }


    public static FeedDataManager getInstance() {
        return FeedDataManagerHolder.sFeedDataManager;
    }

    private Map<String, String> provideBasicRequestParameter() {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.WB_REQUEST_PARAMETER.ACCESS_TOKEN, SharePreferenceUtil.getStringPreference(
                MyApplication.sApplication, SpConstants.WEIBO_AUTHEN_TOKEN));
        map.put(Constants.WB_REQUEST_PARAMETER.TRIM_USER, 0 + "");
        map.put(Constants.WB_REQUEST_PARAMETER.SINGLE_PAGE_COUNT,WB_REQUEST_EVERY_PAGE_NUM+"");
        return map;
    }
    public List<WeiboBean> getWeiboListAtFirstFlush(int itemTabKey, String url) throws  Exception{
        if (wbRamCache.get(itemTabKey) != null && !wbRamCache.get(itemTabKey).isEmpty()) {
            return wbRamCache.get(itemTabKey);
        }
       return  getWeiboListMoreByNet(itemTabKey,url,false);
    }

    public List<WeiboBean> getWeiboListMoreByNet(int itemTabKey,String url,boolean reflash) throws Exception{
        int requestPage=1;
        if(weiboTabCurrentPageMap.get(itemTabKey)!=null){
            requestPage=weiboTabCurrentPageMap.get(itemTabKey)+1;
        }
        weiboTabCurrentPageMap.put(itemTabKey,requestPage);
        Map<String,String>map = provideBasicRequestParameter();
        map.put(Constants.WB_REQUEST_PARAMETER.PAGE,requestPage+"");
        String response=OkHttpUtils.get().url(url).params(map).build().execute().body().string();
        Gson gson=new Gson();
        WeiboRequest request= gson.fromJson(response, WeiboRequest.class);
        return   saveToCache(itemTabKey,request.getWeiboLists(),reflash);
    }

    private List<WeiboBean> saveToCache(int itemTabKey, List<WeiboBean> weiboLists,boolean isReflash) {
        if(isReflash){
            wbRamCache.put(itemTabKey,weiboLists);
        }
        if(wbRamCache.get(itemTabKey)==null){
            wbRamCache.put(itemTabKey,weiboLists);
        }else{
            wbRamCache.get(itemTabKey).addAll(weiboLists);
        }
        return wbRamCache.get(itemTabKey);
    }

    public List<WeiboBean> reflashWeiboListByNet(int itemTabKey,String url) throws Exception{
        weiboTabCurrentPageMap.put(itemTabKey,0);
        return getWeiboListMoreByNet(itemTabKey,url,true);
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
