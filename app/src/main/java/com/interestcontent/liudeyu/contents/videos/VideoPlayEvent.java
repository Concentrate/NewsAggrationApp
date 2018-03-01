package com.interestcontent.liudeyu.contents.videos;

import android.support.annotation.Nullable;

/**
 * Created by liudeyu on 2018/3/1.
 */

public class VideoPlayEvent {
    public  boolean isPlay;
    public @Nullable String playUrl; // 辨别是否是当前播放视频可为null

    public VideoPlayEvent(boolean isPlay) {
        this.isPlay = isPlay;
    }
}
