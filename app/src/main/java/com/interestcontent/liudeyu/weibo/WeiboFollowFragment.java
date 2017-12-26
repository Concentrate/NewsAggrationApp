package com.interestcontent.liudeyu.weibo;

import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.cell.EmptyCell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2017/12/26.
 */

public class WeiboFollowFragment extends AbsFeedFragment {
    @Override
    public void onRecyclerViewInitialized() {

    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    protected List<Cell> getCells(List list) {
        List<Cell>emptylist=new ArrayList<>();
        emptylist.add(new EmptyCell("empty"));
        return emptylist;
    }
}
