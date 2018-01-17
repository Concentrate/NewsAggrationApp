package com.interestcontent.liudeyu.weibo.feeds;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.weibo.component.PictureBrowseActivity;

import java.util.List;

/**
 * Created by liudeyu on 2018/1/17.
 */
class WeiboImageRecycleViewAdapter extends RecyclerView.Adapter<WeiboImageRecycleViewHolder> implements View.OnClickListener, OnRecycleViewItemClickListener {

    private Context mContext;
    private List<String> mUrls;

    public WeiboImageRecycleViewAdapter(Context context, List<String> data) {
        mContext = context;
        mUrls = data;
    }

    public void addImagesUrls(List<String> data) {
        mUrls.addAll(data);
        notifyItemChanged(mUrls.size());
    }

    public void setImageUrls(List<String> data) {
        mUrls = data;
        notifyItemChanged(0);
    }

    @Override
    public WeiboImageRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weibo_images_gallery, parent, false);
        ImageView imageView = view.findViewById(R.id.image_iv);
        imageView.setOnClickListener(this);
        return new WeiboImageRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeiboImageRecycleViewHolder holder, int position) {
        if (position >= mUrls.size()) {
            return;
        }
        holder.mImageView.setTag(R.id.image_iv, position);
        int width = (int) mContext.getResources().getDimension(R.dimen.wb_cell_image_size);
        Glide.with(mContext).load(mUrls.get(position)).override(width, width)
                .centerCrop().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        if (mUrls != null) {
            return mUrls.size();
        }
        return 0;
    }

    @Override
    public void onClick(View view) {
        onItemClick(view, (Integer) view.getTag(R.id.image_iv));
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.image_iv:
                if (mUrls.get(position).contains(WeiboCell.MIDDLE)) {
                    PictureBrowseActivity.start(mContext, mUrls.get(position).replace(WeiboCell.MIDDLE, WeiboCell.ORIGIN));
                }
                break;

        }

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
