package com.interestcontent.liudeyu.contents.news.presenters;

import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.base.dataManager.FeedDataManager;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.mvp.MvpPresenter;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.contents.news.beans.NewsMyserverDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/4/9.
 */

public class NewsMyServerNewsPresenter extends MvpPresenter<List<NewsMyserverDataBean>, IMvpView<List<NewsMyserverDataBean>>> {
    @Override
    public List<NewsMyserverDataBean> doWork(Object... params) throws Exception {
        if (params == null || params.length < 3) {
            throw new IllegalArgumentException("wrong argument num");
        }
        String url = (String) params[0];
        ItemTab itemTab = (ItemTab) params[1];
        FeedConstants.FEED_REQUEST_EMUM request_emum = (FeedConstants.FEED_REQUEST_EMUM) params[2];
        switch (request_emum) {
            case FIRST_FLUSH:
                return FeedDataManager.getInstance().getMyServerDataNewsAtFirst(itemTab.getItemKey(), url);
            case REFLASH:
                return FeedDataManager.getInstance().getMyServerNewsByNet(itemTab.getItemKey(), url, true);
            case NORMAL_BY_NET:
                return FeedDataManager.getInstance().getMyServerNewsByNet(itemTab.getItemKey(), url, false);
            default:
                return new ArrayList<NewsMyserverDataBean>();
        }
    }
}
