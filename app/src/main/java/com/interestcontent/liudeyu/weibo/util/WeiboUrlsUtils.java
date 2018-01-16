package com.interestcontent.liudeyu.weibo.util;

import android.support.annotation.NonNull;

/**
 * Created by liudeyu on 2018/1/16.
 */

public class WeiboUrlsUtils {

    // TODO: 2018/1/16 还没弄好，先不使用
    private static final String MIDDLE = "bmiddle";
    private static final String ORIGIN = "large";
    private static final String SMALL = "thumbnail";

    public static enum ImageUrlTag {
        BIG, MIDDLE, SMALL
    }

    /**
     * 转换微博url的链接清晰度
     */
    private static String transformWeiboImageUrlToOthers(@NonNull ImageUrlTag originImageType, ImageUrlTag toImageUrlType, @NonNull String imageUrl) {
        String oriTag, toTag;
        oriTag = mapTagAndString(originImageType);
        toTag = mapTagAndString(toImageUrlType);
        return "";
    }

    private static String mapTagAndString(@NonNull ImageUrlTag originImageType) {
        String oriTag = null;
        switch (originImageType) {
            case SMALL:
                oriTag = SMALL;
                break;
            case BIG:
                oriTag = ORIGIN;
                break;
            case MIDDLE:
                oriTag = MIDDLE;
                break;
        }
        return oriTag;
    }
}
