package com.interestcontent.liudeyu.contents.zhihu.fragments;

import android.view.animation.RotateAnimation;

import com.blankj.utilcode.util.SizeUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.Constants;
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

    @Override
    public void onRecyclerViewInitialized() {
        mPresenter.attachView(this);
        HorizontalDividerItemDecoration itemDecoration = new HorizontalDividerItemDecoration.Builder(getContext())
                .colorResId(R.color.md_blue_grey_100).size(SizeUtils.dp2px(1)).build();
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAnimation(new RotateAnimation(0, 90));
        startRequestData(false);
    }

    private void startRequestData(boolean isReflash) {
        mBaseAdapter.showLoading();
        mPresenter.execute(Constants.ZHIHU_JOURNAL_LIST_API, ItemTab.OPINION_ZHIHU_NEW_LEASTEST, isReflash);
    }

    @Override
    public void onPullRefresh() {
        startRequestData(true);
    }

    @Override
    public void onLoadMore() {
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
        List<Cell> cellList = new ArrayList<>();
        BannerCell bannerCell = new BannerCell(result.getTop_stories(), this);
        cellList.add(bannerCell);
        for (int i = 0; i < result.getStories().size(); i++) {
            cellList.add(new ZhihuItemCell(result.getStories(), this));
        }
        mBaseAdapter.setData(cellList);
    }

    @Override
    public void onQueryError(Exception e) {
        hideLoadMore();
        setRefreshing(false);
    }
}
