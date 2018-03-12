package com.interestcontent.liudeyu.contents.news.newsUtil;

import android.net.Uri;

import com.interestcontent.liudeyu.base.constants.Constants;

import java.util.Map;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class NewsUrlUtils {

    private static final String[] SEARCH_SITE_FILTER = {
            "qq.com", "toutiao.com", "163.com", "sina.com", "qihoo.com"};

    public static String getNewsTypeUrl(String type, String url) {
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter(Constants.NEWS_TECH_PARAMETER.APIKEY, Constants.NEWS_TECH_API_KEY)
                .appendQueryParameter(Constants.NEWS_TECH_PARAMETER.THEME_PARA, type);
        if (url.equals(Constants.NEWS_360_DOMAIN)) {
            int t = (int) (Math.random() * SEARCH_SITE_FILTER.length);
            if (t >= SEARCH_SITE_FILTER.length) {
                t = SEARCH_SITE_FILTER.length - 1;
            }
            String filter = SEARCH_SITE_FILTER[4];
            builder.appendQueryParameter(Constants.NEWS_360_PARAMETER_SITE, filter);
        }
        return builder.build().toString();
    }


    public static String combineParaAndUrl(String url, Map<String, String> map) {

        Uri.Builder builder = Uri.parse(url).buildUpon();
        if (map == null) {
            return builder.build().toString();
        }
        for (String t1 : map.keySet()) {
            builder.appendQueryParameter(t1, map.get(t1));
        }
        return builder.build().toString();
    }
}
