package com.interestcontent.liudeyu.contents.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.BuildConfig;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.base.constants.LoggerConstants;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.contents.news.beans.NewsApiBean;
import com.interestcontent.liudeyu.contents.news.cells.MutilepleImageNewsCell;
import com.interestcontent.liudeyu.contents.news.cells.SingeImageNewsCell;
import com.interestcontent.liudeyu.contents.news.newsUtil.NewsUrlUtils;
import com.interestcontent.liudeyu.contents.news.presenters.NewsPresenter;
import com.interestcontent.liudeyu.settings.utils.NewsBestUrlSourceFilterUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class NewsFeedBaseFragment extends AbsFeedFragment implements IMvpView<List<NewsApiBean>> {
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
        //这里mItemTab不应该为null
        int itemTabKey = mItemTab == null ? 99 : mItemTab.getItemKey();
        String url = NewsUrlUtils.getNewsTypeUrl(provideInterestTag(), NewsBestUrlSourceFilterUtil.getBestUrlForTopic(mItemTab.getTitle()));
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
        List<NewsApiBean> mList = list;
        for (NewsApiBean bean : mList) {
            Cell cell = null;
            if (bean.getImageUrls() != null && bean.getImageUrls().size() >= 3) {
                loggerImageUrls(bean.getImageUrls());
                cell = new MutilepleImageNewsCell(this, bean);
            } else {
                cell = new SingeImageNewsCell(bean, this);
            }
            cellList.add(cell);
        }
        return cellList;
    }

    private void loggerImageUrls(List<String> imageUrls) {
        if (BuildConfig.DEBUG) {
            for (String a1 : imageUrls) {
                Logger.d("ImageUrls", a1);
            }
        }
    }


    @Override
    public void onQueryResult(List<NewsApiBean> result) {
        if (result != null && !result.isEmpty()) {
            mBaseAdapter.setData(getCells(result));
        } else {
            ToastUtils.showShort("没有更多数据了");
        }
        hideLoadMore();
        setRefreshing(false);
    }

    @Override
    public void onQueryError(Exception e) {
        Logger.d(LoggerConstants.RELEASE_DATA_PROBLEM, e.getMessage());
        hideLoadMore();
        setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNewsPresenter.detachView();
    }
}
