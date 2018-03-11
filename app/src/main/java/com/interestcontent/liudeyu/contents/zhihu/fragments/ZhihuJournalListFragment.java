package com.interestcontent.liudeyu.contents.zhihu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.contents.zhihu.bean.ZhihuJournayListRequest;
import com.interestcontent.liudeyu.contents.zhihu.cells.BannerCell;
import com.interestcontent.liudeyu.contents.zhihu.cells.ZhihuItemCell;
import com.interestcontent.liudeyu.contents.zhihu.presenters.ZhihuJournalListPresenter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/2/8.
 */

public class ZhihuJournalListFragment extends AbsFeedFragment implements IMvpView<ZhihuJournayListRequest> {

    private ZhihuJournalListPresenter mPresenter = new ZhihuJournalListPresenter();
    private boolean isLoadMore;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
    }

    @Override
    public void onRecyclerViewInitialized() {
        HorizontalDividerItemDecoration.Builder builder = new HorizontalDividerItemDecoration.Builder(getContext());
        HorizontalDividerItemDecoration itemDecoration = builder.margin(SizeUtils.dp2px(10))
                .size(SizeUtils.dp2px(1)).colorResId(R.color.md_grey_100).build();
        mRecyclerView.addItemDecoration(itemDecoration);
        startRequestData(false);
    }

    private void startRequestData(boolean isReflash) {
        mPresenter.execute(Constants.ZHIHU_JOURNAL_LIST_API, ItemTab.OPINION_ZHIHU_NEW_LEASTEST, isReflash ? FeedConstants.FEED_REQUEST_EMUM.REFLASH : FeedConstants.FEED_REQUEST_EMUM.FIRST_FLUSH);
        isLoadMore = false;
        mBaseAdapter.showLoading();
    }

    @Override
    public void onPullRefresh() {
        startRequestData(true);

    }

    @Override
    public void onLoadMore() {
        mPresenter.execute(Constants.ZHIHU_JOURNAL_LIST_BEOFRE, ItemTab.OPINION_ZHIHU_NEW_LEASTEST, FeedConstants.FEED_REQUEST_EMUM.NORMAL_BY_NET);
        isLoadMore = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    protected List<Cell> getCells(List list) {
        return null;
    }

    @Override
    public void onQueryResult(ZhihuJournayListRequest result) {
        hideLoadMore();
        setRefreshing(false);
        if (result == null) {
            return;
        }
        if (isLoadMore) {
            List<Cell> cellList = new ArrayList<>();
            for (int i = 0; i < result.getStories().size(); i++) {
                ZhihuItemCell itemCell=new ZhihuItemCell(result.getStories().get(i), this);
                itemCell.setDate(result.getDate());
                cellList.add(itemCell);
            }
            mBaseAdapter.addAll(cellList);
            return;

        }
        List<Cell> cellList = new ArrayList<>();
        BannerCell bannerCell = new BannerCell(result.getTop_stories(), this);
        cellList.add(bannerCell);
        for (int i = 0; i < result.getStories().size(); i++) {
            ZhihuItemCell itemCell=new ZhihuItemCell(result.getStories().get(i), this);
            itemCell.setDate(result.getDate());
            cellList.add(itemCell);
        }
        mBaseAdapter.setData(cellList);
    }

    @Override
    public void onQueryError(Exception e) {
        hideLoadMore();
        setRefreshing(false);
        mBaseAdapter.showError();
    }
}
