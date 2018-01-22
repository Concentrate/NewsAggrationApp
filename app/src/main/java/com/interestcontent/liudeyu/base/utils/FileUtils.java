package com.interestcontent.liudeyu.base.utils;

import com.interestcontent.liudeyu.base.baseComponent.MyApplication;

/**
 * Created by liudeyu on 2018/1/22.
 */

public class FileUtils {
    public static String getExternalCacheFileDir() {
        return MyApplication.sApplication.getExternalCacheDir().getAbsolutePath();
    }
}
