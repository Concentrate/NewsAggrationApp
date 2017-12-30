package com.interestcontent.liudeyu.weibo;

import com.zhouwei.rvadapterlib.base.Cell;

import java.util.List;

/**
 * Created by liudeyu on 2017/12/26.
 */

public class WeiboFollowFragment extends WeiboBaseTabFragment {
    @Override
    public void onRecyclerViewInitialized() {
        mBaseAdapter.showLoading();
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    protected List<Cell> getCells(List list) {
        return null;
    }


}
