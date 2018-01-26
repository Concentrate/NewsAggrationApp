package com.interestcontent.liudeyu.weibo.feeds;

import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.tabs.ItemTab;

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

}
