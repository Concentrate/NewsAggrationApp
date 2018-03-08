package com.interestcontent.liudeyu.contents.weibo.contents;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
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
import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.utils.FilePathUtils;
import com.interestcontent.liudeyu.base.utils.MyPermissionDIalogRequetUtil;
import com.interestcontent.liudeyu.contents.weibo.component.GetImageCacheTask;
import com.interestcontent.liudeyu.settings.ThemeDataManager;
import com.interestcontent.liudeyu.util.FileTypeUtils;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudeyu on 2018/1/16.
 */

public class PictureBrowseActivity extends AbsActivity implements View.OnTouchListener {

    private static final String TAG = "record_pic_size";
    static final String IMAGE_URL_TAG = "IMAGE_URL_TAG".toLowerCase();
    static final String INIT_POS = "INIT_POS".toLowerCase();
    protected static final float FLIP_DISTANCE = 500;

    @BindView(R.id.imagebrowseView)
    SubsamplingScaleImageView mImageView;
    @BindView(R.id.gif_image_view)
    ImageView mGifImageView;
    @BindView(R.id.save_pic_tv)
    TextView mSaveTextView;
    @BindView(R.id.indictor_tv)
    TextView mIndicatorTv;
    public static final int SCALE_SIZE = (int) (1.2f * 1000 * 1000);
    private ArrayList<String> mUrlList;
    private File mFile;
    GestureDetector mDetector;
    private int mCurrentPicPosition;


    public static void start(Context context, ArrayList<String> loadImageUrl, int initPos) {
        Intent starter = new Intent(context, PictureBrowseActivity.class);
        starter.putStringArrayListExtra(IMAGE_URL_TAG, loadImageUrl);
        starter.putExtra(INIT_POS, initPos);
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
            mUrlList = intent.getStringArrayListExtra(IMAGE_URL_TAG);
            mCurrentPicPosition = intent.getIntExtra(INIT_POS, 0);
            if (!mUrlList.isEmpty()) {
                loadImage(mUrlList.get(mCurrentPicPosition));
            }

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
        initGestore();
        mGifImageView.setOnTouchListener(this);
        mImageView.setOnTouchListener(this);
    }

    private void loadImage(String url) {
        mIndicatorTv.setText((mCurrentPicPosition+1)+"/"+mUrlList.size()+"");
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
        }).execute(url);
    }

    private void initGestore() {
        mDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // TODO Auto-generated method stub  
                return false;
            }

            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                // TODO Auto-generated method stub  

            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                // TODO Auto-generated method stub  
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // TODO Auto-generated method stub  

            }

            /**
             * e1 The first down motion event that started the fling. e2 The
             * move motion event that triggered the current onFling.
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
                    Logger.i("MYTAG", "向左滑...");
                    if (mCurrentPicPosition >= mUrlList.size() - 1) {
                        return false;
                    }
                    loadImage(mUrlList.get(++mCurrentPicPosition));
                    return true;
                }
                if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
                    Logger.i("MYTAG", "向右滑...");
                    if (mCurrentPicPosition <= 0) {
                        return false;
                    }
                    loadImage(mUrlList.get(--mCurrentPicPosition));
                    return true;
                }
                if (e1.getY() - e2.getY() > FLIP_DISTANCE) {
                    Logger.i("MYTAG", "向上滑...");
                    return true;
                }
                if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
                    Logger.i("MYTAG", "向下滑...");
                    return true;
                }

                Logger.d("TAG", e2.getX() + " " + e2.getY());
                return false;
            }

        });
    }


    private void saveFile() {
        if (mUrlList != null && mFile != null) {
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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (mDetector != null) {
            return mDetector.onTouchEvent(motionEvent);
        }
        return false;
    }
}
