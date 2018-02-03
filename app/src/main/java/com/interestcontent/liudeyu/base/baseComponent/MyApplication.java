package com.interestcontent.liudeyu.base.baseComponent;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.interestcontent.liudeyu.BuildConfig;
import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.base.thread.TaskManager;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.Executors;
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
        Utils.init(this);
        TaskManager.inst().init(new TaskManager.TaskManagerConfig().setExecutor(Executors.newFixedThreadPool(4)));
    }
}
