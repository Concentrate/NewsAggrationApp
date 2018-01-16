package com.interestcontent.liudeyu.weibo.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.FileUtils;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.AbsActivity;
import com.interestcontent.liudeyu.base.utils.Logger;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudeyu on 2018/1/16.
 */

public class PictureBrowseActivity extends AbsActivity {

    private static final String TAG = "PictureBrowseActivity";
    static final String IMAGE_URL_TAG = "IMAGE_URL_TAG".toLowerCase();
    @BindView(R.id.imagebrowseView)
    SubsamplingScaleImageView mImageView;


    public static void start(Context context, String loadImageUrl) {
        Intent starter = new Intent(context, PictureBrowseActivity.class);
        starter.putExtra(IMAGE_URL_TAG, loadImageUrl);
        context.startActivity(starter);
    }

    @Override
    protected int getStatusBarColor() {
        return getResources().getColor(R.color.md_amber_200);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_browse_layout);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            Intent intent = getIntent();
            String url = intent.getStringExtra(IMAGE_URL_TAG);
            new GetImageCacheTask(this, new GetImageCacheTask.FilePathCallback() {
                @Override
                public void fileCachePath(String path) {
                    File file = new File(path);
                    if (file.isFile() && file.exists()) {
                        long length = file.length();
                        Logger.d(TAG, FileUtils.getFileSize(path));
                        if (length > 2 * 1000 * 1000) {
                            mImageView.setScaleX(mImageView.getMaxScale() - 0.1f);
                            mImageView.setScaleY(mImageView.getMaxScale() - 0.1f);
                        } else {
                            mImageView.resetScaleAndCenter();
                        }
                        mImageView.setImage(ImageSource.uri(path));
                    }
                }
            }).execute(url);


        }
    }


}
