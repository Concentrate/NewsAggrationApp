package com.interestcontent.liudeyu.contents.videos.components;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.contents.videos.beans.VideoBean;
import com.interestcontent.liudeyu.settings.ThemeDataManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudeyu on 2018/3/2.
 */

public class VideoDisplayActivity extends BaseActivity {
    private static final String VIDEOBEAN = "VIDEOBEAN";
    private VideoBean mVideoBean;

    @BindView(R.id.view_stub)
    ViewStub mViewStub;
    @BindView(R.id.avater_iv)
    ImageView authorIv;
    @BindView(R.id.author_tv)
    TextView authorTv;
    @BindView(R.id.video_des)
    TextView videoDes;
    @BindView(R.id.video_title)
    TextView videoTitle;

    private PlayerView mPlayerView;

    public static void start(Context context, VideoBean videoBean) {
        Intent starter = new Intent(context, VideoDisplayActivity.class);
        starter.putExtra(VIDEOBEAN, videoBean);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            mVideoBean = (VideoBean) intent.getSerializableExtra(VIDEOBEAN);
        }
        initViews();
        initToolbarViews();
    }


    private void initToolbarViews() {
        if (mToolbar.getVisibility() == View.VISIBLE && mVideoBean.getData().getWebUrl() != null) {
            final String shareUrl = mVideoBean.getData().getWebUrl().getRaw();
            ImageView shareImage = new ImageView(this);
            shareImage.setImageResource(R.drawable.share_icon);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources()
                    .getDimensionPixelSize(R.dimen.news_share_icon_size), getResources()
                    .getDimensionPixelSize(R.dimen.news_share_icon_size));
            layoutParams.setMargins(0, 0, SizeUtils.dp2px(10), 0);
            shareImage.setLayoutParams(layoutParams);
            mToolbarCustomContainer.addView(shareImage);
            shareImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String des = "这有个视频，我想让你看看, ";
                    intent.putExtra(Intent.EXTRA_SUBJECT, des);//添加分享内容标题
                    intent.putExtra(Intent.EXTRA_TEXT, des + shareUrl);//添加分享内容
                    Intent shareIntent = Intent.createChooser(intent, "选择分享方式");
                    VideoDisplayActivity.this.startActivity(shareIntent);
                }
            });

        }
    }

    private void initViews() {
        if (mVideoBean == null || mVideoBean.getData() == null) {
            return;
        }
        View view = mViewStub.inflate();
        mPlayerView = new PlayerView(this, view);
        List<VideoijkBean> list = new ArrayList<VideoijkBean>();
        List<VideoBean.DataBean.PlayInfoBean> playInfoBeans = mVideoBean.getData().getPlayInfo();
        if (playInfoBeans != null && !playInfoBeans.isEmpty()) {
            for (VideoBean.DataBean.PlayInfoBean playInfoBean : playInfoBeans) {
                VideoijkBean videoijkBean = new VideoijkBean();
                videoijkBean.setUrl(playInfoBean.getUrl());
                videoijkBean.setStream(playInfoBean.getName());
                list.add(videoijkBean);
            }
        }
        String coverUrl = "";
        if (mVideoBean.getData().getCover() != null) {
            coverUrl = mVideoBean.getData().getCover().getDetail();
        }
        final String finalCoverUrl = coverUrl;
        mPlayerView.setTitle(mVideoBean.getData().getTitle())
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        /**加载前显示的缩略图*/
                        Glide.with(VideoDisplayActivity.this)
                                .load(finalCoverUrl)
                                .into(ivThumbnail);
                    }
                })
                .hideSteam(false)
                .setPlaySource(list)
                .startPlay();
        mPlayerView.setBrightness(45);
        VideoBean.DataBean.AuthorBean authorBean = mVideoBean.getData().getAuthor();
        if (authorBean != null) {
            Glide.with(this).load(authorBean.getIcon()).into(authorIv);
            authorTv.setText(authorBean.getName());
            mToolbarTitle.setText(authorBean.getDescription());
        }
        videoDes.setText(mVideoBean.getData().getDescription());
        videoTitle.setText(mVideoBean.getData().getTitle());
    }


    @Override
    protected int getStatusBarColor() {
        return ThemeDataManager.getInstance().getThemeColorInt();
    }

    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.video_display_layout, null);
    }

    @Override
    protected void onBackButtonEvent() {
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayerView != null) {
            mPlayerView.onPause();
        }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPlayerView != null) {
            mPlayerView.onResume();
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayerView != null) {
            mPlayerView.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mPlayerView != null) {
            mPlayerView.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (mPlayerView != null && mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
