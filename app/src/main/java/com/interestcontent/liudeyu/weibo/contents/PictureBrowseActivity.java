package com.interestcontent.liudeyu.weibo.contents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.interestcontent.liudeyu.R;
import com.example.commonlib.components.AbsActivity;
import com.interestcontent.liudeyu.util.FileTypeUtils;
import com.interestcontent.liudeyu.weibo.component.GetImageCacheTask;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudeyu on 2018/1/16.
 */

public class PictureBrowseActivity extends AbsActivity {

    private static final String TAG = "record_pic_size";
    static final String IMAGE_URL_TAG = "IMAGE_URL_TAG".toLowerCase();
    @BindView(R.id.imagebrowseView)
    SubsamplingScaleImageView mImageView;
    @BindView(R.id.gif_image_view)
    ImageView mGifImageView;
    public static final int SCALE_SIZE = (int) (1.2f * 1000 * 1000);


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
                        if ("gif".equals(FileTypeUtils.getFileType(file.getAbsolutePath()))) {
                            mImageView.setVisibility(View.GONE);
                            mGifImageView.setVisibility(View.VISIBLE);
                            Glide.with(PictureBrowseActivity.this).load(file).asGif().into(mGifImageView);
                        } else {
                            mGifImageView.setVisibility(View.GONE);
                            mImageView.setVisibility(View.VISIBLE);
                            mImageView.setImage(ImageSource.uri(path));
                        }
                    }
                }
            }).execute(url);


        }
    }


}
