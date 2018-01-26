package com.interestcontent.liudeyu.weibo.profiles;

import com.interestcontent.liudeyu.weibo.component.AbsWebBrowseFragment;
import com.interestcontent.liudeyu.weibo.util.WeiboUrlsUtils;

/**
 * Created by liudeyu on 2018/1/26.
 */

public class MyWeiboSettingFragment extends AbsWebBrowseFragment {

    @Override
    protected String provideUrl() {
        return WeiboUrlsUtils.getMyProfileSettingUrl();
    }
}
