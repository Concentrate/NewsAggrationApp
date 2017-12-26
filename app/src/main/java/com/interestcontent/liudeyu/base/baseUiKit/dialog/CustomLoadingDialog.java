package com.interestcontent.liudeyu.base.baseUiKit.dialog;

/**
 * Created by liudeyu on 2017/12/26.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.interestcontent.liudeyu.R;


public class CustomLoadingDialog extends CustomProgressDialog {

    public CustomLoadingDialog(Context context) {
        super(context);
        setShowProgress(false);
    }

    public CustomLoadingDialog(Context context, int theme) {
        super(context, theme);
        setShowProgress(false);
    }

    @Override
    public void setMessage(CharSequence message) {
        super.setMessage(message);
        TextView textView = (TextView) findViewById(R.id.message);
        textView.setText(message);
    }

    public static CustomLoadingDialog show(Context context, String message) {
        CustomLoadingDialog mCommitDialog = new CustomLoadingDialog(context, ProgressDialog.THEME_HOLO_LIGHT);
        mCommitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mCommitDialog.setCancelable(false);
        mCommitDialog.setIndeterminate(false);
        mCommitDialog.setMax(100);
        //http://stackoverflow.com/questions/7811993/error-binderproxy45d459c0-is-not-valid-is-your-activity-running

        if(!((Activity) context).isFinishing()) {
            mCommitDialog.show();
            mCommitDialog.setContentView(R.layout.layout_custom_progressdialog);
            mCommitDialog.setMessage(message);
            mCommitDialog.rotate();
            return mCommitDialog;
        }
        return null;
    }

    public static CustomLoadingDialog showFullScreen(Context context, String message) {
        CustomLoadingDialog mCommitDialog = new CustomLoadingDialog(context, android.R.style.Theme);
        mCommitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mCommitDialog.setCancelable(false);
        mCommitDialog.setIndeterminate(false);
        mCommitDialog.setMax(100);
        //http://stackoverflow.com/questions/7811993/error-binderproxy45d459c0-is-not-valid-is-your-activity-running

        if(!((Activity) context).isFinishing()) {
            mCommitDialog.show();
            mCommitDialog.setContentView(R.layout.layout_custom_progressdialog);
            mCommitDialog.setMessage(message);
            mCommitDialog.rotate();
            return mCommitDialog;
        }
        return null;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        super.show();
        if (mAnimation != null) {
            rotate();
        }
    }
}
