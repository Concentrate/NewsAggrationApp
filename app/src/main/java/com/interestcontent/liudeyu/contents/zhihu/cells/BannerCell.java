package com.interestcontent.liudeyu.contents.zhihu.cells;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.contents.zhihu.bean.ZhihuJournayListRequest;
import com.interestcontent.liudeyu.contents.zhihu.contents.ZhihuContentActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/2/8.
 */

public class BannerCell extends RVBaseCell<List<ZhihuJournayListRequest.TopStoriesBean>> {

    private static final String TAG = "BannerCell";
    private Fragment mFragment;
    private Activity mActivity;
    private Banner mBanner;

    public BannerCell(List<ZhihuJournayListRequest.TopStoriesBean> storiesBeans, Fragment fragment) {
        super(storiesBeans);
        mFragment = fragment;
        mActivity = mFragment.getActivity();
    }


    @Override
    public int getItemType() {
        return FeedConstants.BANNER_CELL_TYPE;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVBaseViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.banner_cell_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        if (position >= mData.size()) {
            return;
        }
        mBanner = (Banner) holder.getView(R.id.banner);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mBanner.setImageLoader(new GLideImageLoader());
        mBanner.setBannerAnimation(Transformer.FlipHorizontal);
        List<String> imageUrls = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        final List<String> ids = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            if (!TextUtils.isEmpty(mData.get(i).getImage())) {
                imageUrls.add(mData.get(i).getImage());
                titleList.add(mData.get(i).getTitle());
                ids.add(mData.get(i).getId() + "");
            }
        }
        mBanner.setImages(imageUrls);
        mBanner.isAutoPlay(true);
        mBanner.setBannerTitles(titleList);
        mBanner.setDelayTime(3000);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Logger.d(TAG, "banner click position " + position);
                ZhihuContentActivity.start(mActivity, ids.get(position));
            }
        }).start();

    }


    @Override
    public void releaseResource() {
        super.releaseResource();
        mBanner.stopAutoPlay();
    }

    private class GLideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(mFragment).load(path).centerCrop().into(imageView);
        }
    }


}

