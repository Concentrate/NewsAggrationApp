package com.interestcontent.liudeyu.contents.zhihu.contents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.commonlib.utils.Logger;
import com.google.gson.Gson;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.utils.PathUtils;
import com.interestcontent.liudeyu.contents.zhihu.bean.ZhihuContentRequest;
import com.interestcontent.liudeyu.contents.zhihu.util.HtmlUtil;
import com.just.agentweb.AgentWeb;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by liudeyu on 2018/2/9.
 */

public class ZhihuContentActivity extends BaseActivity {
    public static final String ZHIHU_JOURNAL_ID = "ZHIHU_JOURNAL_ID".toLowerCase();
    private static final String TAG = "ZhihuContentActivity".toLowerCase();
    private String mId;
    private ViewGroup mRootView;
    private AgentWeb mAgentWeb;

    public static void start(Context context, String id) {
        Intent starter = new Intent(context, ZhihuContentActivity.class);
        starter.putExtra(ZHIHU_JOURNAL_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected boolean isUseToolBar() {
        return false;
    }

    @Override
    protected View getResourceLayout() {
        mRootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_browse_layout, null);
        return mRootView;
    }


    @Override
    public boolean isImmersive() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            mId = intent.getStringExtra(ZHIHU_JOURNAL_ID);
            if (!TextUtils.isEmpty(mId)) {
                OkHttpUtils.get().url(Constants.ZHIHU_JOURNAL_CONTENT_BASE + mId).build().execute(new Callback<ZhihuContentRequest>() {

                    @Override
                    public ZhihuContentRequest parseNetworkResponse(Response response, int id) throws Exception {
                        Gson gson = new Gson();
                        ZhihuContentRequest request = gson.fromJson(response.body().string(), ZhihuContentRequest.class);
                        return request;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Logger.d(TAG, "error in the parse content");
                    }

                    @Override
                    public void onResponse(ZhihuContentRequest response, int id) {
                        initWebViewSetting(response);
                    }
                });
            }
        }

    }

    private void initWebViewSetting(ZhihuContentRequest response) {
        mAgentWeb = AgentWeb.with(this).setAgentWebParent(mRootView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)).useDefaultIndicator().defaultProgressBarColor()
                .createAgentWeb().ready().go(null);
        mAgentWeb.getAgentWebSettings().getWebSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        mAgentWeb.getAgentWebSettings().getWebSettings().setDatabaseEnabled(true);
        mAgentWeb.getAgentWebSettings().getWebSettings().setJavaScriptEnabled(true);
        boolean isOk = com.blankj.utilcode.util.FileUtils.createOrExistsDir(PathUtils.getWebViewCachePath());
        Logger.d("createDir", "create is ok " + isOk);
        mAgentWeb.getAgentWebSettings().getWebSettings().setAppCachePath(PathUtils.getWebViewCachePath());
        mAgentWeb.getAgentWebSettings().getWebSettings().setAppCacheEnabled(true);
        mAgentWeb.getLoader().loadData(HtmlUtil.createHtmlData(response.getBody(),
                response.getCss(),response.getJs()), HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
        mAgentWeb.getAgentWebSettings().getWebSettings().setSupportZoom(true);
        mAgentWeb.getWebCreator().get().setInitialScale(170);
    }

    @Override
    protected void onBackButtonEvent() {
        if (mAgentWeb != null && mAgentWeb.getWebCreator().get().canGoBack()) {
            mAgentWeb.getWebCreator().get().goBack();
            return;
        }
        super.onBackButtonEvent();
    }

    @Override
    public void onBackPressed() {
        if (mAgentWeb != null && mAgentWeb.getWebCreator().get().canGoBack()) {
            mAgentWeb.getWebCreator().get().goBack();
            return;
        }
        super.onBackPressed();
    }
}
