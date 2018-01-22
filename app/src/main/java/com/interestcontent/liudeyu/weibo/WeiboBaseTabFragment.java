package com.interestcontent.liudeyu.weibo;

import com.blankj.utilcode.util.SizeUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;
import com.interestcontent.liudeyu.weibo.feeds.presents.WeiboFeedPresenter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import java.util.List;

/**
 * Created by liudeyu on 2017/12/30.
 */

/**
 * 检测是否登录微博，并且给予一定操作
 */
public abstract class WeiboBaseTabFragment extends AbsFeedFragment implements IMvpView<List<WeiboBean>>{


    protected int mCurrentPage = 1;
    protected int mEveryPageDataNum = 20;

    private WeiboFeedPresenter mFeedPresenter;


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
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(getActivity().getResources().getColor(R.color.md_white_1000))
                .size(SizeUtils.dp2px(1.0f))
                .build());
        startRequestWeiboFeed(true, WeiboFeedPresenter.FEED_QUEST_TYPE.FIRST_FLUSH);
    }


    protected abstract int  provideItemTabKey();

    protected void startRequestWeiboFeed(boolean showLoadMore, WeiboFeedPresenter.FEED_QUEST_TYPE type) {
        if(mFeedPresenter==null){
            mFeedPresenter=new WeiboFeedPresenter();
            mFeedPresenter.attachView(this);
        }
        String url = providedRequestDataUrl();
        int itemTabkey=provideItemTabKey();
        mFeedPresenter.execute(url,itemTabkey,type);
        if (showLoadMore) {
            mBaseAdapter.showLoading();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mFeedPresenter!=null){
            mFeedPresenter.detachView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mFeedPresenter!=null){
            mFeedPresenter.detachView();
        }
    }
}
