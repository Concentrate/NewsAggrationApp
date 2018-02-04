package com.interestcontent.liudeyu.weibo.data;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.example.commonlib.utils.Logger;
import com.google.gson.Gson;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.thread.TaskManager;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboUserBean;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by liudeyu on 2017/12/30.
 */

public class WeiboLoginManager {

    private static final String LOG_TAG = WeiboLoginManager.class.getSimpleName();
    private static final int RE_REQUEST_USER_INFO = 2 * 24 * 60 * 60 * 1000;
    private static WeiboLoginManager sWeiboLoginManager;
    private SsoHandler mSsoHandler;
    public boolean isLogin;
    private AuthInfo mAuthInfo;
    private WeiboUserBean mLoginUser;

    private WbAuthListener mWbAuthListener = new MyWeiboAuthenLitener();
    private Handler mGetUserInfo = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!(msg.obj instanceof Exception)) {
                switch (msg.what) {
                    case 1:
                        mLoginUser = (WeiboUserBean) msg.obj;
                        if (mLoginUser != null) {
                            Gson gson = new Gson();
                            String tmp = gson.toJson(mLoginUser);
                            SharePreferenceUtil.setStringPreference(MyApplication.sApplication, SpConstants.WEIBO_USER_INFO, tmp);
                        }
                        break;
                }

            }
        }
    };

    private WeiboLoginManager() {
        resetLoginState();
        initDataTasks();
    }


    private void initDataTasks() {
        if (isLogin && getUid() != null) {
            final String token = SharePreferenceUtil.getStringPreference(MyApplication.sApplication, SpConstants.WEIBO_AUTHEN_TOKEN, "");
            if (TextUtils.isEmpty(token)) {
                return;
            }
            TaskManager.inst().commit(mGetUserInfo, new Callable<WeiboUserBean>() {
                @Override
                public WeiboUserBean call() throws Exception {
                    long lastNetReq = SharePreferenceUtil.getLongPreference(MyApplication.sApplication, SpConstants.WEIBO_RE_USER_INFO_LAST_TIME,
                            0);
                    boolean isNeedReq = Math.abs(new Date().getTime() - lastNetReq) > RE_REQUEST_USER_INFO;
                    String tmp = SharePreferenceUtil.getStringPreference(MyApplication.sApplication, SpConstants.WEIBO_USER_INFO, "");
                    if (!TextUtils.isEmpty(tmp) && !isNeedReq) {
                        Gson gson = new Gson();
                        mLoginUser = gson.fromJson(tmp, WeiboUserBean.class);
                        return mLoginUser;
                    }
                    SharePreferenceUtil.setLongPreference(MyApplication.sApplication, SpConstants.WEIBO_RE_USER_INFO_LAST_TIME,
                            new Date().getTime());
                    Map<String, String> map = new HashMap();
                    map.put(Constants.WB_REQUEST_PARAMETER.ACCESS_TOKEN, token);
                    map.put(Constants.WB_REQUEST_PARAMETER.UID, getUid());
                    String a1 = OkHttpUtils.get().url(Constants.WEIBO_USER_INFO_API).params(map).build().execute().body().string();
                    Gson gson = new Gson();
                    return gson.fromJson(a1, WeiboUserBean.class);
                }
            }, 1);
        }
    }

    public WeiboUserBean getLoginUser() {
        return mLoginUser;
    }

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
        mSsoHandler.authorizeWeb(mWbAuthListener);
    }

    public void resetLoginState() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getStringPreference(MyApplication.sApplication, SpConstants.WEIBO_AUTHEN_TOKEN))) {
            Date date = new Date();
            if (date.getTime() < SharePreferenceUtil.getLongPreference(MyApplication.sApplication, SpConstants.WEIBO_TOKEN_EXPIRED_TIME, 0)) {
                isLogin = true;
            }
        }
    }

    public void loginOutWeibo() {
        SharePreferenceUtil.setStringPreference(MyApplication.sApplication, SpConstants.WEIBO_AUTHEN_TOKEN, "");
        isLogin = false;
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
                initDataTasks();
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
