package com.interestcontent.liudeyu.base.baseComponent;

import android.app.Application;
import android.util.Log;

import com.interestcontent.liudeyu.BuildConfig;
import com.interestcontent.liudeyu.base.utils.Logger;

/**
 * Created by liudeyu on 2017/12/30.
 */

public class MyApplication extends Application {

    public static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        if(!BuildConfig.DEBUG){
            Logger.setLogLevel(Log.ASSERT+1);
        }

    }
}
