package com.interestcontent.liudeyu.base.tabs;

import android.support.annotation.DrawableRes;
import android.view.View;

import java.io.Serializable;

/**
 * Created by liudeyu on 2018/1/12.
 */

public class ItemTab implements Serializable {
    public static final int TAB_WEIBO = 1;
    public static final int TAB_NEWS = 2;
    public static final int TAB_SETTINGS = 3;
    public static final int VIDEO_MAIN_TAB = 20;
    public static final int TAB_MAIN_OPINIONS = 18;


    //    weibo
    public static final int WEIBO_SUB_FOLLOW = 4;
    public static final int WEIBO_SUB_HOT = 5;
    public static final int WEIBO_SUB_MESSAGE = 6;
    public static final int WEIBO_SUB_MY_WEIBO_PAGE = 7;
    public static final int WEIBO_SUB_PERSON_SETTING = 8;
    public static final int WEIBO_COMMENT = 9;

    //    news
    public static final int NEWS_TECHNOLEGE = 10; //科技，选用36kr api
    public static final int NEWS_PHONE = 11;
    public static final int NEWS_PROGRAM = 12;
    public static final int NEWS_TENCENT = 13;
    public static final int NEWS_GOOGLE = 14;
    public static final int NEWS_LIFE = 15;
    public static final int NEWS_APPLE = 16;


    // 见解 ，知乎
    public static final int OPINION_ZHIHU_NEW_LEASTEST = 17;
    public static final int TAB_ZHIHU_THEME = 19;


    //video,现在到了21,注意要和上面没有重复
    public static final int VIDEO_RECOMEND_TAB = 21;



    private int mItemKey;
    private int mResourceId;
    private String mTitle;
    private View mIconView;

    public ItemTab(int itemKey, String title) {
        mItemKey = itemKey;
        mTitle = title;
    }

    public ItemTab(int itemKey, @DrawableRes int resourceId, String title) {
        mItemKey = itemKey;
        mResourceId = resourceId;
        mTitle = title;
    }

    public int getItemKey() {
        return mItemKey;
    }

    public void setItemKey(int itemKey) {
        mItemKey = itemKey;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public void setResourceId(int resourceId) {
        mResourceId = resourceId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public View getIconView() {
        return mIconView;
    }

    public void setIconView(View iconView) {
        mIconView = iconView;
    }
}
