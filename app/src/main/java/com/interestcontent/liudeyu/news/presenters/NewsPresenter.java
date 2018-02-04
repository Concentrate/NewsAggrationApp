package com.interestcontent.liudeyu.news.presenters;

import com.interestcontent.liudeyu.base.dataManager.FeedDataManager;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.mvp.MvpPresenter;
import com.interestcontent.liudeyu.news.beans.NewsTechnoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class NewsPresenter extends MvpPresenter<List<NewsTechnoBean>, IMvpView<List<NewsTechnoBean>>> {

    public enum FEED_QUEST_TYPE {
        FIRST_FLUSH, NORMAL_BY_NET, REFLASH
    }

    @Override
    public List<NewsTechnoBean> doWork(Object... params) throws Exception {
        if (params == null || params.length < 3) {
            throw new IllegalArgumentException("wrong argument num");
        }
        String url = (String) params[0];
        int itemTab = (int) params[1];
        FEED_QUEST_TYPE type = (FEED_QUEST_TYPE) params[2];
        switch (type) {
            case FIRST_FLUSH:
                return FeedDataManager.getInstance().getNewsTechListAtFirst(itemTab, url);
            case REFLASH:
                return FeedDataManager.getInstance().getNewsTechListByNet(itemTab, url, true);
            case NORMAL_BY_NET:
                return FeedDataManager.getInstance().getNewsTechListByNet(itemTab, url, false);
            default:
                return new ArrayList<NewsTechnoBean>();
        }

    }
}
