package com.interestcontent.liudeyu.weibo.contents;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.weibo.component.BaseWebBrowseFragment;

/**
 * Created by liudeyu on 2018/1/27.
 */

public class WebContentBrowseFragment extends BaseWebBrowseFragment {


    //    去除某些前端页面标签的函数，js函数需自己实现
    public static final String DEPRECATED_WEB_Class_FUN = "DEPRECATED_WEB_Class_FUN";
    private String jsDeprecatedClassFun = "";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            jsDeprecatedClassFun = bundle.getString(DEPRECATED_WEB_Class_FUN);
            if (!TextUtils.isEmpty(jsDeprecatedClassFun)) {
                deprecatedWebTag();
            }
        }
    }

    private void deprecatedWebTag() {

        mAgentWeb.getWebCreator().get().setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress>=99){
                    depracatedSomeClassTag(view, view.getUrl());
                }
            }
        });
    }

    private void depracatedSomeClassTag(WebView view, String url) {
        if (url.contains(Constants.WEIBO_BASE_WEB_DOMAIN)) {
            if (Build.VERSION.SDK_INT < 19) {
                view.loadUrl("javaScript:" + jsDeprecatedClassFun);
            } else {
                Logger.d("WebContentBrowseFragment", "js execute is " + "javascript:" + jsDeprecatedClassFun);
                view.evaluateJavascript("javascript:" + jsDeprecatedClassFun, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //此处为 js 返回的结果
                        Logger.d("WebContentBrowseFragment", "删除 微博 tag 返回 " + value);
                    }
                });
            }
        }
    }


    @Override
    protected String provideUrl() {
        return null;
    }
}
