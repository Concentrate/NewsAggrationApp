package com.interestcontent.liudeyu.contents.videos.components;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.contents.videos.beans.VideoBean;

/**
 * Created by liudeyu on 2018/3/2.
 */

public class VideoDisplayActivity extends BaseActivity {
    private static final String VIDEOBEAN="VIDEOBEAN";
    public static void start(Context context, VideoBean videoBean) {
        Intent starter = new Intent(context, VideoDisplayActivity.class);
        starter.putExtra(VIDEOBEAN,videoBean);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        if(intent!=null){

        }
    }

    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected View getResourceLayout() {
        return null;
    }

    @Override
    protected void onBackButtonEvent() {
        finish();
    }
}
