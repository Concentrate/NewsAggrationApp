package com.interestcontent.liudeyu.contents.weibo.component;

import android.content.Context;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;

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
            return Glide.with(context)
                    .load(imgUrl)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
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
        if(mFilePathCallback!=null){
            mFilePathCallback.fileCachePath(path);
        }
    }

    public static interface FilePathCallback {
        void fileCachePath(String path);
    }
}