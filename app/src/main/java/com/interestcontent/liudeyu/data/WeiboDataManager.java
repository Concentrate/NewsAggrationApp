package com.interestcontent.liudeyu.data;

import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;

/**
 * Created by liudeyu on 2017/12/30.
 */

public class WeiboDataManager {

    private static WeiboDataManager sWeiboDataManager = new WeiboDataManager();

    public static WeiboDataManager getInstance() {
        return sWeiboDataManager;
    }

    private WeiboDataManager() {
        initData();
    }

    private void initData() {
        isLogin = SharePreferenceUtil.getBooleanPreference(MyApplication.sApplication,
                SpConstants.WEIBO_LOGIN, false);
    }

    public boolean isLogin;
}
