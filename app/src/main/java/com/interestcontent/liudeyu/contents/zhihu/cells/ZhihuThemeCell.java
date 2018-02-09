package com.interestcontent.liudeyu.contents.zhihu.cells;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseUiKit.aboutGlide.GlideRoundTransform;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.contents.zhihu.bean.ZhihuThemeRequest;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

/**
 * Created by liudeyu on 2018/2/9.
 */

public class ZhihuThemeCell extends RVBaseCell<ZhihuThemeRequest.ThemeBean> {
    private Fragment mFragment;
    private Activity mActivity;

    public ZhihuThemeCell(ZhihuThemeRequest.ThemeBean themeBean, Fragment fragment) {
        super(themeBean);
        mFragment = fragment;
        mActivity = mFragment.getActivity();
    }

    @Override
    public int getItemType() {
        return FeedConstants.OPINION_ZHIHU_THEME_CELL_TYPE;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RVBaseViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.opinion_theme_cell_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        if (!TextUtils.isEmpty(mData.getThumbnail())) {
            Glide.with(mActivity).load(mData.getThumbnail()).transform(new GlideRoundTransform(mActivity, 5)
                    , new CenterCrop(mActivity)).into(holder.getImageView(R.id.theme_avater));
        }
        holder.getTextView(R.id.theme_title).setText(mData.getName());
        holder.getTextView(R.id.theme_des).setText(mData.getDescription());
    }
}
