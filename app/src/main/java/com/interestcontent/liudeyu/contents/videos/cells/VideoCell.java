package com.interestcontent.liudeyu.contents.videos.cells;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.NetworkUtils;
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
        if (mData.getData() == null) {
            return;
        }
        holder.getTextView(R.id.video_title).setText(mData.getData().getTitle());
        holder.getTextView(R.id.video_des).setText(mData.getData().getDescription());
        String coverUrl = "";
        String playUrl = mData.getData().getPlayUrl();
        if (mData.getData().getCover() != null) {
            coverUrl = mData.getData().getCover().getFeed();
        }
        if (!NetworkUtils.isWifiConnected()) {
            if (mData.getData().getPlayInfo() != null && !mData.getData().getPlayInfo().isEmpty()) {
                for (VideoBean.DataBean.PlayInfoBean playInfoBean : mData.getData().getPlayInfo()) {
                    if ("normal".equals(playInfoBean.getType())) {
                        playUrl = playInfoBean.getUrl();
                    }
                }
            }
        }
        final String finalCoverUrl = coverUrl;
        View tmpVideoView = holder.getView(R.id.video_player_layout);
//        PlayerView playerView = new PlayerView(mActivity, view1)
//                .setTitle(mData.getData().getTitle())
//                .setScaleType(PlayStateParams.fitparent)
//                .hideMenu(true)
//                .forbidTouch(false)
//                .showThumbnail(new OnShowThumbnailListener() {
//                    @Override
//                    public void onShowThumbnail(ImageView ivThumbnail) {
//
//                        Glide.with(mFragment).load(finalCoverUrl).into(ivThumbnail);
//                    }
//
//                })
//                .setPlaySource(playUrl);
    }
}
