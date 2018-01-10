package com.interestcontent.liudeyu.weibo.feeds;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboRequest;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboUserBean;
import com.luseen.autolinklibrary.AutoLinkMode;
import com.luseen.autolinklibrary.AutoLinkOnClickListener;
import com.luseen.autolinklibrary.AutoLinkTextView;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

import java.util.List;

/**
 * Created by liudeyu on 2018/1/2.
 */

public class WeiboCell extends RVBaseCell<List<WeiboRequest.StatusesBean>> {

    private Context mContext;

    public WeiboCell(List<WeiboRequest.StatusesBean> data) {
        super(data);
    }

    public WeiboCell(List<WeiboRequest.StatusesBean> data, Context context) {
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
                view = LayoutInflater.from(mContext).inflate(R.layout.weibo_feed_cell_layout, parent, false);
                initViewState(view);
                break;
        }
        RVBaseViewHolder viewHolder = new RVBaseViewHolder(view);
        return viewHolder;
    }

    private void initViewState(View view) {
        AutoLinkTextView autoLinkTextView = (AutoLinkTextView) view.findViewById(R.id.wb_content_tv);
        autoLinkTextView.addAutoLinkMode(
                AutoLinkMode.MODE_HASHTAG,
                AutoLinkMode.MODE_PHONE,
                AutoLinkMode.MODE_URL,
                AutoLinkMode.MODE_MENTION);
        autoLinkTextView.setHashtagModeColor(mContext.getResources().getColor(R.color.md_pink_100));
        autoLinkTextView.setPhoneModeColor(mContext.getResources().getColor(R.color.md_green_100));
        autoLinkTextView.setUrlModeColor(mContext.getResources().getColor(R.color.md_light_blue_400));
        autoLinkTextView.setMentionModeColor(mContext.getResources().getColor(R.color.md_deep_purple_100));
        autoLinkTextView.setEmailModeColor(ContextCompat.getColor(mContext, R.color.md_deep_orange_800));
        autoLinkTextView.setAutoLinkOnClickListener(new AutoLinkOnClickListener() {
            @Override
            public void onAutoLinkTextClick(AutoLinkMode autoLinkMode, String matchedText) {
                switch (autoLinkMode) {
                    case MODE_URL:
                        BrowseActivity.start(mContext, matchedText);
                        break;

                }
            }
        });
    }


    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        AutoLinkTextView autoLinkTextView = (AutoLinkTextView) holder.getTextView(R.id.wb_content_tv);
        autoLinkTextView.setAutoLinkText(mData.get(position).getText());
        holder.getTextView(R.id.create_time_tv).setText(mData.get(position).getCreated_at());
        WeiboUserBean userBean = mData.get(position).getUser();
        if (userBean != null) {
            holder.getTextView(R.id.author_tv).setText(userBean.getName());
            Glide.with(mContext).load(userBean.getProfile_image_url()).into(holder.getImageView(R.id.avater_iv));
        }
    }

}
