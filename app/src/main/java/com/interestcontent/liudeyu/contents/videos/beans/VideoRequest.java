package com.interestcontent.liudeyu.contents.videos.beans;

import com.interestcontent.liudeyu.base.baseBeans.BaseRequest;

import java.util.List;

/**
 * Created by liudeyu on 2018/2/12.
 */

public class VideoRequest extends BaseRequest {

    /**
     * itemList : []
     * count : 16
     * total : 0
     * nextPageUrl : http://baobab.kaiyanapp.com/api/v4/tabs/selected?date=1518224400000&num=2&page=2
     * adExist : false
     * date : 1518397200000
     * nextPublishTime : 1518483600000
     * dialog : null
     * topIssue : null
     * refreshCount : 0
     * lastStartId : 0
     */

    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;
    private long date;
    private long nextPublishTime;
    private Object dialog;
    private Object topIssue;
    private int refreshCount;
    private int lastStartId;
    private List<VideoBean> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public boolean isAdExist() {
        return adExist;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public Object getDialog() {
        return dialog;
    }

    public void setDialog(Object dialog) {
        this.dialog = dialog;
    }

    public Object getTopIssue() {
        return topIssue;
    }

    public void setTopIssue(Object topIssue) {
        this.topIssue = topIssue;
    }

    public int getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(int refreshCount) {
        this.refreshCount = refreshCount;
    }

    public int getLastStartId() {
        return lastStartId;
    }

    public void setLastStartId(int lastStartId) {
        this.lastStartId = lastStartId;
    }

    public List<VideoBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<VideoBean> itemList) {
        this.itemList = itemList;
    }
}
