package com.interestcontent.liudeyu.weibo.contents;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.sina.weibo.sdk.web.WebViewRequestCallback;
import com.sina.weibo.sdk.web.client.AuthWebViewClient;
import com.sina.weibo.sdk.web.client.BaseWebViewClient;
import com.sina.weibo.sdk.web.client.DefaultWebViewClient;
import com.sina.weibo.sdk.web.client.ShareWebViewClient;
import com.sina.weibo.sdk.web.param.AuthWebViewRequestParam;
import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam;
import com.sina.weibo.sdk.web.param.DefaultWebViewRequestParam;
import com.sina.weibo.sdk.web.param.ShareWebViewRequestParam;
import com.sina.weibo.sdk.web.view.LoadingBar;

/**
 * Created by liudeyu on 2018/1/24.
 */

public class WeiboContentBrowseActivity extends BrowseActivity implements WebViewRequestCallback {

    private static final String CANCEL_EN = "Close";
    private static final String CANCEL_ZH_CN = "关闭";
    private static final String CANCEL_ZH_TW = "关闭";
    private static final String EMPTY_PROMPT_BAD_NETWORK_UI_EN = "A network error occurs, please tap the button to reload";
    private static final String EMPTY_PROMPT_BAD_NETWORK_UI_ZH_CN = "网络出错啦，请点击按钮重新加载";
    private static final String EMPTY_PROMPT_BAD_NETWORK_UI_ZH_TW = "網路出錯啦，請點擊按鈕重新載入";
    private static final String CHANNEL_DATA_ERROR_EN = "channel_data_error";
    private static final String CHANNEL_DATA_ERROR_ZH_CN = "重新加载";
    private static final String CHANNEL_DATA_ERROR_ZH_TW = "重新載入";
    private static final String WEIBOBROWSER_NO_TITLE_EN = "No Title";
    private static final String WEIBOBROWSER_NO_TITLE_ZH_CN = "无标题";
    private static final String WEIBOBROWSER_NO_TITLE_ZH_TW = "無標題";
    private static final String LOADINFO_EN = "Loading....";
    private static final String LOADINFO_ZH_CN = "加载中....";
    private static final String LOADINFO_ZH_TW = "載入中....";
    public static final String BROWSER_CLOSE_SCHEME = "sinaweibo://browser/close";
    private TextView leftBtn;
    private TextView titleText;
    private LoadingBar loadingBar;
    private Button retryBtn;
    private TextView retryTitle;
    private LinearLayout retryLayout;
    private BaseWebViewRequestParam baseParam;
    private BaseWebViewClient webViewClient;
    private int pageStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoad();

    }

    private void initLoad() {
        Bundle bundle = this.getIntent().getExtras();
        int type = bundle.getInt("type", -1);
        if (type == -1) {
            this.finish();
        } else {
            switch (type) {
                case 0:
                    this.baseParam = new DefaultWebViewRequestParam();
                    this.webViewClient = new DefaultWebViewClient(this, this.baseParam);
                    break;
                case 1:
                    this.baseParam = new ShareWebViewRequestParam(this);
                    this.webViewClient = new ShareWebViewClient(this, this, this.baseParam);
                    break;
                case 2:
                    this.baseParam = new AuthWebViewRequestParam();
                    this.webViewClient = new AuthWebViewClient(this, this, this.baseParam);
            }

            this.baseParam.transformBundle(bundle);
            if (this.baseParam.hasExtraTask()) {
                this.baseParam.doExtraTask(new BaseWebViewRequestParam.ExtraTaskCallback() {
                    public void onComplete(String urlTask) {
                        mAgentWeb.getLoader().loadUrl(baseParam.getRequestUrl());

                    }

                    public void onException(String errorMsg) {
                    }
                });
            } else {
                String url = this.baseParam.getRequestUrl();
                mAgentWeb.getLoader().loadUrl(url);
            }

        }
    }

    @Override
    protected boolean useImmerseMode() {
        return true;
    }

    @Override
    protected boolean isUseFullAScreenAndTransparent() {
        return true;
    }

    private void showErrorPage() {
        // TODO: 2018/1/24 替换
    }

    private void showDefaultPage() {
        // TODO: 2018/1/24 替换
    }

    public void onPageStartedCallBack(WebView view, String url, Bitmap favicon) {
    }

    public void onPageFinishedCallBack(WebView view, String url) {
        if (this.pageStatus == -1) {
            this.showErrorPage();
        } else {
            this.showDefaultPage();
        }

    }

    public boolean shouldOverrideUrlLoadingCallBack(WebView view, String url) {
        return false;
    }

    public void onReceivedErrorCallBack(WebView view, int errorCode, String description, String failingUrl) {
        String url = view.getUrl();

        try {
            if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(failingUrl)) {
                Uri loadUri = Uri.parse(url);
                Uri failUri = Uri.parse(failingUrl);
                if (loadUri.getHost().equals(failUri.getHost()) && loadUri.getScheme().equals(failUri.getScheme())) {
                    this.pageStatus = -1;
                }
            }
        } catch (Exception var8) {
            ;
        }

    }

    public void onReceivedSslErrorCallBack(WebView view, final SslErrorHandler handler, SslError error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage("你访问的连接可能存在隐患，是否继续访问");
        builder.setPositiveButton("继续", new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                handler.proceed();
            }
        });
        builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                handler.cancel();
            }
        });
        builder.create().show();
    }

    public void closePage() {
        this.finish();
    }

}
