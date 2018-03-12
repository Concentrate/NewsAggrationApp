package com.interestcontent.liudeyu.settings.components;

import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;

/**
 * Created by liudeyu on 2018/3/12.
 */

public class NewsSourceFilterManager {
    private static NewsSourceFilterManager sNewsSourceFilterManager;

    private final String[] SEARCH_SITE_FILTER = {
            "qq.com", "toutiao.com", "163.com", "sina.com", "qihoo.com"};


    private NewsSourceFilterManager() {

    }

    public static synchronized NewsSourceFilterManager getInstance() {
        if (sNewsSourceFilterManager == null) {
            sNewsSourceFilterManager = new NewsSourceFilterManager();
        }
        return sNewsSourceFilterManager;
    }


    public boolean isNewsOriginWebFilter() {
        return SharePreferenceUtil.getBooleanPreference(MyApplication.sApplication,
                SpConstants.NEWS_SOURCE_IS_FILTER, true);
    }

    public boolean setNewsOriginWebFilter(boolean b) {
        return SharePreferenceUtil.setBooleanPreference(MyApplication.sApplication,
                SpConstants.NEWS_SOURCE_IS_FILTER, b);
    }

    public String getNewsWebSourceFIlter() {
        return "qihoo.com";
    }
}
