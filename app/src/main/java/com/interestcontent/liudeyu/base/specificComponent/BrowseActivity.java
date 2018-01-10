package com.interestcontent.liudeyu.base.specificComponent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowseActivity extends BaseActivity implements ChromeClientCallbackManager.ReceivedTitleCallback {

    public static final String LOAD_URL = "LOAD_URL".toLowerCase();

    @BindView(R.id.parent_container)
    RelativeLayout mRelativeLayout;
    private AgentWeb mAgentWeb;

    public static void start(Context context, String loadurl) {
        Intent starter = new Intent(context, BrowseActivity.class);
        starter.putExtra(LOAD_URL, loadurl);
        context.startActivity(starter);
    }

    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.activity_browse, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = "";
        if (intent != null) {
            url = intent.getStringExtra(LOAD_URL);
        }
        if (mAgentWeb == null && !TextUtils.isEmpty(url)) {
            mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                    .setAgentWebParent(mRelativeLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                    .useDefaultIndicator()// 使用默认进度条
                    .defaultProgressBarColor() // 使用默认进度条颜色
                    .setReceivedTitleCallback(this) //设置 Web 页面的 title 回调
                    .createAgentWeb().ready()
                    .go(url);
        }

    }

    @Override
    protected void onBackButtonEvent() {
        finish();
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        mToolbarTitle.setText(title);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAgentWeb.getWebLifeCycle().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAgentWeb.getWebLifeCycle().onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
