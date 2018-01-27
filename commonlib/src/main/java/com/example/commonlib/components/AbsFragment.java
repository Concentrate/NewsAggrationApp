package com.example.commonlib.components;

/**
 * Created by liudeyu on 2017/12/23.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Base class of fragment.
 */
public abstract class AbsFragment extends Fragment implements IComponent {

    protected boolean mStatusActive;
    protected boolean mStatusViewValid;
    protected boolean mStatusDestroyed;
    //监听上层生命周期变化的需注册
    private WeakContainer<LifeCycleMonitor> mMonitors =
            new WeakContainer<LifeCycleMonitor>();

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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!mMonitors.isEmpty()) {
            for (LifeCycleMonitor m : mMonitors) {
                if (m != null)
                    m.onTopFragmentUserVisibleHint(isVisibleToUser);
            }
        }
    }

    @Override
    public boolean isActive() {
        return mStatusActive;
    }

    @Override
    public boolean isViewValid() {
        return mStatusViewValid;
    }

    public boolean isDestroyed() {
        return mStatusDestroyed;
    }

}
