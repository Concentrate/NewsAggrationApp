package com.interestcontent.liudeyu.contents.zhihu.presenters;

import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.base.dataManager.FeedDataManager;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.mvp.MvpPresenter;
import com.interestcontent.liudeyu.contents.zhihu.bean.ZhihuJournayListRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liudeyu on 2018/2/8.
 */

public class ZhihuJournalListPresenter extends MvpPresenter<ZhihuJournayListRequest, IMvpView<ZhihuJournayListRequest>> {

    private Date lastUrlDate; //上一个请求回来的date

    @Override
    public ZhihuJournayListRequest doWork(Object... params) throws Exception {
        String url = (String) params[0];
        int itemTabKey = (int) params[1];
        FeedConstants.FEED_REQUEST_EMUM type = (FeedConstants.FEED_REQUEST_EMUM) params[2];
        ZhihuJournayListRequest zhihuJournayListRequest = null;
        switch (type) {
            case FIRST_FLUSH:
            case REFLASH:
                zhihuJournayListRequest = FeedDataManager.getInstance().getRequest(true, itemTabKey, url, ZhihuJournayListRequest.class);
                break;
            case NORMAL_BY_NET:
                zhihuJournayListRequest = FeedDataManager.getInstance().getRequest(true, itemTabKey, getLoadMoreUrl(), ZhihuJournayListRequest.class);
                break;
        }
        if (zhihuJournayListRequest != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            lastUrlDate = dateFormat.parse(zhihuJournayListRequest.getDate());
        }
        return zhihuJournayListRequest;
    }

    private String getLoadMoreUrl() {
        if (lastUrlDate == null) {
            return "";
        }
        Date lastDay = new Date(lastUrlDate.getTime() - 24 * 3600 * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String t1 = dateFormat.format(lastDay);
        return Constants.ZHIHU_JOURNAL_LIST_BEOFRE + t1;
    }

}
