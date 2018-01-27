package com.interestcontent.liudeyu.weibo.component;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.RelativeLayout;

import com.example.commonlib.components.AbsActivity;
import com.example.commonlib.components.AbsFragment;
import com.example.commonlib.components.LifeCycleMonitor;
import com.interestcontent.liudeyu.MainActivity;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.AbsTopTabFragment;
import com.interestcontent.liudeyu.base.constants.FileConstants;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;

import java.io.File;

/**
 * Created by liudeyu on 2018/1/24.
 */

public class BaseWebBrowseFragment extends AbsFragment implements LifeCycleMonitor, ObservableWebView.OnScrollChangedCallback {

    public static final String LOAD_URL = "LOAD_URL".toLowerCase();
    private final String TAG = this.getClass().getSimpleName();
    protected AgentWeb mAgentWeb;
    private RelativeLayout mRelativeLayout;
    private boolean isWebInit = false;
    private ObservableWebView mWebView;
    private boolean lastDirectionDown = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRelativeLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.fragment_browse_layout, null);
        return mRelativeLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AgentWeb.AgentBuilderFragment builder = AgentWeb.with(this);//传入Activity or Fragment
        AgentWeb.CommonBuilderForFragment commonBuilderForFragment = builder.setAgentWebParent(mRelativeLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator();
        if (getActivity() instanceof ChromeClientCallbackManager.ReceivedTitleCallback) {
            commonBuilderForFragment.setReceivedTitleCallback((ChromeClientCallbackManager.ReceivedTitleCallback) getActivity());
        }
        mWebView = new ObservableWebView(getContext());
        mWebView.setOnScrollChangedCallback(this);
        commonBuilderForFragment.setWebView(mWebView);
        mAgentWeb = commonBuilderForFragment.createAgentWeb().ready().go(null);
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
        Fragment fragment = getParentFragment();
        if (fragment != null && fragment instanceof AbsTopTabFragment) {
            ((AbsTopTabFragment) fragment).registerLifeCycleMonitor(this);
        }
        Activity activity = getActivity();
        if (activity instanceof AbsActivity) {
            ((AbsActivity) activity).registerLifeCycleMonitor(this);
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mAgentWeb == null) {
            return;
        }
        loadFirstPageUrl(isVisibleToUser, true);
    }


    private void loadFirstPageUrl(boolean isVisible, boolean isResume) {
        if (!isVisible || !isResume) {
            return;
        }
        if (isWebInit) {
            return;
        }
        String passUrl = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            passUrl = bundle.getString(LOAD_URL);
        }
        String requestUrl = provideUrl();
//        传递url优先级比本身写死url高
        if (!TextUtils.isEmpty(passUrl)) {
            requestUrl = passUrl;
        }
        if (!TextUtils.isEmpty(requestUrl) && !isWebInit) {
            mAgentWeb.getLoader().loadUrl(requestUrl);
            isWebInit = true;
        }
    }

    protected String provideUrl() {
        return null;
    }


    @Override
    public void onResume() {
        super.onResume();
        loadFirstPageUrl(getUserVisibleHint(), true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Fragment fragment = getParentFragment();
        if (fragment != null && fragment instanceof AbsTopTabFragment) {
            ((AbsTopTabFragment) fragment).unregisterLifeCycleMonitor(this);
        }
        Activity activity = getActivity();
        if (activity instanceof AbsActivity) {
            ((AbsActivity) activity).unregisterLifeCycleMonitor(this);
        }
        mAgentWeb.getWebLifeCycle().onDestroy();
    }


    @Override
    public boolean onTopBackPressed() {
        if (mAgentWeb.getWebCreator().get().canGoBack()) {
            mAgentWeb.getWebCreator().get().goBack();
            return true;
        }
        return false;
    }

    @Override
    public void onTopFragmentUserVisibleHint(boolean visible) {

    }

    @Override
    public void onScroll(int dx, int dy) {
        // 说明下滑
        int touchMinum = ViewConfiguration.get(getContext()).getScaledTouchSlop() * 4;
        int touchMaxum = (int) (ViewConfiguration.get(getContext()).getScaledPagingTouchSlop() * 5.5);

        if (dy > touchMinum && dy < touchMaxum) {
            if (!lastDirectionDown) {
                showTopBarAndBottomBar(false);
            }
            lastDirectionDown = true;
        } else if (dy < -touchMinum && dy > -touchMaxum) {
            if (lastDirectionDown) {
                showTopBarAndBottomBar(true);
            }
            lastDirectionDown = false;
        }

    }

    private void showTopBarAndBottomBar(boolean b) {
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).setTabLayoutVisible(b ? View.VISIBLE : View.GONE);
        }
        Fragment fragment = getParentFragment();
        if (fragment instanceof AbsTopTabFragment) {
            ((AbsTopTabFragment) fragment).setTabLayoutVisible(b ? View.VISIBLE : View.GONE);
        }
    }
}
