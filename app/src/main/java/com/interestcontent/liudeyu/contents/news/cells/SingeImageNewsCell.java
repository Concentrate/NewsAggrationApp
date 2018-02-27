package com.interestcontent.liudeyu.contents.news.cells;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.interestcontent.liudeyu.contents.news.beans.NewsTechnoBean;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liudeyu on 2018/2/4.
 */

public class SingeImageNewsCell extends RVBaseCell<NewsTechnoBean> {


    private Activity mActivity;
    private Fragment mFragment;


    public SingeImageNewsCell(NewsTechnoBean newsTechnoBean, Fragment fragment) {
        super(newsTechnoBean);
        mFragment = fragment;
        mActivity = mFragment.getActivity();
    }


    @Override
    public void releaseResource() {

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
    public void onBindViewHolder(RVBaseViewHolder holder, final int position) {
        holder.getTextView(R.id.news_title_tv).setText(mData.getTitle());
        if (mData.getImageUrls() != null) {
            Glide.with(mFragment).load(mData.getImageUrls().get(0)).centerCrop().into(holder.getImageView(R.id.news_display_image_iv));
        } else {
            holder.getImageView(R.id.news_display_image_iv).setVisibility(View.GONE);
        }
        holder.getTextView(R.id.news_publish_source_tv).setText(mData.getPosterScreenName());
        holder.getTextView(R.id.news_comment_count_tv).setText("评论数:" + mData.getCommentCount());
        long publishTime = mData.getPublishDate();
        Date date = new Date(publishTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMMdd日");
        holder.getTextView(R.id.news_publish_time_tv).setText(dateFormat.format(date));
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = BrowseActivity.getIntent(mData.getUrl(), true);
                intent.setClass(mActivity, BrowseActivity.class);
                mActivity.startActivity(intent);
            }
        });
    }


}
