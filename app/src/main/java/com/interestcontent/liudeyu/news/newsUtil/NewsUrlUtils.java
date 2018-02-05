package com.interestcontent.liudeyu.news.newsUtil;

import android.net.Uri;

import com.interestcontent.liudeyu.base.constants.Constants;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class NewsUrlUtils {
    public static String get36krNewsTypeUrl(String type) {
        String tmp = Constants.NES_TECH_REQUEST_DOMAIN;
        Uri.Builder builder = Uri.parse(Constants.NEWS_TECH_BASE).buildUpon();
        builder.appendQueryParameter(Constants.NEWS_TECH_PARAMETER.APIKEY, Constants.NEWS_TECH_API_KEY)
                .appendQueryParameter(Constants.NEWS_TECH_PARAMETER.THEME_PARA, type);
        return builder.build().toString();
    }
}
