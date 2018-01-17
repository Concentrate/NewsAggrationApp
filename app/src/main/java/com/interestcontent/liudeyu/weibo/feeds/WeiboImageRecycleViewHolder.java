package com.interestcontent.liudeyu.weibo.feeds;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.interestcontent.liudeyu.R;

/**
 * Created by liudeyu on 2018/1/17.
 */
class WeiboImageRecycleViewHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;

    public WeiboImageRecycleViewHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.image_iv);
    }

}
