package com.interestcontent.liudeyu.data;

/**
 * Created by liudeyu on 2017/12/30.
 */

public class WeiboDataManager {

    private static WeiboDataManager sWeiboDataManager = new WeiboDataManager();

    public static WeiboDataManager getInstance() {
        return sWeiboDataManager;
    }

    private WeiboDataManager() {
    }


}
