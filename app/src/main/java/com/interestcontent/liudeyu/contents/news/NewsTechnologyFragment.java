package com.interestcontent.liudeyu.contents.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.contents.news.beans.NewsTechBean;
import com.interestcontent.liudeyu.contents.news.cells.MutilepleImageNewsCell;
import com.interestcontent.liudeyu.contents.news.cells.SingeImageNewsCell;
import com.interestcontent.liudeyu.contents.news.newsUtil.NewsUrlUtils;
import com.interestcontent.liudeyu.contents.news.presenters.NewsPresenter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class NewsTechnologyFragment extends AbsFeedFragment implements IMvpView<List<NewsTechBean>> {
    public static final String SCIENCE = "科技";
    private NewsPresenter mNewsPresenter;
    public static final String ITEM_TAB = "ITEM_TAB".toLowerCase();
    private ItemTab mItemTab;

    public static Bundle getTopicBundle(ItemTab itemTab) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEM_TAB, itemTab);
        return bundle;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mItemTab = (ItemTab) bundle.getSerializable(ITEM_TAB);
        }
        mNewsPresenter = new NewsPresenter();
        mNewsPresenter.attachView(this);
    }

    @Override
    public void onRecyclerViewInitialized() {
        HorizontalDividerItemDecoration.Builder builder = new HorizontalDividerItemDecoration.Builder(getContext());
        HorizontalDividerItemDecoration itemDecoration = builder.margin(SizeUtils.dp2px(10))
                .size(SizeUtils.dp2px(1)).colorResId(R.color.md_grey_100).build();
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setItemAnimator(new SlideInDownAnimator());
        startRequestData(FeedConstants.FEED_REQUEST_EMUM.FIRST_FLUSH);
        mBaseAdapter.showLoading();
    }

    protected String provideInterestTag() {
        return mItemTab == null ? SCIENCE : mItemTab.getTitle();
    }

    private void startRequestData(FeedConstants.FEED_REQUEST_EMUM type) {
        int itemTabKey = mItemTab == null ? ItemTab.NEWS_TECHNOLEGE : mItemTab.getItemKey();
        String url = NewsUrlUtils.getNewsTypeUrl(provideInterestTag(), Constants.NEWS_LEIFENG_NET_BASE);
        mNewsPresenter.execute(url, itemTabKey, type);
    }

    @Override
    public void onPullRefresh() {
        startRequestData(FeedConstants.FEED_REQUEST_EMUM.REFLASH);
    }

    @Override
    public void onLoadMore() {
        startRequestData(FeedConstants.FEED_REQUEST_EMUM.NORMAL_BY_NET);
    }

    @Override
    protected List<Cell> getCells(List list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        List<Cell> cellList = new ArrayList<>();
        List<NewsTechBean> mList = list;
        for (NewsTechBean bean : mList) {
            Cell cell = null;
            if (bean.getImageUrls() != null && bean.getImageUrls().size() >= 3) {
                cell = new MutilepleImageNewsCell(this, bean);
            } else {
                cell = new SingeImageNewsCell(bean, this);
            }
            cellList.add(cell);
        }
        return cellList;
    }


    @Override
    public void onQueryResult(List<NewsTechBean> result) {
        mBaseAdapter.setData(getCells(result));
        hideLoadMore();
        setRefreshing(false);
    }

    @Override
    public void onQueryError(Exception e) {
        hideLoadMore();
        setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNewsPresenter.detachView();
    }
}
