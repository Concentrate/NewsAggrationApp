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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.base.baseComponent.BaseWebBrowseFragment;
import com.interestcontent.liudeyu.base.baseComponent.BrowseFragmentRemoveAd;
import com.just.agentweb.ChromeClientCallbackManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowseActivity extends BaseActivity implements ChromeClientCallbackManager.ReceivedTitleCallback {

    public static final String LOAD_URL = "LOAD_URL".toLowerCase();
    public static final String USE_TOOL_BAR = "USE_TOOL_BAR";//是否使用状态栏


    @BindView(R.id.parent_container)
    FrameLayout rootContainer;
    private String mUrl;


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
        mUrl = "";
        boolean useToolbar = true;
        if (intent != null) {
            mUrl = intent.getStringExtra(LOAD_URL);
            useToolbar = intent.getBooleanExtra(USE_TOOL_BAR, false);
            if (!useToolbar) {
                mToolbar.setVisibility(View.GONE);
            }


        }
        if (!TextUtils.isEmpty(mUrl)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString(BaseWebBrowseFragment.LOAD_URL, mUrl);
            Fragment fragment = new BrowseFragmentRemoveAd();
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.parent_container, fragment)
                    .commitAllowingStateLoss();
        }
        initToolbarViews();
    }

    private void initToolbarViews() {
        if (mToolbar.getVisibility() == View.VISIBLE) {
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
                    String des = "这有个新闻，我想让你看看, ";
                    intent.putExtra(Intent.EXTRA_SUBJECT, des);//添加分享内容标题
                    intent.putExtra(Intent.EXTRA_TEXT, des + mUrl);//添加分享内容
                    Intent shareIntent = Intent.createChooser(intent, "选择分享方式");
                    BrowseActivity.this.startActivity(shareIntent);
                }
            });

        }
    }

    @Override
    protected void onBackButtonEvent() {
        super.onBackButtonEvent();
        onBackPressed();
    }


    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (mToolbar.getVisibility() != View.GONE) {
            String tmp = title;
            mToolbarTitle.setText(tmp);
        }
    }

}
