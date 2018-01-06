package com.interestcontent.liudeyu.weibo;

import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBeanTestRequest;
import com.interestcontent.liudeyu.weibo.feeds.WeiboCell;
import com.zhouwei.rvadapterlib.base.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2017/12/26.
 */

public class WeiboFollowFragment extends WeiboBaseTabFragment {

    @Override
    public void onRecyclerViewInitialized() {
        startRequestWeiboFeed();
    }

    @Override
    public void onPullRefresh() {
        requestPageData(1);
    }

    @Override
    public void onLoadMore() {
        requestPageData(mCurrentPage+1);
    }

    @Override
    protected List<Cell> getCells(List list) {
        List<Cell>cellList=new ArrayList<>();
        cellList.add(new WeiboCell(list,getActivity()));
        return cellList;
    }



    @Override
    protected String providedRequestDataUrl() {
        return Constants.HOME_WEIBO_FOLLOW;
    }

    @Override
    protected void getResponseData(List<WeiboBeanTestRequest.StatusesBean> statuses) {
        mBaseAdapter.addAll(getCells(statuses));
        hideLoadMore();
    }
}
