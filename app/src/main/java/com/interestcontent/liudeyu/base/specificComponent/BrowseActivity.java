package com.interestcontent.liudeyu.base.specificComponent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.weibo.component.BaseWebBrowseFragment;
import com.interestcontent.liudeyu.weibo.contents.WeiboContentBrowseFragment;
import com.just.agentweb.ChromeClientCallbackManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowseActivity extends BaseActivity implements ChromeClientCallbackManager.ReceivedTitleCallback {

    public static final String LOAD_URL = "LOAD_URL".toLowerCase();
    public static final String USE_TOOL_BAR = "USE_TOOL_BAR";//是否使用状态栏

    @BindView(R.id.parent_container)
    FrameLayout rootContainer;


    public static Intent getIntent(String loadUrl, boolean useToolBar) {
        Intent intent = new Intent();
        intent.putExtra(LOAD_URL, loadUrl);
        intent.putExtra(USE_TOOL_BAR, useToolBar);
        return intent;
    }

    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.activity_browse_layout, null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = "";
        boolean useToolbar = true;
        if (intent != null) {
            url = intent.getStringExtra(LOAD_URL);
            useToolbar = intent.getBooleanExtra(USE_TOOL_BAR, false);
            if (!useToolbar) {
                mToolbar.setVisibility(View.GONE);
            }
        }
        if (!TextUtils.isEmpty(url)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString(BaseWebBrowseFragment.LOAD_URL, url);
            Fragment fragment = new WeiboContentBrowseFragment();
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.parent_container, fragment).commit();
        }
    }


    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (mToolbar.getVisibility() != View.GONE) {
            mToolbarTitle.setText(title);
        }
    }

}
