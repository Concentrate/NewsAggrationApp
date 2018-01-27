package com.interestcontent.liudeyu.weibo.util;

/**
 * Created by liudeyu on 2018/1/24.
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.interestcontent.liudeyu.weibo.contents.WeiboContentBrowseActivity;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WbAppInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyWeiboPageUtils {
    private static MyWeiboPageUtils weiboSdkUtils;
    private WbAppInfo mWeiboInfo = null;
    private AuthInfo authInfo;
    private Context context;
    private static final String USER_INFO_SCHEME = "sinaweibo://userinfo?";
    private static final String USER_INFO_H5 = "http://m.weibo.cn/u/";
    private static final String WEIBO_DETAIL_SCHEME = "sinaweibo://detail?";
    private static final String WEIBO_DETAIL_H5 = "http://m.weibo.cn/";
    private static final String WEIBO_ARTICLE_SCHEME = "sinaweibo://article?";
    private static final String WEIBO_ARTICLE_H5 = "http://media.weibo.cn/article?";
    private static final String WEIBO_SEND_CONTENT_SCHEME = "sinaweibo://sendweibo?";
    private static final String WEIBO_SEND_CONTENT_H5 = "http://m.weibo.cn/mblog?";
    private static final String WEIBO_COMMENT_SCHEME = "sinaweibo://comment?";
    private static final String WEIBO_COMMENT_H5 = "http://m.weibo.cn/comment?";
    private static final String WEIBO_SEARCH_URL_CHEME = "sinaweibo://searchall?";
    private static final String WEIBO_SEARCH_URL_H5 = "https://m.weibo.cn/p/100103type=1&";
    private static final String WEIBO_GOTO_HOME_SCHEME = "sinaweibo://gotohome?";
    private static final String WEIBO_GOTO_HOME_H5 = "http://m.weibo.cn/index/router?";
    private static final String WEIBO_GOTO_MYPROFILE_SCHEME = "sinaweibo://myprofile?";
    private static final String WEIBO_GOTO_MYPROFILE_H5 = "http://m.weibo.cn/index/router?";

    private MyWeiboPageUtils(Context context, AuthInfo authInfo) {
        this.mWeiboInfo = WeiboAppManager.getInstance(context).getWbAppInfo();
        this.authInfo = authInfo;
        this.context = context;
    }

    public static MyWeiboPageUtils getInstance(Context context, AuthInfo authInfo) {
        if (weiboSdkUtils == null) {
            weiboSdkUtils = new MyWeiboPageUtils(context, authInfo);
        }

        return weiboSdkUtils;
    }

    public void startUserMainPage(String uid) {
        this.startUserMainPage(uid, false);
    }

    public void startUserMainPage(String uid, boolean webOnly) {
        String url;
        if (!webOnly && this.mWeiboInfo != null && this.mWeiboInfo.isLegal()) {
            url = "sinaweibo://userinfo?";
            HashMap<String, String> property = new HashMap();
            property.put("uid", uid);
            Intent intent = this.createScheme(url, property);

            try {
                this.context.startActivity(intent);
            } catch (Exception var7) {
                ;
            }
        } else {
            url = "http://m.weibo.cn/u/" + uid + "?";
            url = this.schemeAddProperty(url, (HashMap) null);
            this.gotoWebActivity(url);
        }

    }

    public void startWeiboDetailPage(String mid, String uid) {
        this.startWeiboDetailPage(mid, uid, false);
    }

    public void startWeiboDetailPage(String mid, String uid, boolean webOnly) {
        String url;
        if (!webOnly && this.mWeiboInfo != null && this.mWeiboInfo.isLegal()) {
            url = "sinaweibo://detail?";
            HashMap<String, String> property = new HashMap();
            property.put("mblogid", mid);
            Intent intent = this.createScheme(url, property);

            try {
                this.context.startActivity(intent);
            } catch (Exception var8) {
                ;
            }
        } else {
            url = "http://m.weibo.cn/" + uid + "/" + mid + "?";
            url = this.schemeAddProperty(url, (HashMap) null);
            this.gotoWebActivity(url);
        }

    }

    public void startWeiboTopPage(String object_id) {
        this.startWeiboTopPage(object_id, false);
    }

    public void startWeiboTopPage(String pageId, boolean webOnly) {
        String url;
        HashMap property;
        if (!webOnly && this.mWeiboInfo != null && this.mWeiboInfo.isLegal()) {
            url = "sinaweibo://article?";
            property = new HashMap();
            property.put("object_id", "1022:" + pageId);
            Intent intent = this.createScheme(url, property);

            try {
                this.context.startActivity(intent);
            } catch (Exception var7) {
                ;
            }
        } else {
            url = "http://media.weibo.cn/article?";
            property = new HashMap();
            property.put("id", pageId);
            url = this.schemeAddProperty(url, property);
            this.gotoWebActivity(url);
        }

    }

    public void shareToWeibo(String content) {
        this.shareToWeibo(content, false);
    }

    public void shareToWeibo(String content, boolean webOnly) {
        String url;
        HashMap property;
        if (!webOnly && this.mWeiboInfo != null && this.mWeiboInfo.isLegal()) {
            url = "sinaweibo://sendweibo?";
            property = new HashMap();
            property.put("content", content);
            Intent intent = this.createScheme(url, property);

            try {
                this.context.startActivity(intent);
            } catch (Exception var7) {
                ;
            }
        } else {
            url = "http://m.weibo.cn/mblog?";
            property = new HashMap();
            property.put("content", content);
            url = this.schemeAddProperty(url, (HashMap) null);
            this.gotoWebActivity(url);
        }

    }

    public void commentWeibo(String srcid) {
        this.commentWeibo(srcid, false);
    }

    public void commentWeibo(String srcid, boolean webOnly) {
        String url;
        HashMap property;
        if (!webOnly && this.mWeiboInfo != null && this.mWeiboInfo.isLegal()) {
            url = "sinaweibo://comment?";
            property = new HashMap();
            property.put("srcid", srcid);
            Intent intent = this.createScheme(url, property);

            try {
                this.context.startActivity(intent);
            } catch (Exception var7) {
                ;
            }
        } else {
            url = "http://m.weibo.cn/comment?";
            property = new HashMap();
            property.put("id", srcid);
            url = this.schemeAddProperty(url, property);
            this.gotoWebActivity(url);
        }

    }

    public void openWeiboSearchPage(String searchKey) {
        this.openWeiboSearchPage(searchKey, false);
    }

    public void openWeiboSearchPage(String searchKey, boolean webOnly) {
        String url = null;
        HashMap property;
        if (!webOnly && this.mWeiboInfo != null && this.mWeiboInfo.isLegal()) {
            url = "sinaweibo://searchall?";
            property = new HashMap();
            property.put("q", searchKey);
            Intent intent = this.createScheme(url, property);

            try {
                this.context.startActivity(intent);
            } catch (Exception var7) {
                ;
            }
        } else {
            url = "https://m.weibo.cn/p/100103type=1&";
            property = new HashMap();
            property.put("q", searchKey);
            url = this.schemeAddProperty(url, property);
            this.gotoWebActivity(url);
        }

    }

    public void gotoMyHomePage() {
        this.gotoMyHomePage(false);
    }

    public void gotoMyHomePage(boolean webOnly) {
        String url;
        if (!webOnly && this.mWeiboInfo != null && this.mWeiboInfo.isLegal()) {
            url = "sinaweibo://gotohome?";
            Intent intent = this.createScheme(url, (HashMap) null);

            try {
                this.context.startActivity(intent);
            } catch (Exception var5) {
                ;
            }
        } else {
            url = "http://m.weibo.cn/index/router?";
            HashMap<String, String> property = new HashMap();
            property.put("cookie", "0_all");
            url = this.schemeAddProperty(url, (HashMap) null);
            this.gotoWebActivity(url);
        }

    }

    public void gotoMyProfile() {
        this.gotoMyProfile(false);
    }

    public void gotoMyProfile(boolean webOnly) {
        String url;
        if (!webOnly && this.mWeiboInfo != null && this.mWeiboInfo.isLegal()) {
            url = "sinaweibo://myprofile?";
            Intent intent = this.createScheme(url, (HashMap) null);

            try {
                this.context.startActivity(intent);
            } catch (Exception var5) {
                ;
            }
        } else {
            url = "http://m.weibo.cn/index/router?";
            HashMap<String, String> property = new HashMap();
            property.put("cookie", "3");
            url = this.schemeAddProperty(url, (HashMap) null);
            this.gotoWebActivity(url);
        }

    }

    public void startOtherPage(String url) {
        this.startOtherPage(url, (HashMap) null);
    }

    public void startOtherPage(String url, HashMap<String, String> property) {
        if (!TextUtils.isEmpty(url)) {
            url = this.schemeAddProperty(url, property);
            this.gotoWebActivity(url);
        }
    }

    private Intent createScheme(String action, HashMap<String, String> property) {
        action = this.schemeAddProperty(action, property);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(action));
        return intent;
    }

    private String schemeAddProperty(String url, HashMap<String, String> property) {
        url = url + "luicode=10000360&&lfid=OP_" + this.authInfo.getAppKey();
        Map.Entry entry;
        if (property != null) {
            for (Iterator iterator = property.entrySet().iterator(); iterator.hasNext(); url = url + "&" + entry.getKey().toString() + "=" + entry.getValue().toString()) {
                entry = (Map.Entry) iterator.next();
            }
        }

        return url;
    }

    private void gotoWebActivity(String url) {
        Intent intent = BrowseActivity.getIntent(url, false);
        intent.setClass(context, WeiboContentBrowseActivity.class);
        context.startActivity(intent);
    }
}
