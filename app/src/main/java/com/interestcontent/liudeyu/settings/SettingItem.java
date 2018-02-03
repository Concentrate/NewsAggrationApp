package com.interestcontent.liudeyu.settings;

import android.view.View;

/**
 * Created by liudeyu on 2018/2/3.
 */

abstract public class SettingItem implements View.OnClickListener {
    public int iconResId;
    public View iconRes;
    public String title;

    public SettingItem(int iconResId, String title) {
        this.iconResId = iconResId;
        this.title = title;
    }

    public SettingItem(View iconRes, String title) {
        this.iconRes = iconRes;
        this.title = title;
    }

}
