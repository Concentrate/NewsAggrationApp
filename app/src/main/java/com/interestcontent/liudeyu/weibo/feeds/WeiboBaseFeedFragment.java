package com.interestcontent.liudeyu.weibo.feeds;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.commonlib.components.LifeCycleMonitor;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.AbsTopTabFragment;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;
import com.interestcontent.liudeyu.weibo.feeds.presents.WeiboFeedPresenter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2017/12/26.
 */

public abstract class WeiboBaseFeedFragment extends AbsFeedFragment implements IMvpView<List<WeiboBean>>, LifeCycleMonitor {


    private RVBaseCell mCell;

    private String loadUrl;
    private int itemTabKey;

    private WeiboFeedPresenter mFeedPresenter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fragment fragment = getParentFragment();
        if (fragment instanceof AbsTopTabFragment) {
            ((AbsTopTabFragment) fragment).registerLifeCycleMonitor(this);
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration itemDecoration=new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getActivity().getResources().getDrawable(R.drawable.weibo_cell_divider_line));
        mRecyclerView.addItemDecoration(itemDecoration);
    }

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
    public void onQueryResult(List<WeiboBean> result) {
        if (result == null || result.isEmpty()) {
            return;
        }
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


    protected abstract String providedRequestDataUrl();

    @Override
    public void onPullRefresh() {
        startRequestWeiboFeed(false, WeiboFeedPresenter.FEED_QUEST_TYPE.REFLASH);
    }

    @Override
    public void onLoadMore() {
        startRequestWeiboFeed(true, WeiboFeedPresenter.FEED_QUEST_TYPE.NORMAL_BY_NET);
    }

    @Override
    public void onRecyclerViewInitialized() {
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(MyApplication.sApplication)
                .color(MyApplication.sApplication.getResources().getColor(R.color.md_white_1000))
                .size(SizeUtils.dp2px(1.0f))
                .build());
        startRequestWeiboFeed(true, WeiboFeedPresenter.FEED_QUEST_TYPE.FIRST_FLUSH);

    }


    protected abstract int provideItemTabKey();

    protected void startRequestWeiboFeed(boolean showLoadMore, WeiboFeedPresenter.FEED_QUEST_TYPE type) {
        if (mFeedPresenter == null) {
            mFeedPresenter = new WeiboFeedPresenter();
        }
        mFeedPresenter.attachView(this);
        String url = providedRequestDataUrl();
        int itemTabkey = provideItemTabKey();
        mFeedPresenter.execute(url, itemTabkey, type);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mFeedPresenter != null) {
            mFeedPresenter.detachView();
        }
        Fragment fragment = getParentFragment();
        if (fragment instanceof AbsTopTabFragment) {
            ((AbsTopTabFragment) fragment).unregisterLifeCycleMonitor(this);
        }
    }

    @Override
    public boolean onTopBackPressed() {
        return false;
    }

    @Override
    public void onTopFragmentUserVisibleHint(boolean visible) {

    }
}
