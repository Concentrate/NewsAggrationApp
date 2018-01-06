package com.interestcontent.liudeyu.weibo.feeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBeanTestRequest;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

import java.util.List;

/**
 * Created by liudeyu on 2018/1/2.
 */

public class WeiboCell extends RVBaseCell<List<WeiboBeanTestRequest.StatusesBean>> {

    private Context mContext;

    public WeiboCell(List<WeiboBeanTestRequest.StatusesBean> data) {
        super(data);
    }

    public WeiboCell(List<WeiboBeanTestRequest.StatusesBean> data, Context context) {
        super(data);
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
                view = LayoutInflater.from(mContext).inflate(R.layout.weibo_feed_cell_layout, null);
                break;
        }
        RVBaseViewHolder viewHolder = new RVBaseViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        holder.getTextView(R.id.wb_content_tv).setText(mData.get(position).getText());
        holder.getTextView(R.id.create_time_tv).setText(mData.get(position).getCreated_at());
    }

}
