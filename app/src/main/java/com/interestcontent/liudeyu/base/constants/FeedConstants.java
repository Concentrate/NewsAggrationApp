package com.interestcontent.liudeyu.base.constants;

/**
 * Created by liudeyu on 2018/1/2.
 */

public class FeedConstants {
    public static final int FEED_NORMAL_WEIBO_TYPE = 1;
    // news
    public static final int NEWS_SINGLE_IMAGE_TYPE = 2;
    public static final int NEWS_MULTIPLE_IMAGE_TYPE = 3;


    // opinion
    public static final int OPINION_ZHIHU_CELL_TYPE = 4;
    public static final int BANNER_CELL_TYPE=5;
    public static final int OPINION_ZHIHU_THEME_CELL_TYPE=6;



//    video
    public static final int VIDEO_CELL_TYPE=7;


    public enum FEED_REQUEST_EMUM {
        FIRST_FLUSH, NORMAL_BY_NET, REFLASH
    }
}
