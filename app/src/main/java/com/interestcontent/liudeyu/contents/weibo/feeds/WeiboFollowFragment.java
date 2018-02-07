package com.interestcontent.liudeyu.contents.weibo.feeds;

import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.contents.weibo.data.WeiboLoginManager;

/**
 * Created by liudeyu on 2018/1/26.
 */

public class WeiboFollowFragment extends WeiboBaseFeedFragment {
    @Override
    protected String providedRequestDataUrl() {
        return Constants.HOME_WEIBO_FOLLOW;
    }

    @Override
    protected int provideItemTabKey() {
        return ItemTab.WEIBO_SUB_FOLLOW;
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
