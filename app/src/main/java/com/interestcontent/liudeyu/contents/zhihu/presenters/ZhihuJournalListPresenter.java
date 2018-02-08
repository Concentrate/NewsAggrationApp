package com.interestcontent.liudeyu.contents.zhihu.presenters;

import com.interestcontent.liudeyu.base.dataManager.FeedDataManager;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.mvp.MvpPresenter;
import com.interestcontent.liudeyu.contents.zhihu.bean.ZhihuJournayListRequest;

/**
 * Created by liudeyu on 2018/2/8.
 */

public class ZhihuJournalListPresenter extends MvpPresenter<ZhihuJournayListRequest, IMvpView<ZhihuJournayListRequest>> {
    @Override
    public ZhihuJournayListRequest doWork(Object... params) throws Exception {
        String url = (String) params[0];
        int itemTabKey = (int) params[1];
        boolean isReflash= (boolean) params[2];
        return FeedDataManager.getInstance().getRequest(isReflash,itemTabKey,url,ZhihuJournayListRequest.class);
    }
}
