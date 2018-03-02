package com.interestcontent.liudeyu.contents.videos;

import com.dou361.ijkplayer.widget.PlayerView;

/**
 * Created by liudeyu on 2018/3/1.
 */

public class VideoPlayEvent {
    public  boolean isPlay;

    public VideoPlayEvent(boolean isPlay) {
        this.isPlay = isPlay;
    }

    public PlayerView mPlayerView;
}
