package com.interestcontent.liudeyu.news.newsUtil;

import com.interestcontent.liudeyu.base.constants.Constants;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class NewsUrlUtils {
    public static String get36krNewsTypeUrl( String type) {
        String tmp = Constants.NES_TECH_REQUEST_DOMAIN;
        String aUrl = tmp + "&" + Constants.NEWS_TECH_PARAMETER.THEME_PARA + "=" + type;
        return aUrl;
    }
}
