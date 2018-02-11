package com.interestcontent.liudeyu.contents.zhihu.cells;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseUiKit.aboutGlide.GlideRoundTransform;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.contents.zhihu.bean.StoriesBean;
import com.interestcontent.liudeyu.contents.zhihu.contents.ZhihuContentActivity;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

/**
 * Created by liudeyu on 2018/2/8.
 */

public class ZhihuItemCell extends RVBaseCell<StoriesBean> {
    private Activity mActivity;
    private android.support.v4.app.Fragment mFragment;

    public ZhihuItemCell(StoriesBean storiesBean, Fragment fragment) {
        super(storiesBean);
        mFragment = fragment;
        mActivity = mFragment.getActivity();
    }

    @Override
    public int getItemType() {
        return FeedConstants.OPINION_ZHIHU_CELL_TYPE;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.zhihu_feed_cell, parent, false);
        return new RVBaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, final int position) {

        if (mData.getImages().isEmpty()) {
            holder.getImageView(R.id.image_iv).setVisibility(View.GONE);
        }
        Glide.with(mFragment).load(mData.getImages().get(0)).transform(new GlideRoundTransform(mActivity))
                .into(holder.getImageView(R.id.image_iv));
        holder.getTextView(R.id.title).setText(mData.getTitle());
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZhihuContentActivity.start(mActivity, mData.getId() + "");
            }
        });
    }

}
