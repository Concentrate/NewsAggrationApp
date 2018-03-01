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
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.contents.videos.VideoPlayEvent;
import com.interestcontent.liudeyu.contents.videos.beans.VideoBean;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by liudeyu on 2018/2/12.
 */

public class VideoCell extends RVBaseCell<VideoBean> {
    private android.support.v4.app.Fragment mFragment;
    private Activity mActivity;
    private PlayerView mPlayerView;
    private static final int STOP_PLAY_MEDIA = R.layout.video_cell;

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
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (mData.getData() == null) {
            return;
        }
        holder.getTextView(R.id.video_title).setText(mData.getData().getTitle());
        holder.getTextView(R.id.video_des).setText(mData.getData().getDescription());
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
        ViewStub viewStub = (ViewStub) holder.getView(R.id.video_player_viewstub);
        View view1 = null;
        if (viewStub.getParent() != null) {
            view1 = viewStub.inflate();
            holder.getItemView().setTag(view1);
        } else {
            view1 = (View) holder.getItemView().getTag();
        }
        mPlayerView = new PlayerView(mActivity, view1)
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
                .hideCenterPlayer(false);
        mPlayerView.setBrightness(50);
        mPlayerView.getPlayerView().setTag(STOP_PLAY_MEDIA, mPlayUrl);

        final String finalPlayUrl = mPlayUrl;
        mPlayerView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                if (i == IMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START) {
                    //让其他视频停止播放
                    VideoPlayEvent event = new VideoPlayEvent(false);
                    event.playUrl = finalPlayUrl;
                    EventBus.getDefault().post(event);
                }
                return false;
            }
        });

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(VideoPlayEvent event) {
        if (!event.isPlay) {
            if (event.playUrl == null) {
                mPlayerView.onPause();
            } else {
                String beforeUrl = (String) mPlayerView.getPlayerView().getTag(STOP_PLAY_MEDIA);
                if (!event.playUrl.equals(beforeUrl)) {
                    mPlayerView.onPause();
                }
            }
        }
    }

    @Override
    public void releaseResource() {
        super.releaseResource();
        mPlayerView.onPause();
        mPlayerView.seekTo(0);
        EventBus.getDefault().unregister(this);
    }


}
