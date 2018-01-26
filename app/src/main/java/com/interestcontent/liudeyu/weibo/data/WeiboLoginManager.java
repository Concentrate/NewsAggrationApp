package com.interestcontent.liudeyu.weibo.data;

import android.app.Activity;
import android.text.TextUtils;

import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.util.Date;

/**
 * Created by liudeyu on 2017/12/30.
 */

public class WeiboLoginManager {

    private static final String LOG_TAG = WeiboLoginManager.class.getSimpleName();
    private static WeiboLoginManager sWeiboLoginManager;
    private SsoHandler mSsoHandler;
    public boolean isLogin;
    private AuthInfo mAuthInfo;

    private WeiboLoginManager() {
        resetLoginState();
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

    public void resetLoginState() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getStringPreference(MyApplication.sApplication, SpConstants.WEIBO_AUTHEN_TOKEN))) {
            Date date = new Date();
            if (date.getTime() < SharePreferenceUtil.getLongPreference(MyApplication.sApplication, SpConstants.WEIBO_TOKEN_EXPIRED_TIME, 0)) {
                isLogin = true;
            }
        }
    }

    public AuthInfo getAuthInfo() {
        if (mAuthInfo == null) {
            mAuthInfo = new AuthInfo(MyApplication.sApplication, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        }
        return mAuthInfo;
    }

    public String getUid() {
        String string = SharePreferenceUtil.getStringPreference(MyApplication.sApplication, SpConstants.WEIBO_USER_ID);
        return TextUtils.isEmpty(string) ? "" : string;
    }

    private class MyWeiboAuthenLitener implements WbAuthListener {
        @Override
        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            if (oauth2AccessToken == null) {
                return;
            }
            if (oauth2AccessToken.isSessionValid()) {
                SharePreferenceUtil.setStringPreference(MyApplication.sApplication, SpConstants.WEIBO_AUTHEN_TOKEN,
                        oauth2AccessToken.getToken());
                SharePreferenceUtil.setStringPreference(MyApplication.sApplication, SpConstants.WEIBO_USER_ID,
                        oauth2AccessToken.getUid());
                SharePreferenceUtil.setLongPreference(MyApplication.sApplication, SpConstants.WEIBO_TOKEN_EXPIRED_TIME,
                        oauth2AccessToken.getExpiresTime());
                WeiboLoginManager.getInstance().resetLoginState();
                Logger.d(LOG_TAG, oauth2AccessToken.getToken());
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
