package com.interestcontent.liudeyu.contents.weibo.contents;

import android.os.Bundle;

import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;

/**
 * Created by liudeyu on 2018/1/24.
 */

public class WeiboContentBrowseActivity extends BrowseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected boolean useImmerseMode() {
        return true;
    }


    @Override
    protected boolean isUseFullAScreen() {
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
