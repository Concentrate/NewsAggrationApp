package com.interestcontent.liudeyu.weibo.contents.comment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.interestcontent.liudeyu.R;
import com.luseen.autolinklibrary.AutoLinkTextView;

import butterknife.ButterKnife;

/**
 * Created by liudeyu on 2018/1/31.
 */

public class WeiboCommentListViewHolder extends RecyclerView.ViewHolder {

    public static final int VIEWTAG = R.layout.comment_item_layout;
    ImageView mImageView;
    TextView mAuthor;
    TextView mCreateTime;
    AutoLinkTextView mCommentText;
    ImageView mGoodAttitudeIv;
    TextView mGoodAttitudeTv;
    View mItemView;

    public WeiboCommentListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
        mItemView = itemView;
        mImageView = mItemView.findViewById(R.id.avater_iv);
        mAuthor = mItemView.findViewById(R.id.comment_author_name_tv);
        mCreateTime = mItemView.findViewById(R.id.comment_create_time_tv);
        mCommentText = mItemView.findViewById(R.id.comment_content_tv);
        mGoodAttitudeIv = mItemView.findViewById(R.id.comment_digger_iv);
        mGoodAttitudeTv = mItemView.findViewById(R.id.digger_count_tv);


    }

    public void setViewTag(int pos) {
        mItemView.setTag(VIEWTAG, pos);
    }

    public int getPos() {
        return (int) mItemView.getTag(VIEWTAG);
    }
}
