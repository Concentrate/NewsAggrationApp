package com.interestcontent.liudeyu.contents.videos.cells;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.contents.videos.VideoPlayManager;
import com.interestcontent.liudeyu.contents.videos.beans.VideoBean;
import com.interestcontent.liudeyu.contents.videos.components.VideoDisplayActivity;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

/**
 * Created by liudeyu on 2018/2/12.
 */

public class VideoCell extends RVBaseCell<VideoBean> {
    private static final String TAG = "VideoCell";
    private static int createHoldercount = 0;
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
        Logger.d(TAG, "onCreateViewHolder times is " + (++createHoldercount));
        MyViewHodler holder = new MyViewHodler(LayoutInflater.from(mActivity).inflate(R.layout.video_cell, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final RVBaseViewHolder holder, final int position) {
        if (mData.getData() == null) {
            return;
        }
        String avaterUrl = "";
        if (mData.getData().getAuthor() != null) {
            avaterUrl = mData.getData().getAuthor().getIcon();
        }
        Glide.with(mFragment).load(avaterUrl).placeholder(R.drawable.tab_videos_selector).centerCrop()
                .into(holder.getImageView(R.id.author_iv));
        holder.getTextView(R.id.video_title).setText(mData.getData().getTitle());
        holder.getView(R.id.author_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoDisplayActivity.start(mActivity, mData);

            }
        });
        String coverUrl = "";
        if (mData.getData().getCover() != null) {
            coverUrl = mData.getData().getCover().getFeed();
        }
        Glide.with(mFragment).load(coverUrl).into(holder.getImageView(R.id.video_image_pre_iv));
        holder.getView(R.id.video_play_zoom_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout viewGroup = (RelativeLayout) holder.getView(R.id.video_play_zoom_rl);
                VideoPlayManager.getInstance().setPlayerView(mActivity, (ViewGroup) holder.getView(R.id.video_play_zoom_rl),
                        mData, position);

            }
        });
    }


    @Override
    public void releaseResource() {
        super.releaseResource();
    }

    private static class MyViewHodler extends RVBaseViewHolder {

        private PlayerView mPlayerView;

        public MyViewHodler(View itemView) {
            super(itemView);
        }

        private PlayerView getPlayerView() {
            return mPlayerView;
        }

        private void setPlayerView(PlayerView playerView) {
            mPlayerView = playerView;
        }
    }
}
