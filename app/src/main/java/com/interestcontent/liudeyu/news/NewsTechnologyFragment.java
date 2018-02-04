package com.interestcontent.liudeyu.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.news.beans.NewsTechnoBean;
import com.interestcontent.liudeyu.news.cells.SingeImageNewsCell;
import com.interestcontent.liudeyu.news.newsUtil.NewsUrlUtils;
import com.interestcontent.liudeyu.news.presenters.NewsPresenter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class NewsTechnologyFragment extends AbsFeedFragment implements IMvpView<List<NewsTechnoBean>> {
    public static final String SCIENCE = "科技";
    private NewsPresenter mNewsPresenter;
    public static final String NEWS_TOPIC = "NEWS_TOPIC";
    public String mTopic;

    public static Bundle getTopicBundle(String topic) {
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_TOPIC, topic);
        return bundle;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTopic = bundle.getString(NEWS_TOPIC);
        }

    }

    @Override
    public void onRecyclerViewInitialized() {
        if (mNewsPresenter == null) {
            mNewsPresenter = new NewsPresenter();
            mNewsPresenter.attachView(this);
        }

        HorizontalDividerItemDecoration.Builder builder = new HorizontalDividerItemDecoration.Builder(getContext());
        HorizontalDividerItemDecoration itemDecoration = builder.margin(SizeUtils.dp2px(20)).colorResId(R.color.md_blue_grey_200).build();
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setItemAnimator(new SlideInDownAnimator());
        startRequestData(NewsPresenter.FEED_QUEST_TYPE.FIRST_FLUSH);
        mBaseAdapter.showLoading();
    }

    protected String provideInterestTag() {
        return mTopic == null ? SCIENCE : mTopic;
    }

    private void startRequestData(NewsPresenter.FEED_QUEST_TYPE type) {
        String url = NewsUrlUtils.get36krNewsTypeUrl(provideInterestTag());
        mNewsPresenter.execute(url, ItemTab.NEWS_TECHNOLEGE, type);
    }

    @Override
    public void onPullRefresh() {
        mNewsPresenter.execute(NewsUrlUtils.get36krNewsTypeUrl(SCIENCE), ItemTab.NEWS_TECHNOLEGE,
                NewsPresenter.FEED_QUEST_TYPE.REFLASH);
    }

    @Override
    public void onLoadMore() {
        mNewsPresenter.execute(NewsUrlUtils.get36krNewsTypeUrl(SCIENCE), ItemTab.NEWS_TECHNOLEGE,
                NewsPresenter.FEED_QUEST_TYPE.NORMAL_BY_NET);
    }

    @Override
    protected List<Cell> getCells(List list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        List<Cell> cellList = new ArrayList<>();
        List<NewsTechnoBean> mList = list;
        for (NewsTechnoBean bean : mList) {
            SingeImageNewsCell cell = new SingeImageNewsCell(getActivity(), bean);
            cell.setFragment(this);
            cellList.add(cell);
        }
        return cellList;
    }


    @Override
    public void onQueryResult(List<NewsTechnoBean> result) {
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
