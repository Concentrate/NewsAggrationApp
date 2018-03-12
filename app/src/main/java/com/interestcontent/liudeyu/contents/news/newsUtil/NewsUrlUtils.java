package com.interestcontent.liudeyu.contents.news.newsUtil;

import android.net.Uri;

import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.settings.components.NewsSourceFilterManager;

import java.util.Map;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class NewsUrlUtils {


    public static String getNewsTypeUrl(String type, String url) {
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter(Constants.NEWS_TECH_PARAMETER.APIKEY, Constants.NEWS_TECH_API_KEY)
                .appendQueryParameter(Constants.NEWS_TECH_PARAMETER.THEME_PARA, type);
        if (url.equals(Constants.NEWS_360_DOMAIN) && NewsSourceFilterManager.getInstance().isNewsOriginWebFilter()) {
            builder.appendQueryParameter(Constants.NEWS_360_PARAMETER_SITE, NewsSourceFilterManager.getInstance()
                    .getNewsWebSourceFIlter());
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
