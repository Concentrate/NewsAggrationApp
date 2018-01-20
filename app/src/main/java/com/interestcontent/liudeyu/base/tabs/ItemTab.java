package com.interestcontent.liudeyu.base.tabs;

import android.support.annotation.DrawableRes;
import android.view.View;

/**
 * Created by liudeyu on 2018/1/12.
 */

public class ItemTab {
    public static final int TAB_WEIBO = 1;
    public static final int TAB_NEWS = 2;
    public static final int TAB_SETTINGS = 3;

//    weibo
    public static final int WEIBO_SUB_FOLLOW=4;
    public static final int WEIBO_SUB_HOT=5;
    public static final int WEIBO_SUB_MESSAGE=6;
    public static final int WEIBO_SUB_PROFILE=7;


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