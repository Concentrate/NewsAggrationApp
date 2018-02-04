package com.interestcontent.liudeyu.weibo.feeds.presents;

import com.interestcontent.liudeyu.base.dataManager.FeedDataManager;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.mvp.MvpPresenter;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;

import java.util.List;

/**
 * Created by liudeyu on 2018/1/22.
 */

public class WeiboFeedPresenter extends MvpPresenter<List<WeiboBean>,IMvpView<List<WeiboBean>>> {

    public enum  FEED_QUEST_TYPE{
        FIRST_FLUSH, NORMAL_BY_NET,REFLASH
    }

    /**三个参数， url,type,flush type*/
    @Override
    public List<WeiboBean> doWork(Object... params) throws Exception {
        String url= (String) params[0];
        int itemType= (int) params[1];
        FEED_QUEST_TYPE type= (FEED_QUEST_TYPE) params[2];

        switch (type){
            case NORMAL_BY_NET:
                return FeedDataManager.getInstance().getWeiboBeanListByNet(itemType,url,false);
            case REFLASH:
                return FeedDataManager.getInstance().reflashWeiboListByNet(itemType,url);
            case FIRST_FLUSH:
                return FeedDataManager.getInstance().getWeiboListAtFirst(itemType,url);
        }
        return null;
    }

}
