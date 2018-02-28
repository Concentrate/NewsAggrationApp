package com.interestcontent.liudeyu.base.utils;

/**
 * Created by liudeyu on 2018/2/28.
 */

import com.example.commonlib.utils.Logger;

/**
 * 根据内存大小进行应用内存优化设置
 */
public class RamUtil {
    private static final String TAG = "RamUtil";
    private static int sMaxMemory;

    public static void LoogerMemoery() {
        //应用程序最大可用内存
        sMaxMemory = ((int) Runtime.getRuntime().maxMemory()) / 1024 / 1024;
        //应用程序已获得内存
        long totalMemory = ((int) Runtime.getRuntime().totalMemory()) / 1024 / 1024;
        //应用程序已获得内存中未使用内存
        long freeMemory = ((int) Runtime.getRuntime().freeMemory()) / 1024 / 1024;
        Logger.d(TAG, String.format("最大可用内存 %d m,已获取内存 %d m,内存中未使用内存 %d m", sMaxMemory, totalMemory, freeMemory));
    }


    /**
     * 单位 m
     */
    public static int getMaxMemoryCanGet() {
        if (sMaxMemory == 0) {
            sMaxMemory = ((int) Runtime.getRuntime().maxMemory()) / 1024 / 1024;
        }
        return sMaxMemory;
    }

}
