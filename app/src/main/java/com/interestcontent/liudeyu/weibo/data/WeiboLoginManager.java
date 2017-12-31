package com.interestcontent.liudeyu.weibo.data;

import android.app.Activity;

import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Created by liudeyu on 2017/12/30.
 */

public class WeiboLoginManager {

    private static WeiboLoginManager sWeiboLoginManager;
    private SsoHandler mSsoHandler;
    public boolean isLogin;

    private WeiboLoginManager() {
        initData();
    }

    private WbAuthListener mWbAuthListener = new MyWeiboAuthenLitener();

    public static WeiboLoginManager getInstance() {
        if (sWeiboLoginManager == null) {
            synchronized (WeiboLoginManager.class) {
                if (sWeiboLoginManager == null) {
                    sWeiboLoginManager = new WeiboLoginManager();
                }
            }
        }
        return sWeiboLoginManager;
    }


    public SsoHandler getSsoHandler() {
        return mSsoHandler;
    }

    public void startLoginAuthen(Activity activity) {
        if (mSsoHandler == null) {
            mSsoHandler = new SsoHandler(activity);
        }
        mSsoHandler.authorize(mWbAuthListener);
    }

    private void initData() {
        isLogin = SharePreferenceUtil.getBooleanPreference(MyApplication.sApplication,
                SpConstants.WEIBO_LOGIN, false);
    }

    private class MyWeiboAuthenLitener implements WbAuthListener {
        @Override
        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            if (oauth2AccessToken == null) {
                return;
            }
            if (oauth2AccessToken.isSessionValid()) {

            }
        }

        @Override
        public void cancel() {

        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {

        }
    }

}
