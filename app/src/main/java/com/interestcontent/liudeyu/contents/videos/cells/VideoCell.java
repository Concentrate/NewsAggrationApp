package com.interestcontent.liudeyu.contents.videos.cells;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.contents.videos.beans.VideoBean;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

/**
 * Created by liudeyu on 2018/2/12.
 */

public class VideoCell extends RVBaseCell<VideoBean> {
    private android.support.v4.app.Fragment mFragment;
    private Activity mActivity;

    public VideoCell(VideoBean videoBean, Fragment fragment) {
        super(videoBean);
        mFragment = fragment;
        mActivity = mFragment.getActivity();
    }

    @Override
    public int getItemType() {
        return FeedConstants.VIDEO_CELL_TYPE;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVBaseViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.video_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        if (mData.getData().getAuthor() != null && mData.getData().getAuthor().getName() != null) {

        }


    }
}
