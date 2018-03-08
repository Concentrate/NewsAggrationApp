package com.interestcontent.liudeyu.contents.weibo.component;

import android.content.Context;
import android.os.AsyncTask;

import com.blankj.utilcode.util.FileUtils;
import com.interestcontent.liudeyu.base.utils.FilePathUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by liudeyu on 2018/1/16.
 */
public class GetImageCacheTask extends AsyncTask<String, Void, File> {
    private final Context context;
    private FilePathCallback mFilePathCallback;

    public GetImageCacheTask(Context context) {
        this.context = context;
    }

    public GetImageCacheTask(Context context, FilePathCallback filePathCallback) {
        this.context = context;
        mFilePathCallback = filePathCallback;
    }

    @Override
    protected File doInBackground(String... params) {
        String imgUrl = params[0];
        try {
            String cacheFileName = FilePathUtils.getPicCacheFileDir() + File.separator + imgUrl.hashCode();
            if (FileUtils.isFile(cacheFileName) && FileUtils.isFileExists(cacheFileName)) {
                return new File(cacheFileName);
            }
            byte[] tmp = OkHttpUtils.get().url(imgUrl).build().execute().body().bytes();
            FileOutputStream outputStream = new FileOutputStream(cacheFileName);
            outputStream.write(tmp);
            outputStream.close();
            return new File(cacheFileName);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(File result) {
        if (result == null) {
            return;
        }
        //此path就是对应文件的缓存路径
        String path = result.getPath();
        //将缓存文件copy, 命名为图片格式文件
        if (mFilePathCallback != null) {
            mFilePathCallback.fileCachePath(path);
        }
    }

    public static interface FilePathCallback {
        void fileCachePath(String path);
    }
}