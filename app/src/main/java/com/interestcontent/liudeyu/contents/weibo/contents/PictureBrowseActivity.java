package com.interestcontent.liudeyu.contents.weibo.contents;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.commonlib.components.AbsActivity;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.utils.FilePathUtils;
import com.interestcontent.liudeyu.base.utils.MyPermissionDIalogRequetUtil;
import com.interestcontent.liudeyu.contents.weibo.component.GetImageCacheTask;
import com.interestcontent.liudeyu.settings.ThemeDataManager;
import com.interestcontent.liudeyu.util.FileTypeUtils;

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
    @BindView(R.id.save_pic_tv)
    TextView mSaveTextView;
    public static final int SCALE_SIZE = (int) (1.2f * 1000 * 1000);
    private String mUrl;
    private File mFile;


    public static void start(Context context, String loadImageUrl) {
        Intent starter = new Intent(context, PictureBrowseActivity.class);
        starter.putExtra(IMAGE_URL_TAG, loadImageUrl);
        context.startActivity(starter);
    }

    @Override
    protected int getStatusBarColor() {
        return ThemeDataManager.getInstance().getThemeColorInt();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_browse_layout);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            Intent intent = getIntent();
            mUrl = intent.getStringExtra(IMAGE_URL_TAG);
            new GetImageCacheTask(this, new GetImageCacheTask.FilePathCallback() {
                @Override
                public void fileCachePath(String path) {
                    if (!PictureBrowseActivity.this.isActive()) {
                        return;
                    }
                    mFile = new File(path);
                    if (mFile.isFile() && mFile.exists()) {
                        if ("gif".equals(FileTypeUtils.getFileType(mFile.getAbsolutePath()))) {
                            mImageView.setVisibility(View.GONE);
                            mGifImageView.setVisibility(View.VISIBLE);
                            Glide.with(PictureBrowseActivity.this).load(mFile).asGif().into(mGifImageView);
                        } else {
                            mGifImageView.setVisibility(View.GONE);
                            mImageView.setVisibility(View.VISIBLE);
                            mImageView.setImage(ImageSource.uri(path));
                        }
                    }
                }
            }).execute(mUrl);

        }
        mSaveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    saveFile();
                } else {
                    MyPermissionDIalogRequetUtil.requetPermission(PictureBrowseActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
            }
        });

    }


    private void saveFile() {
        if (mUrl != null && mFile != null) {
            String saveFileName = mFile.hashCode() + "." + FileTypeUtils.getFileType(mFile.getAbsolutePath());
            if (mFile.isFile() && mFile.exists()) {
                String picDir = FilePathUtils.getSaveImageFilePath();
                if (TextUtils.isEmpty(picDir)) {
                    return;
                }
                String targetDir = picDir + File.separator + saveFileName;
                boolean success = FileUtils.copyFile(mFile.getAbsolutePath(), targetDir, new FileUtils.OnReplaceListener() {
                    @Override
                    public boolean onReplace() {
                        return false;
                    }
                });
                if (success) {
                    ToastUtils.showShort("保存成功，文件在 " + targetDir);
                }
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MyPermissionDIalogRequetUtil.PERMISSION_WIRTE_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    saveFile();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    ToastUtils.showShort("无权限，无法保存图片");
                }
                break;

        }
    }
}
