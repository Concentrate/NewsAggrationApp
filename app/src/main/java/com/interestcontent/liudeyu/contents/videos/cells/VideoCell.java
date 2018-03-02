package com.interestcontent.liudeyu.contents.videos.cells;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;

import com.blankj.utilcode.util.NetworkUtils;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.contents.videos.beans.VideoBean;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

import java.util.HashSet;
import java.util.Set;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by liudeyu on 2018/2/12.
 */

public class VideoCell extends RVBaseCell<VideoBean> {
    private static final String TAG = "VideoCell";
    private static int createHoldercount = 0;
    private android.support.v4.app.Fragment mFragment;
    private Activity mActivity;
    private static Set<PlayerView> mPlayerViewSet = new HashSet<>();
    private PlayerView mPlayerView;

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
        initViews(holder);
        return holder;
    }

    private void initViews(MyViewHodler holder) {
        ViewStub viewStub = (ViewStub) holder.getView(R.id.video_player_viewstub);
        View view1 = null;
        view1 = viewStub.inflate();
        PlayerView mPlayerView = new PlayerView(mActivity, view1);
        mPlayerViewSet.add(mPlayerView);
        holder.setPlayerView(mPlayerView);
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        if (mData.getData() == null) {
            return;
        }
        String avaterUrl = "";
        if (mData.getData().getAuthor() != null) {
            avaterUrl = mData.getData().getAuthor().getIcon();
        }
        Glide.with(mFragment).load(avaterUrl).placeholder(R.drawable.tab_videos_selector).into(holder.getImageView(R.id.author_iv));
        holder.getTextView(R.id.video_title).setText(mData.getData().getTitle());
        holder.getView(R.id.author_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        String coverUrl = "";
        String mPlayUrl = mData.getData().getPlayUrl();
        if (mData.getData().getCover() != null) {
            coverUrl = mData.getData().getCover().getFeed();
        }
        if (!NetworkUtils.isWifiConnected()) {
            if (mData.getData().getPlayInfo() != null && !mData.getData().getPlayInfo().isEmpty()) {
                for (VideoBean.DataBean.PlayInfoBean playInfoBean : mData.getData().getPlayInfo()) {
                    if ("normal".equals(playInfoBean.getType())) {
                        mPlayUrl = playInfoBean.getUrl();
                    }
                }
            }
        }
        final String finalCoverUrl = coverUrl;
        mPlayerView = ((MyViewHodler) holder).getPlayerView();
        Logger.d(TAG, "set size is " + mPlayerViewSet.size());
        mPlayerView
                .setTitle(mData.getData().getTitle())
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(mFragment).load(finalCoverUrl).into(ivThumbnail);
                    }

                })
                .hideRotation(true)
                .hideFullscreen(true)
                .hideRotation(true)
                .setForbidDoulbeUp(true)
                .setPlaySource(mPlayUrl)
                .setForbidHideControlPanl(true)
                .seekTo(0)
                .hideCenterPlayer(false);
        mPlayerView.setBrightness(40);

        mPlayerView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                mPlayerView.hideAllUI();
                mPlayerView.hideCenterPlayer(true);
                return false;
            }
        });
    }


    private void onPauseOthersPlayerViewExcept(PlayerView mPlayerView) {
        for (PlayerView playerView : mPlayerViewSet) {
            if (playerView != mPlayerView) {
                mPlayerView.stopPlay();
            }
        }
    }


    public static void destoryAllPlayerView() {
        for (PlayerView playerView : mPlayerViewSet) {
            playerView.onDestroy();
        }
        mPlayerViewSet.clear();
    }

    @Override
    public void releaseResource() {
        super.releaseResource();
        mPlayerView.stopPlay();
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
