package com.interestcontent.liudeyu.weibo.contents;

import com.interestcontent.liudeyu.weibo.util.WeiboUrlsUtils;

/**
 * Created by liudeyu on 2018/1/26.
 */

public class MyWeiboMessageFragment extends WebContentBrowseFragment{
    @Override
    protected String provideUrl() {
        return WeiboUrlsUtils.getWeiboMessageUrl();
    }

}
