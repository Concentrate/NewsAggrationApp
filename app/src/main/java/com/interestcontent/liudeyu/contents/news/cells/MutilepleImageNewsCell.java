package com.interestcontent.liudeyu.contents.news.cells;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.interestcontent.liudeyu.contents.news.beans.NewsTechBean;
import com.interestcontent.liudeyu.contents.weibo.contents.PictureBrowseActivity;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by liudeyu on 2018/2/5.
 */

public class MutilepleImageNewsCell extends RVBaseCell<NewsTechBean> {

    private Context mActivity;
    private Fragment mFragment;

    public MutilepleImageNewsCell(Fragment fragment, NewsTechBean newsTechnoBean) {
        super(newsTechnoBean);
        mFragment = fragment;
        mActivity = mFragment.getActivity();
    }

    @Override
    public int getItemType() {
        return FeedConstants.NEWS_MULTIPLE_IMAGE_TYPE;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.news_item_multiple_image_display_cell, parent, false);
        return new RVBaseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        holder.getTextView(R.id.news_title_tv).setText(mData.getTitle());
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
        RecyclerView imaveRecycleView = (RecyclerView) holder.getView(R.id.news_image_preview_listview);
        imaveRecycleView.setAdapter(new CommonAdapter<String>(mActivity, R.layout.multiple_imageview_display, mData.getImageUrls()) {
            @Override
            protected void convert(ViewHolder holder, String s, final int position) {
                Glide.with(mContext).load(s).transform(new CenterCrop(mContext))
                        .override(mContext.getResources().getDimensionPixelSize(R.dimen.news_mutiple_images_dis_size),
                                mContext.getResources().getDimensionPixelSize(R.dimen.news_mutiple_images_dis_size))
                        .into((ImageView) holder.getView(R.id.news_multi_image_iv));
                final String picUrl = s;
                holder.getView(R.id.news_multi_image_iv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<String> tmpImgs = new ArrayList<>();
                        tmpImgs.addAll(mData.getImageUrls());
                        PictureBrowseActivity.start(mContext, tmpImgs,position);
                    }
                });

            }
        });
        if (imaveRecycleView.getItemDecorationCount() == 0) {
            VerticalDividerItemDecoration verticalDividerItemDecoration = new VerticalDividerItemDecoration.Builder(mActivity)
                    .size(10).colorResId(R.color.white).build();
            imaveRecycleView.setLayoutManager(new LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false));
            imaveRecycleView.addItemDecoration(verticalDividerItemDecoration);
        }
    }
}
