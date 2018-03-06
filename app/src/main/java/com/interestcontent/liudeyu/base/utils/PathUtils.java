package com.interestcontent.liudeyu.base.utils;

import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;

import java.io.File;

/**
 * Created by liudeyu on 2018/1/22.
 */

public class PathUtils {
    static String TAG = "PathUtils";

    public static String getExternalCacheFileDir() {
        File file = MyApplication.sApplication.getExternalCacheDir();
        if (file == null) {
            file = MyApplication.sApplication.getCacheDir();
        }
        return file.getAbsolutePath();
    }


    public static String getWebViewCachePath() {
        String path = getExternalCacheFileDir() + File.separator + "webViewCache";
        Logger.d(TAG, String.format("webview cache path is %s", path));
        return path;
    }
}
