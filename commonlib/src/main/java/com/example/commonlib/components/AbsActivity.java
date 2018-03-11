package com.example.commonlib.components;

/**
 * Created by liudeyu on 2018/1/9.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.commonlib.R;
import com.example.commonlib.utils.Logger;

import java.util.HashSet;
import java.util.Set;


public abstract class AbsActivity extends AppCompatActivity implements IComponent {

    public static final String ACTION_EXIT_APP = "com.ss.android.common.app.action.exit_app";

    private static final String KEY = "abs_Activity_Key";
    protected boolean mStatusActive = false;
    protected boolean mStatusDestroyed = false;
    private WeakContainer<LifeCycleMonitor> mMonitors =
            new WeakContainer<LifeCycleMonitor>();

    private BroadcastReceiver mExitAppReceiver;

    private static volatile int sActivityId;
    private String mKey;

    private static Set<String> sActivitySet = new HashSet<String>();
    private static WeakContainer<AbsActivity> sFinishedActivityContainer = new WeakContainer<AbsActivity>();
    protected static int mActivityStartNum = 0;


    public static String getAliveActivitiesString() {
        if (sActivitySet == null || sActivitySet.isEmpty()) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (String activityString : sActivitySet) {
                if (index < (sActivitySet.size() - 1)) {
                    sb.append(activityString).append("|");
                } else {
                    sb.append(activityString);
                }
                index++;
            }
            return sb.toString();
        } catch (Throwable t) {
            // ignore
        }
        return "";
    }

    public static String getFinishedActivitiesString() {
        if (sFinishedActivityContainer == null || sFinishedActivityContainer.isEmpty()) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (AbsActivity absActivity : sFinishedActivityContainer) {
                if (absActivity != null &&
                        !sActivitySet.contains(absActivity.mKey) &&
                        absActivity.isFinishing()) {
                    if (index < (sFinishedActivityContainer.size() - 1)) {
                        sb.append(absActivity.mKey).append("|");
                    } else {
                        sb.append(absActivity.mKey);
                    }
                }
                index++;
            }
            return sb.toString();
        } catch (Throwable t) {
            // ignore
        }
        return "";
    }

    public static void onActivityCreate(AbsActivity activity) {
        if (activity != null) {
            try {
                sFinishedActivityContainer.add(activity);
                sActivitySet.add(activity.mKey);
            } catch (Throwable t) {
                // ignore
            }
        }
    }

    public static void onActivityDestroy(AbsActivity activity) {
        if (activity != null) {
            try {
                sActivitySet.remove(activity.mKey);
            } catch (Throwable t) {
                // ignore
            }
        }
    }

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


    /**
     * enable InitHook or not (enabled by default). SplashActivity should disable init hook and run init hook by
     * itself.
     */
    protected boolean enableInitHook() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY)) {
            mKey = savedInstanceState.getString(KEY);
        } else {
            mKey = this.getClass().getCanonicalName() + "@" + (sActivityId++);
        }
        mExitAppReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!isFinishing()) {
                    finish();
                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(mExitAppReceiver, new IntentFilter(ACTION_EXIT_APP));
        onActivityCreate(this);
    }

    protected boolean isUseFullAScreen() {
        return false;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    private void detectUiStyle() {
        if (isUseFullAScreen()) {
            setActivityFullScreen();
            return;
        }
        if (useImmerseMode()) {
            setStatusBarColor();
        }
    }

    private void setActivityFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }



    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    /**
     * 是否使用沉浸式
     */
    protected boolean useImmerseMode() {
        return true;
    }

    protected int getStatusBarColor() {
        return getResources().getColor(R.color.md_grey_700);
    }

    protected void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //使用标准的模式
            getWindow().setStatusBarColor(getStatusBarColor());
            return;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putString(KEY, mKey);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY)) {
            mKey = savedInstanceState.getString(KEY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStatusActive = true;
        setStatusBarColor();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActivityStartNum++;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActivityStartNum--;
        mStatusActive = false;

    }

    @Override
    protected void onPause() {
        super.onPause();
        mStatusActive = false;

    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mExitAppReceiver);
        super.onDestroy();
        mStatusDestroyed = true;
        if (!mMonitors.isEmpty()) {
            mMonitors.clear();
        }
        onActivityDestroy(this);
        if (Logger.debug()) {
            Logger.d("SS_OOM", "onDestroy FinishedActivities = " + getFinishedActivitiesString());
        }
    }

    @Override
    public boolean isActive() {
        return mStatusActive;
    }

    @Override
    public boolean isViewValid() {
        return !mStatusDestroyed;
    }

    @Override
    public void onBackPressed() {
        boolean isHandle = false;
        if (!mMonitors.isEmpty()) {
            for (LifeCycleMonitor m : mMonitors) {
                if (m != null) {
                    isHandle = m.onTopBackPressed();
                }
                if (isHandle) {
                    return;
                }
            }
        }
        if (!moveTaskToBack(false)) {
            super.onBackPressed();
        }
    }

}
