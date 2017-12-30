package com.interestcontent.liudeyu.base.baseComponent;

import android.app.Application;

/**
 * Created by liudeyu on 2017/12/30.
 */

public class MyApplication extends Application {

    public static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

    }
}
