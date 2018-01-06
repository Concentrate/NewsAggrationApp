package com.interestcontent.liudeyu.weibo.feeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

import java.util.List;

/**
 * Created by liudeyu on 2018/1/2.
 */

public class WeiboCell extends RVBaseCell<List<WeiboBean>> {

    private Context mContext;

    public WeiboCell(List<WeiboBean> weiboBeans) {
        super(weiboBeans);
    }

    public WeiboCell(List<WeiboBean> weiboBeans, Context context) {
        super(weiboBeans);
        mContext = context;
    }

    @Override
    public int getItemType() {
        return FeedConstants.FEED_NORMAL_WEIBO_TYPE;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case FeedConstants.FEED_NORMAL_WEIBO_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.weibo_feed_layout, null);
                break;
        }
        RVBaseViewHolder viewHolder = new RVBaseViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {

    }
}
