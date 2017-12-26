package com.interestcontent.liudeyu.base.baseUiKit.dialog;

/**
 * Created by liudeyu on 2017/12/26.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.interestcontent.liudeyu.R;


public class CustomProgressDialog extends ProgressDialog {
    private boolean mShowProgress = true;
    private TextView mProgressView;

    public void setShowProgress(boolean showProgress) {
        mShowProgress = showProgress;
    }

    public CustomProgressDialog(Context context) {
        super(context);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
    }

    @Override
    public void setProgress(int value) {
        super.setProgress(value);
        if (mProgressView == null) {
            mProgressView = (TextView) findViewById(R.id.progress);
        }
        mProgressView.setText(value + "%");
    }

    @Override
    public void setMessage(CharSequence message) {
        super.setMessage(message);
        TextView textView = (TextView) findViewById(R.id.message);
        textView.setText(message);
    }

    public static CustomProgressDialog show(Context context, String message) {
        CustomProgressDialog mCommitDialog = new CustomProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);
        mCommitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mCommitDialog.setCancelable(false);
        mCommitDialog.setIndeterminate(false);
        mCommitDialog.setMax(100);
        if (context instanceof Activity) {
            if (context != null && !((Activity) context).isFinishing()) {
                mCommitDialog.show();
            }
        }
        mCommitDialog.setContentView(R.layout.layout_custom_progressdialog);
        mCommitDialog.setMessage(message);
        mCommitDialog.rotate();
        return mCommitDialog;
    }

    public static CustomProgressDialog show(Context context, String message, boolean cancelable) {
        CustomProgressDialog mCommitDialog = new CustomProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);
        mCommitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mCommitDialog.setCancelable(cancelable);
        mCommitDialog.setIndeterminate(false);
        mCommitDialog.setMax(100);
        if (context instanceof Activity) {
            if (context != null && !((Activity) context).isFinishing()) {
                mCommitDialog.show();
            }
        }
        mCommitDialog.setContentView(R.layout.layout_custom_progressdialog);
        mCommitDialog.setMessage(message);
        mCommitDialog.rotate();
        return mCommitDialog;
    }

    protected Animation mAnimation;

    protected void rotate() {
        if (mAnimation == null) {
            mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.loading_dialog_animation);
        }
        findViewById(R.id.iv_loading).startAnimation(mAnimation);

        if (mShowProgress) {
            findViewById(R.id.progress).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.progress).setVisibility(View.INVISIBLE);
        }
    }
}
