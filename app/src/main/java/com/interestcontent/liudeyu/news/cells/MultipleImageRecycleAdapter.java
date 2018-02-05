package com.interestcontent.liudeyu.news.cells;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
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
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        if (mFragment != null) {
            Glide.with(mFragment).load(mData.get(position)).into(
                    holder.getImageView(R.id.news_multi_image_iv));
        } else {
            Glide.with(mContext).load(mData.get(position)).centerCrop()
                    .into(holder.getImageView(R.id.news_multi_image_iv));
        }

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }
}
