package com.interestcontent.liudeyu.base.baseComponent;

/**
 * Created by liudeyu on 2017/12/26.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.commonlib.components.LifeCycleMonitor;
import com.example.commonlib.components.WeakContainer;

public class BaseDialogFragment extends DialogFragment {

    protected boolean mStatusActive;
    protected boolean mStatusViewValid;
    protected boolean mStatusDestroyed;
    protected boolean mSoftKeyIsShow;

    private WeakContainer<LifeCycleMonitor> mMonitors = new WeakContainer<>();

    /**
     * register a LifeCycleMonitor
     */
    public void registerLifeCycleMonitor(LifeCycleMonitor m) {
        mMonitors.add(m);
    }

    /**
     * unregister a LifeCycleMonitor
     */
    public void unregisterLifeCycleMonitor(LifeCycleMonitor m) {
        mMonitors.remove(m);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStatusActive = false;
        mStatusViewValid = false;
        mStatusDestroyed = false;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStatusViewValid = true;
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // 事件会触发action_down 和 action_up，所以要过滤一下。
                if (KeyEvent.KEYCODE_BACK == keyCode && KeyEvent.ACTION_UP == event.getAction()) {
                    onBackPressed();
                    return false;
                }
                return false;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null)
            outState.putBoolean("WORKAROUND_FOR_BUG_19917_KEY", true);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mStatusActive = true;

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();
        mStatusActive = false;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mStatusViewValid = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mStatusViewValid = false;
        mStatusDestroyed = true;
        if (!mMonitors.isEmpty()) {
            mMonitors.clear();
        }
    }

    public boolean isActive() {
        return mStatusActive;
    }

    public boolean isViewValid() {
        return mStatusViewValid;
    }

    public boolean isDestroyed() {
        return mStatusDestroyed;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        try {
            dismiss();
        } catch (IllegalStateException e) {
            dismissAllowingStateLoss();
        }
    }

    protected void hideIme(View view, int windowMarginBottom) {
        if (view == null || getActivity() == null) return;
        InputMethodManager ime = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        ime.hideSoftInputFromWindow(view.getWindowToken(), 0);
        setUpWindowParams(windowMarginBottom);
        mSoftKeyIsShow = false;
    }

    protected void showIme(final View view) {
        if (view == null) return;
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getActivity() == null) return;
                view.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
                setUpWindowParams(0);
                mSoftKeyIsShow = true;
            }
        }, 100);
    }

    //每次弹出收起软键盘时对dialog的处理
    protected void setUpWindowParams(int windownMarginBottom) {

    }

    public static void show(FragmentActivity fragmentActivity, BaseDialogFragment baseDialogFragment) {
        if (fragmentActivity == null || baseDialogFragment == null) {
            return;
        }
        baseDialogFragment.show(fragmentActivity.getSupportFragmentManager(), baseDialogFragment.getClass().getCanonicalName());
    }

    protected boolean onBackPressed() {
        return false;
    }
}
