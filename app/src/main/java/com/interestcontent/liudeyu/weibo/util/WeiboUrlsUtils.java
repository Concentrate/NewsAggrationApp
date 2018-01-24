package com.interestcontent.liudeyu.weibo.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.utils.Logger;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/1/16.
 */

public class WeiboUrlsUtils {


    public static final String MIDDLE = "bmiddle";
    public static final String ORIGIN = "large";
    public static final String SMALL = "thumbnail";
    public static String TAG = WeiboUrlsUtils.class.getSimpleName();

    public static int getLimitPreivewSize(List<WeiboBean.PicUrlsBean> picUrlsBeans) {
        int limitPreivewSize = picUrlsBeans.size();
        return limitPreivewSize > 6 ? 6 : limitPreivewSize;
    }

    @NonNull
    public static List<String> getOriginPicUrls(List<WeiboBean.PicUrlsBean> picUrlsBeans, String originPicDomen, int limitPreivewSize
            , String imageScaleTag) {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < limitPreivewSize; i++) {
            String picRequestUrl = "";
            if (!TextUtils.isEmpty(originPicDomen)) {
                try {
//                            通过观察链接得到的原图链接，官方api没提供
                    URL url = new URL(picUrlsBeans.get(i).getThumbnail_pic());
                    String jpgName = url.getFile().substring(url.getFile().indexOf("thumbnail/") + "thumbnail/".length());
                    picRequestUrl = originPicDomen + "/" + imageScaleTag + "/" + jpgName;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            Logger.d(TAG, "request origin pic url is " + picRequestUrl);
            urls.add(picRequestUrl);
        }
        return urls;
    }

    @NonNull
    public static String getOriginPicHost(String tmpUrl) {
        String originPicDomen = "";
        if (!TextUtils.isEmpty(tmpUrl)) {
            try {
                URL url = new URL(tmpUrl);
                originPicDomen = url.getProtocol() + "://" + url.getHost();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return originPicDomen;
    }



    public static String getPersonalProfileUrl(String profileName) {
        return Constants.WEIBO_PERSONNAL_PROFILE + profileName+"?";
    }
}
