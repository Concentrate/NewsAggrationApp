package com.interestcontent.liudeyu.contents.videos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.NetworkUtils;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.contents.videos.beans.VideoBean;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by liudeyu on 2018/3/5.
 */

public class VideoPlayManager {
    public static VideoPlayManager sVideoPlayManager;
    private int mLastPlayPosition;
    private PlayerView mPlayerView;
    private View mLastVideoView;

    private VideoPlayManager() {
    }

    public static VideoPlayManager getInstance() {
        if (sVideoPlayManager == null) {
            synchronized (VideoPlayManager.class) {
                if (sVideoPlayManager == null) {
                    sVideoPlayManager = new VideoPlayManager();
                }
            }
        }
        return sVideoPlayManager;
    }

    public void setPlayerView(final Activity activity, final ViewGroup contentViewRoot, VideoBean bean, int position) {
        if (mPlayerView != null) {
            destoryAllVideoViews();
        }
        mLastPlayPosition = position;
        mLastVideoView = LayoutInflater.from(activity).inflate(R.layout.simple_player_view_player, null);
        contentViewRoot.addView(mLastVideoView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mPlayerView = new PlayerView(activity, mLastVideoView);
        String coverUrl = "";
        String mPlayUrl = bean.getData().getPlayUrl();
        if (bean.getData().getCover() != null) {
            coverUrl = bean.getData().getCover().getFeed();
        }
        if (!NetworkUtils.isWifiConnected()) {
            if (bean.getData().getPlayInfo() != null && !bean.getData().getPlayInfo().isEmpty()) {
                for (VideoBean.DataBean.PlayInfoBean playInfoBean : bean.getData().getPlayInfo()) {
                    if ("normal".equals(playInfoBean.getType())) {
                        mPlayUrl = playInfoBean.getUrl();
                    }
                }
            }
        }
        final String finalCoverUrl = coverUrl;
        mPlayerView
                .setTitle(bean.getData().getTitle())
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .hideRotation(true)
                .hideFullscreen(true)
                .hideRotation(true)
                .setForbidDoulbeUp(true)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(activity).load(finalCoverUrl).into(ivThumbnail);
                    }
                })
                .setPlaySource(mPlayUrl)
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
        mPlayerView.startPlay();
    }

    private void destoryAllVideoViews() {
        mPlayerView.onDestroy();
        mPlayerView = null;
        ViewGroup viewGroup = (ViewGroup) mLastVideoView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(mLastVideoView);
        }
    }


    private void setLastPlayPosition(int lastPlayPosition) {
        mLastPlayPosition = lastPlayPosition;
    }

    public void onJudgeIfStopPlayVideo(int firstVisiblePosition) {
        if ((firstVisiblePosition - mLastPlayPosition >= 1 || mLastPlayPosition - firstVisiblePosition >1) && mPlayerView != null) {
            destoryAllVideoViews();
        }
    }

    public void onDestoryVideoPlayView() {
        if (mPlayerView != null) {
            destoryAllVideoViews();
        }
    }

}
