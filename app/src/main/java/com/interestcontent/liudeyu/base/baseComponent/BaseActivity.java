package com.interestcontent.liudeyu.base.baseComponent;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.commonlib.components.AbsActivity;
import com.interestcontent.liudeyu.R;

/**
 * Created by liudeyu on 2018/1/10.
 */

public abstract class BaseActivity extends AbsActivity implements View.OnClickListener {

    protected Toolbar mToolbar;
    protected ImageView mBackButton;
    protected TextView mToolbarTitle;
    private FrameLayout mFrameLayout;
    protected TextView mToolbarRightBtn;
    protected LinearLayout mToolbarCustomContainer;

    protected abstract boolean isUseToolBar();

    protected boolean isUseToolbarMenuItem() {
        return false;
    }

    protected abstract View getResourceLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        initBaseUiData();
        if (getResourceLayout() != null) {
            mFrameLayout.addView(getResourceLayout());
        }
        if (!isUseToolBar()) {
            mToolbar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setStatusBarColor();
        setToolbarColor();
    }

    protected void initBaseUiData() {
        mToolbar = findViewById(R.id.tool_bar);
        mBackButton = findViewById(R.id.back_iv);
        mToolbarTitle = findViewById(R.id.title_tv);
        mFrameLayout = findViewById(R.id.content_container_layout);
        mToolbarRightBtn = findViewById(R.id.tool_bar_right_btn);
        mToolbarCustomContainer = findViewById(R.id.tool_bar_container_ll);
        mBackButton.setOnClickListener(this);
        mToolbarTitle.setOnClickListener(this);
        mToolbarRightBtn.setOnClickListener(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbarRightBtn.setVisibility(View.GONE);
    }


    protected int provideToolbarColor() {
        return getResources().getColor(R.color.white);
    }

    private void setToolbarColor() {
        mToolbar.setBackgroundColor(provideToolbarColor());
    }

    protected void onTitleTextClick() {
    }

    protected void onBackButtonEvent() {
        onBackPressed();
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
            case R.id.tool_bar_right_btn:
                onToolbarRightBtnClick();
                break;
        }
    }

    protected void onToolbarRightBtnClick() {
    }
}
