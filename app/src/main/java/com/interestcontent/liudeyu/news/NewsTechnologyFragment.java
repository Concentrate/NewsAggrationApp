package com.interestcontent.liudeyu.news;

import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.news.beans.NewsTechnoBean;
import com.interestcontent.liudeyu.news.cells.SingeImageNewsCell;
import com.interestcontent.liudeyu.news.newsUtil.NewsUrlUtils;
import com.interestcontent.liudeyu.news.presenters.NewsPresenter;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class NewsTechnologyFragment extends AbsFeedFragment implements IMvpView<List<NewsTechnoBean>> {
    private NewsPresenter mNewsPresenter;

    @Override
    public void onRecyclerViewInitialized() {
        if (mNewsPresenter == null) {
            mNewsPresenter = new NewsPresenter();
            mNewsPresenter.attachView(this);
        }

        startRequestData(NewsPresenter.FEED_QUEST_TYPE.FIRST_FLUSH);
    }

    private void startRequestData(NewsPresenter.FEED_QUEST_TYPE type) {
        String url = NewsUrlUtils.get36krNewsTypeUrl("科技");
        mNewsPresenter.execute(url, ItemTab.NEWS_TECHNOLEGE, type);
    }

    @Override
    public void onPullRefresh() {
        mNewsPresenter.execute(NewsUrlUtils.get36krNewsTypeUrl("科技"), ItemTab.NEWS_TECHNOLEGE,
                NewsPresenter.FEED_QUEST_TYPE.REFLASH);
    }

    @Override
    public void onLoadMore() {
        mNewsPresenter.execute(NewsUrlUtils.get36krNewsTypeUrl("科技"), ItemTab.NEWS_TECHNOLEGE,
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
    }

    @Override
    public void onQueryError(Exception e) {
        hideLoadMore();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNewsPresenter.detachView();
    }
}
