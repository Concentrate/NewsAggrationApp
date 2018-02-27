package com.interestcontent.liudeyu.contents.news.cells;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.contents.weibo.contents.PictureBrowseActivity;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

import java.util.List;

/**
 * Created by liudeyu on 2018/2/5.
 */

public class MultipleImageRecycleAdapter extends RecyclerView.Adapter<RVBaseViewHolder> {

    private Context mContext;
    private List<String> mData;
    private Fragment mFragment;

    public MultipleImageRecycleAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVBaseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.multiple_imageview_display, parent, false));
    }

    @Override
    public void onBindViewHolder(final RVBaseViewHolder holder, final int position) {
        if (mFragment != null) {
            Glide.with(mFragment).load(mData.get(position)).into(
                    holder.getImageView(R.id.news_multi_image_iv));
        } else {
            Glide.with(mContext).load(mData.get(position)).centerCrop()
                    .into(holder.getImageView(R.id.news_multi_image_iv));
        }
        holder.getImageView(R.id.news_multi_image_iv).setTag(R.layout.multiple_imageview_display, mData.get(position));
        holder.getImageView(R.id.news_multi_image_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureBrowseActivity.start(mContext, (String) holder
                        .getImageView(R.id.news_multi_image_iv).getTag(R.layout.multiple_imageview_display));
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


}
