package com.interestcontent.liudeyu.weibo.feeds;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.thread.TaskManager;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.weibo.data.WeiboLoginManager;
import com.interestcontent.liudeyu.weibo.util.MyWeiboPageUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.Callable;

/**
 * Created by liudeyu on 2018/1/23.
 */
public class OnWeiboOperationBottomClickListener implements View.OnClickListener {


    public void setNecesseryViewTag(String weiboId, View... list) {
        if (list == null || list.length == 0) {
            return;
        }
        for (View view : list) {
            view.setTag(R.layout.weibo_feed_cell_layout, weiboId);
        }
    }

    @Override
    public void onClick(View view) {
        ImageView needAnimationView = null;
        TextView viewCountTv = null;
        boolean isJustAdd = false;
        switch (view.getId()) {
            case R.id.good_fingger_layout:
                needAnimationView = view.findViewById(R.id.good_finger_iv);
                viewCountTv = view.findViewById(R.id.good_fingger_count_tv);
                dealWithAttitudeUpload(!needAnimationView.isSelected(), (String) view.getTag(R.layout.weibo_feed_cell_layout));
                break;
            case R.id.resend_layout:
                needAnimationView = view.findViewById(R.id.resend_iv);
                viewCountTv = view.findViewById(R.id.resend_count_tv);
                isJustAdd = true;
                dealWithResendShare((String) view.getTag(R.layout.weibo_feed_cell_layout));
                break;
            case R.id.comment_layout:
                needAnimationView = view.findViewById(R.id.comment_iv);
                viewCountTv = view.findViewById(R.id.comment_count_tv);
                dealWithGotoComment((String) view.getTag(R.layout.weibo_feed_cell_layout));
                isJustAdd = true;
                break;
        }
        if (needAnimationView == null) {
            return;
        }
        needAnimationView.setSelected(!needAnimationView.isSelected());
        try {
            int num = TextUtils.isEmpty(viewCountTv.getText().toString()) ? 0 : Integer.parseInt(viewCountTv.getText().toString());
            if (isJustAdd) {
                viewCountTv.setText(String.valueOf(num + 1));
            } else {
                viewCountTv.setText(String.valueOf(num + (needAnimationView.isSelected() ? 1 : -1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (needAnimationView.isSelected()) {
            showEnlargeAnimation(needAnimationView);
        }

    }

    private void dealWithResendShare(String content) {
        if (!TextUtils.isEmpty(content)) {
            MyWeiboPageUtils.getInstance(MyApplication.sApplication, WeiboLoginManager.getInstance().getAuthInfo())
                    .repostWeiboContent(content);
        }
    }

    private void dealWithGotoComment(@NonNull String id) {
        MyWeiboPageUtils.getInstance(MyApplication.sApplication, WeiboLoginManager.getInstance().getAuthInfo())
                .commentWeibo(id, true);
    }

    private void dealWithAttitudeUpload(boolean b, final String id) {
        final String url = b ? Constants.WEIBO_GOOD_ATTITUDE : Constants.WEIBO_NEGETIVE_ATTITUDE;
        TaskManager.inst().commit(new Callable() {
            @Override
            public Object call() throws Exception {
                OkHttpUtils.post().url(url).addParams(Constants.WB_REQUEST_PARAMETER.ACCESS_TOKEN, SharePreferenceUtil.getStringPreference(MyApplication.sApplication,
                        SpConstants.WEIBO_AUTHEN_TOKEN)).addParams(Constants.WB_REQUEST_PARAMETER.ATTITUDE, "simle").addParams(Constants.WB_REQUEST_PARAMETER.ID,
                        id).build().execute();
                return null;
            }
        });
    }

    private void showEnlargeAnimation(final ImageView needAnimationView) {
        needAnimationView.animate().scaleX(1.5f).scaleY(1.5f).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {
                needAnimationView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500).start();
            }
        });
    }
}
