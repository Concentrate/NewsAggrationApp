package com.interestcontent.liudeyu.weibo.component;

import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.interestcontent.liudeyu.base.constants.Constants;

import org.jsoup.nodes.Document;

/**
 * Created by liudeyu on 2018/2/2.
 */

public class NoAdWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dealWithLoadUrl(view, String.valueOf(request.getUrl()));
        }
        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        dealWithLoadUrl(view, url);
        return true;
    }

    private void dealWithLoadUrl(WebView view, String url) {
        if (url.startsWith(Constants.WEIBO_BASE_WEB_DOMAIN)) {
            Document document = new Document(url);
            document.removeClass("aside");


        } else {
            view.loadUrl(url);
        }
    }
}
