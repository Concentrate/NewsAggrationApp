package com.interestcontent.liudeyu.base;

/**
 * Created by liudeyu on 2017/12/23.
 */

public interface LifeCycleMonitor {
    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    class Stub implements LifeCycleMonitor {

        @Override
        public void onResume() {
        }

        @Override
        public void onPause() {
        }

        @Override
        public void onStop() {
        }

        @Override
        public void onDestroy() {
        }
    }
}
