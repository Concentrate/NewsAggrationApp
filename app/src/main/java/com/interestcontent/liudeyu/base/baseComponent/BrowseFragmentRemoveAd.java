package com.interestcontent.liudeyu.base.baseComponent;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.just.agentweb.ChromeClientCallbackManager;

/**
 * Created by liudeyu on 2018/1/27.
 */

public class BrowseFragmentRemoveAd extends BaseWebBrowseFragment {


    private RemoveWebAdTagControler mRemoveWebAdTagControler;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        deprecatedWebTag();
    }

    private void deprecatedWebTag() {
        mAgentWeb.getWebCreator().get().setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 100) {
                    depracatedSomeClassTag(view, view.getUrl());
                }
            }
        });
        mAgentWeb.getWebCreator().get().setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (getActivity() instanceof ChromeClientCallbackManager.ReceivedTitleCallback) {
                    ((ChromeClientCallbackManager.ReceivedTitleCallback) getActivity()).onReceivedTitle(view, view.getTitle());
                }
            }
        });
    }

    private void depracatedSomeClassTag(WebView view, String url) {
        if (mRemoveWebAdTagControler == null) {
            mRemoveWebAdTagControler = new RemoveWebAdTagControler();
        }
        mRemoveWebAdTagControler.removeFrontEndAd(view, url);
    }


    @Override
    protected String provideUrl() {
        return null;
    }
}
