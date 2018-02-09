package com.interestcontent.liudeyu.contents.weibo.contents.comment;

import com.google.gson.annotations.SerializedName;
import com.interestcontent.liudeyu.base.baseBeans.BaseRequest;
import com.interestcontent.liudeyu.contents.weibo.data.bean.WeiboCommontBean;

import java.util.List;

/**
 * Created by liudeyu on 2018/1/31.
 */

public class WeiboCommentRequet extends BaseRequest {
    @SerializedName(value = "total_number")
    private int totalNumber;
    @SerializedName(value = "comments")
    private List<WeiboCommontBean>mWeiboCommontBeans;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<WeiboCommontBean> getWeiboCommontBeans() {
        return mWeiboCommontBeans;
    }

    public void setWeiboCommontBeans(List<WeiboCommontBean> weiboCommontBeans) {
        mWeiboCommontBeans = weiboCommontBeans;
    }
}
