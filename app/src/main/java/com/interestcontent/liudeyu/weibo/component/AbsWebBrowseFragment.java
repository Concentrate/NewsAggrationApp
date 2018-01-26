package com.interestcontent.liudeyu.weibo.component;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.example.commonlib.components.AbsFragment;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.FileConstants;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;

import java.io.File;

/**
 * Created by liudeyu on 2018/1/24.
 */

public abstract class AbsWebBrowseFragment extends AbsFragment implements ChromeClientCallbackManager.ReceivedTitleCallback {

    private final String TAG = this.getClass().getSimpleName();
    protected AgentWeb mAgentWeb;
    private RelativeLayout mRelativeLayout;
    private boolean isWebInit = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRelativeLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.activity_browse, null);
        return mRelativeLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AgentWeb.AgentBuilderFragment builder = AgentWeb.with(this);//传入Activity or Fragment
        mAgentWeb = builder.setAgentWebParent(mRelativeLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .setReceivedTitleCallback(this)//设置 Web 页面的 title 回调
                .createAgentWeb().ready().go(null);
        mAgentWeb.getAgentWebSettings().getWebSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mAgentWeb.getAgentWebSettings().getWebSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        mAgentWeb.getAgentWebSettings().getWebSettings().setDatabaseEnabled(true);

        String cacheDirPath = getActivity().getFilesDir().getAbsolutePath() + File.separator + FileConstants.WEB_CACHE_DIR;
        Log.d(TAG, "web view cache path is " + cacheDirPath);
        //设置数据库缓存路径
        //设置  Application Caches 缓存目录
        mAgentWeb.getAgentWebSettings().getWebSettings().setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        mAgentWeb.getAgentWebSettings().getWebSettings().setAppCacheEnabled(true);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mAgentWeb == null) {
            return;
        }
        if (!TextUtils.isEmpty(provideUrl()) && !isWebInit) {
            mAgentWeb.getLoader().loadUrl(provideUrl());
            isWebInit = true;
        }
    }

    abstract protected String provideUrl();

    @Override
    public void onStop() {
        super.onStop();
        mAgentWeb.getWebLifeCycle().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(provideUrl()) && !isWebInit && getUserVisibleHint()) {
            mAgentWeb.getLoader().loadUrl(provideUrl());
            isWebInit = true;
        }
        mAgentWeb.getWebLifeCycle().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mAgentWeb.getWebLifeCycle().onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }


    @Override
    public void onReceivedTitle(WebView view, String title) {

    }

    @Override
    public boolean onBackPressed() {
        if (mAgentWeb.getWebCreator().get().canGoBack()) {
            mAgentWeb.getWebCreator().get().goBack();
            return true;
        }
        return false;
    }
}
