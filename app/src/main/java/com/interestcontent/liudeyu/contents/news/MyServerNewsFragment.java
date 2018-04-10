package com.interestcontent.liudeyu.contents.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.contents.news.beans.NewsMyserverDataBean;
import com.interestcontent.liudeyu.contents.news.cells.MyServerSingleNewsCell;
import com.interestcontent.liudeyu.contents.news.newsUtil.NewsUrlUtils;
import com.interestcontent.liudeyu.contents.news.presenters.NewsMyServerNewsPresenter;
import com.interestcontent.liudeyu.settings.utils.NewsTopicSelectUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

/**
 * Created by liudeyu on 2018/4/9.
 */

public class MyServerNewsFragment extends AbsFeedFragment implements IMvpView<List<NewsMyserverDataBean>> {
    private static final String TAG = "MyServerNewsFragment";
    private static final String ITEM_TAB = "ITEM_TAB";
    private static final String UPLOAD_PARA = "UPLOAD_PARA";
    private ItemTab mItemTab;
    private String mGetRequestPara;
    private NewsMyServerNewsPresenter mNewsMyServerNewsPresenter;


    public static Bundle getBundle(String para, ItemTab itemTab) {
        Bundle bundle = new Bundle();
        bundle.putString(UPLOAD_PARA, para);
        bundle.putSerializable(ITEM_TAB, itemTab);
        return bundle;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mItemTab = (ItemTab) bundle.getSerializable(ITEM_TAB);
            mGetRequestPara = bundle.getString(UPLOAD_PARA);
        } else {
            throw new IllegalStateException("arguments 不应该为null");
        }
        mNewsMyServerNewsPresenter = new NewsMyServerNewsPresenter();
        mNewsMyServerNewsPresenter.attachView(this);
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

    private void startRequestData(FeedConstants.FEED_REQUEST_EMUM type) {
        String url = NewsUrlUtils.getMyServerNewsCategoriesUrl(mGetRequestPara, NewsTopicSelectUtils.getNewsApiBestUrlForTopic(mItemTab.getTitle()));
        mNewsMyServerNewsPresenter.execute(url, mItemTab, type);
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
    protected List<Cell> getCells(@NonNull List list) {
        List<NewsMyserverDataBean> dataBeans = list;
        List<Cell> cellList = new ArrayList<>();
        for (NewsMyserverDataBean bean : dataBeans) {
            cellList.add(new MyServerSingleNewsCell(bean, this));
        }
        return cellList;
    }


    @Override
    public void onQueryResult(List<NewsMyserverDataBean> result) {
        if (result != null && !result.isEmpty()) {
            mBaseAdapter.setData(getCells(result));
        }
        hideLoadMore();
        setRefreshing(false);

    }

    @Override
    public void onQueryError(Exception e) {
        hideLoadMore();
        setRefreshing(false);
        Logger.d(TAG, e.getMessage());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mNewsMyServerNewsPresenter.detachView();
    }
}
