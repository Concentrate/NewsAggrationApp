package com.interestcontent.liudeyu.base.utils;

/**
 * Created by liudeyu on 2018/3/8.
 */

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * 权限请求时候
 */
public class MyPermissionDIalogRequetUtil {


    public static final int PERMISSION_WIRTE_EXTERNAL_STORAGE = 10;

    public static void requetPermission(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission}, PERMISSION_WIRTE_EXTERNAL_STORAGE
                );
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission}, PERMISSION_WIRTE_EXTERNAL_STORAGE
                );
            }
        }
    }

}
