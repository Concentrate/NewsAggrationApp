package com.interestcontent.liudeyu.weibo.feeds;

import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.weibo.data.WeiboLoginManager;

/**
 * Created by liudeyu on 2018/1/26.
 */

public class WeiboHotFragment extends WeiboBaseFeedFragment {

    @Override
    protected String providedRequestDataUrl() {
        return Constants.WEIBO_COMMON_API;
    }

    @Override
    protected int provideItemTabKey() {
        return ItemTab.WEIBO_SUB_HOT;
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
