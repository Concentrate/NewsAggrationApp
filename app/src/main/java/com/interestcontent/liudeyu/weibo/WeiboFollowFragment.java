package com.interestcontent.liudeyu.weibo;

import com.blankj.utilcode.util.ToastUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;
import com.interestcontent.liudeyu.weibo.feeds.WeiboCell;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.base.RVBaseCell;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2017/12/26.
 */

public class WeiboFollowFragment extends WeiboBaseTabFragment {


    private RVBaseCell mCell;



    @Override
    protected List<Cell> getCells(@NotNull List list) {
        List<Cell> cellList = new ArrayList<>();
        if (mCell == null) {
            mCell = new WeiboCell(list, getActivity());
        }
        mCell.mData = list;
        for (int i = 0; i < list.size(); i++) {
            cellList.add(mCell);
        }
        return cellList;
    }


    @Override
    protected String providedRequestDataUrl() {
        return Constants.HOME_WEIBO_FOLLOW;
    }

    @Override
    protected int provideItemTabKey() {
        return ItemTab.WEIBO_SUB_FOLLOW;
    }

    @Override
    public void onQueryResult(List<WeiboBean> result) {
        mBaseAdapter.setData(getCells(result));
        hideLoadMore();
        setRefreshing(false);
    }

    @Override
    public void onQueryError(Exception e) {
        ToastUtils.setBgResource(R.color.md_deep_orange_300);
        ToastUtils.showShort("网络错误");
        hideLoadMore();
        setRefreshing(false);

    }
}
