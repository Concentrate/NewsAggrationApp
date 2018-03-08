package com.interestcontent.liudeyu.base.utils;

import android.os.Environment;

import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;

import java.io.File;

/**
 * Created by liudeyu on 2018/1/22.
 */

public class FilePathUtils {
    static String TAG = "FilePathUtils";

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

    public static String getSaveImageFilePath() {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (file != null) {
            return file.getAbsolutePath();
        } else {
            return "";
        }
    }
}
