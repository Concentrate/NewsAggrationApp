package com.interestcontent.liudeyu.contents.news.newsUtil;

import android.net.Uri;

import com.interestcontent.liudeyu.base.constants.Constants;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class NewsUrlUtils {
    public static String getNewsTypeUrl(String type, String url) {
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter(Constants.NEWS_TECH_PARAMETER.APIKEY, Constants.NEWS_TECH_API_KEY)
                .appendQueryParameter(Constants.NEWS_TECH_PARAMETER.THEME_PARA, type);
        return builder.build().toString();
    }
}
