package com.interestcontent.liudeyu.weibo;

import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

/**
 * Created by liudeyu on 2017/12/30.
 */

/**
 * 检测是否登录微博，并且给予一定操作
 */
public abstract class WeiboBaseTabFragment extends AbsFeedFragment {


    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isResumed()) {
        }
    }

}
