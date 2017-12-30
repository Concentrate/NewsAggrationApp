package com.interestcontent.liudeyu.weibo;

import android.app.Activity;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Created by liudeyu on 2017/12/30.
 */

public class WeiboLoginManager {

    private static WeiboLoginManager sWeiboLoginManager;
    private WbAuthListener mWbAuthListener = new WbAuthListener() {
        @Override
        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            if (oauth2AccessToken == null) {
                return;
            }
            if (oauth2AccessToken.isSessionValid()) {
                // TODO: 2017/12/30 存储一些必要信息
            }
        }

        @Override
        public void cancel() {

        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {

        }
    };
    private SsoHandler mSsoHandler;

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


    private WeiboLoginManager() {
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
}
