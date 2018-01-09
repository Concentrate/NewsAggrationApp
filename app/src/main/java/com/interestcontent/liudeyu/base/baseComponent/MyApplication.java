package com.interestcontent.liudeyu.base.baseComponent;

import android.app.Application;
import android.util.Log;

import com.interestcontent.liudeyu.BuildConfig;
import com.interestcontent.liudeyu.base.utils.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

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
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10*1000L, TimeUnit.MILLISECONDS)
                .readTimeout(60*1000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
