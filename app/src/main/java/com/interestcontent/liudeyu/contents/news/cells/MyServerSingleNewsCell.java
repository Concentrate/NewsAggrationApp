package com.interestcontent.liudeyu.contents.news.cells;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.interestcontent.liudeyu.contents.news.beans.NewsMyserverDataBean;
import com.interestcontent.liudeyu.contents.news.newsUtil.NewsUrlUtils;
import com.interestcontent.liudeyu.util.NumberUtils;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liudeyu on 2018/4/9.
 */

public class MyServerSingleNewsCell extends RVBaseCell<NewsMyserverDataBean> {

    private Activity mActivity;
    private Fragment mFragment;

    public MyServerSingleNewsCell(NewsMyserverDataBean newsMyserverDataBean, Fragment fragment) {
        super(newsMyserverDataBean);
        mFragment = fragment;
        mActivity = mFragment.getActivity();
    }

    public MyServerSingleNewsCell(NewsMyserverDataBean newsMyserverDataBean) {
        super(newsMyserverDataBean);
    }

    @Override
    public int getItemType() {
        return FeedConstants.NEWS_SINGLE_IMAGE_TYPE;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.news_cell_singleimage_item_layout, parent, false);
        return new RVBaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        holder.getTextView(R.id.news_title_tv).setText(mData.getTitle());
        if (mData.getMiddle_image() != null) {
            Glide.with(mFragment).load(mData.getMiddle_image())
                    .centerCrop().into(holder.getImageView(R.id.news_display_image_iv));
        } else {
            return;
        }
        holder.getTextView(R.id.news_publish_source_tv).setText(mData.getSource());
        holder.getTextView(R.id.news_comment_count_tv).setText("评论数:" + mData.getComments_count());
        String publishTime = mData.getBehot_time();
        long realTime = 0;
        if (NumberUtils.isNumeric(publishTime)) {
            realTime = Long.parseLong(publishTime);
        }
        Date date = new Date(realTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        holder.getTextView(R.id.news_publish_time_tv).setText(dateFormat.format(date));
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //统计数据
                Map<String, String> map = new HashMap<>();
                //post group_id是故意的
                map.put(Constants.NEWS_MYSERVER_PARAMETER.NEWS_ID, mData.getGroup_id() + "");
                map.put(Constants.NEWS_MYSERVER_PARAMETER.ACCOUNT_ID, NewsUrlUtils.getRecomendAccountId());
                OkHttpUtils.get().url(Constants.NEWS_MYSERVER_RECOMMEND_DATA).params(map).build().execute(null);
                Intent intent = BrowseActivity.getIntent(mData.getSource_url(), true);
                intent.setClass(mActivity, BrowseActivity.class);
                mActivity.startActivity(intent);
            }
        });
        if (!mData.getVideo_id().equals("0")) {
            holder.getTextView(R.id.news_video_play_count).setVisibility(View.VISIBLE);
            holder.getTextView(R.id.news_video_play_count).setText("播放次数 " + mData.getVideo_play_count());
        } else {
            holder.getTextView(R.id.news_video_play_count).setVisibility(View.GONE);
        }
    }
}
