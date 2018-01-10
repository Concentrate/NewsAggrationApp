package com.interestcontent.liudeyu.base.baseComponent;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.interestcontent.liudeyu.R;

/**
 * Created by liudeyu on 2018/1/10.
 */

public abstract class BaseActivity extends AbsActivity implements View.OnClickListener {

    protected Toolbar mToolbar;
    protected ImageView mBackButton;
    protected TextView mToolbarTitle;
    private FrameLayout mFrameLayout;

    protected abstract boolean isUseToolBar();

    protected boolean isUseToolbarMenuItem() {
        return false;
    }

    protected abstract View getResourceLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        initUiData();
        mFrameLayout.addView(getResourceLayout());
        if (!isUseToolBar()) {
            mToolbar.setVisibility(View.GONE);
        }
    }

    private void initUiData() {
        mToolbar = findViewById(R.id.tool_bar);
        mBackButton = findViewById(R.id.back_iv);
        mToolbarTitle = findViewById(R.id.title_tv);
        mFrameLayout = findViewById(R.id.content_container_layout);
        mBackButton.setOnClickListener(this);
        mToolbarTitle.setOnClickListener(this);
        mToolbar.setBackgroundColor(setToolbarColor());
        setSupportActionBar(mToolbar);
    }

    protected @ColorInt int setToolbarColor(){
        return getResources().getColor(R.color.md_white_1000);
    }
    protected void onTitleTextClick() {
    }

    protected void onBackButtonEvent() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                onBackButtonEvent();
                break;
            case R.id.title_tv:
                onTitleTextClick();
                break;
        }
    }
}
