package com.interestcontent.liudeyu.weibo.contents;

import android.os.Bundle;
import android.view.View;

import com.interestcontent.liudeyu.weibo.data.WeiboLoginManager;
import com.interestcontent.liudeyu.weibo.util.WeiboUrlsUtils;

/**
 * Created by liudeyu on 2018/1/24.
 */

public class MyWeiboProfileFragment extends WebContentBrowseFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected String provideUrl() {
        return WeiboUrlsUtils.getMyProfilePageUrl();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (!WeiboLoginManager.getInstance().isLogin && getActivity() != null) {
                WeiboLoginManager.getInstance().startLoginAuthen(getActivity());
            }
        }
    }

}
