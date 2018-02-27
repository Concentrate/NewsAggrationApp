package com.interestcontent.liudeyu.settings;

import android.support.annotation.ColorInt;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;

/**
 * Created by liudeyu on 2018/2/27.
 */

public class ThemeDataManager {
    private static final ThemeDataManager sThemeDataManager = new ThemeDataManager();

    private ThemeDataManager() {
    }

    public static ThemeDataManager getInstance() {
        return sThemeDataManager;
    }


    public @ColorInt
    int getThemeColorInt() {
        int def = MyApplication.sApplication.getResources().getColor(R.color.md_orange_400);
        return SharePreferenceUtil.getIntegerPreference(MyApplication.sApplication, SpConstants.THEME_SP_COLOR, def);
    }

    public boolean setThemeColor(@ColorInt int color) {
        return SharePreferenceUtil.setIntegerPreference(MyApplication.sApplication, SpConstants.THEME_SP_COLOR, color);
    }

}
