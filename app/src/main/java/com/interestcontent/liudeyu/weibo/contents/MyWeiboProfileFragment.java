package com.interestcontent.liudeyu.weibo.contents;

import android.os.Bundle;
import android.view.View;

import com.interestcontent.liudeyu.weibo.component.BaseWebBrowseFragment;
import com.interestcontent.liudeyu.weibo.util.WeiboUrlsUtils;

/**
 * Created by liudeyu on 2018/1/24.
 */

public class MyWeiboProfileFragment extends BaseWebBrowseFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected String provideUrl() {
        return WeiboUrlsUtils.getMyProfilePageUrl();
    }
}
