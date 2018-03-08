package com.interestcontent.liudeyu.base.utils;

import android.content.Context;
import android.content.Intent;

import com.interestcontent.liudeyu.MainActivity;

/**
 * Created by liudeyu on 2018/3/8.
 */

public class AppRestartUtil {


    /**
     * 不杀进程
     */
    public static void restartAppWithoutKillProcess(Context context) {
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 重新启动App -> 杀进程,会短暂黑屏,启动慢
     */
    public static void restartAppWithKillProcess(Context context) {
        //启动页
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
