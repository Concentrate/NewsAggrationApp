package com.interestcontent.liudeyu.weibo.component;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;

import org.jetbrains.annotations.NotNull;

/**
 * Created by liudeyu on 2018/1/20.
 */

public class WeiboContentActivity extends BaseActivity {

    private static final String WEIBO_ID="WEIBO_ID";

    public static void start(Context context, @NotNull String weiboId) {
        Intent starter = new Intent(context, WeiboContentActivity.class);
        starter.putExtra(WEIBO_ID,weiboId);
        context.startActivity(starter);
    }
    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected int setToolbarColor() {
        return getResources().getColor(R.color.md_blue_300);
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.weibo_main_content_layout, null);
    }

    @Override
    protected void onBackButtonEvent() {
        finish();
    }
}
