package com.interestcontent.liudeyu.contents.weibo.feeds;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.contents.weibo.contents.PictureBrowseActivity;
import com.interestcontent.liudeyu.contents.weibo.util.WeiboUrlsUtils;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liudeyu on 2018/1/17.
 */
@Deprecated
public class WeiboImageRecycleViewAdapter extends RecyclerView.Adapter<WeiboImageRecycleViewHolder> implements View.OnClickListener, OnRecycleViewItemClickListener {

    private Context mContext;
    private List<String> mUrls;
    private Set<WeakReference<OnRecycleViewItemClickListener>> mImagesSetClickObserverSet;
    private Fragment mFragment;

    public WeiboImageRecycleViewAdapter(List<String> urls, Fragment fragment) {
        mUrls = urls;
        mFragment = fragment;
        mContext = mFragment.getActivity();
    }

    public WeiboImageRecycleViewAdapter(Context context, List<String> urls) {
        mContext = context;
        mUrls = urls;
    }

    public void addImagesUrls(List<String> data) {
        mUrls.addAll(data);
        notifyItemChanged(mUrls.size());
    }

    public void setImageUrls(List<String> data) {
        mUrls = data;
        notifyItemChanged(0);
    }

    public void addItemClickListener(OnRecycleViewItemClickListener listener) {
        if (mImagesSetClickObserverSet == null) {
            mImagesSetClickObserverSet = new HashSet<>();
        }
        mImagesSetClickObserverSet.add(new WeakReference<OnRecycleViewItemClickListener>(listener));
    }

    @Override
    public WeiboImageRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weibo_images_gallery, parent, false);
        ImageView imageView = view.findViewById(R.id.image_iv);
        imageView.setOnClickListener(this);
        view.setOnClickListener(this);
        return new WeiboImageRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeiboImageRecycleViewHolder holder, int position) {
        if (position >= mUrls.size()) {
            return;
        }
        holder.mImageView.setTag(R.layout.weibo_images_gallery, position);
        holder.itemView.setTag(R.layout.weibo_images_gallery);
        int width = (int) mContext.getResources().getDimension(R.dimen.wb_cell_image_size);
        if (mFragment != null) {
            Glide.with(mFragment).load(mUrls.get(position)).override(width, width)
                    .centerCrop().into(holder.mImageView);
        } else {
            Glide.with(mContext).load(mUrls.get(position)).override(width, width)
                    .centerCrop().into(holder.mImageView);
        }
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
        onItemClick(view, (Integer) view.getTag(R.layout.weibo_images_gallery));
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.image_iv:
                if (mUrls.get(position).contains(WeiboUrlsUtils.MIDDLE)) {
                    PictureBrowseActivity.start(mContext, mUrls.get(position).replace(WeiboUrlsUtils.MIDDLE, WeiboUrlsUtils.ORIGIN));
                }
                break;

        }
        notifyRecycleItemClickObservers(view, position);
    }

    private void notifyRecycleItemClickObservers(View view, int position) {
        if (mImagesSetClickObserverSet == null) {
            return;
        }
        for (WeakReference<OnRecycleViewItemClickListener> listenerWeakReference : mImagesSetClickObserverSet) {
            if (listenerWeakReference.get() != null) {
                listenerWeakReference.get().onItemClick(view, position);
            } else {
                mImagesSetClickObserverSet.remove(listenerWeakReference);
            }
        }
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
