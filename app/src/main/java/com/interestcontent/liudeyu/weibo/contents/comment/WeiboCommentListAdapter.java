package com.interestcontent.liudeyu.weibo.contents.comment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.interestcontent.liudeyu.base.thread.TaskManager;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboCommontBean;
import com.luseen.autolinklibrary.AutoLinkMode;
import com.luseen.autolinklibrary.AutoLinkOnClickListener;
import com.luseen.autolinklibrary.AutoLinkTextView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by liudeyu on 2018/1/31.
 */

public class WeiboCommentListAdapter extends RecyclerView.Adapter<WeiboCommentListViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<WeiboCommontBean> mData;
    private WeiboCommentListViewHolder mHolder;
    private WeiboBean mWeiboBean;

    public WeiboCommentListAdapter(Context context, List<WeiboCommontBean> data, WeiboBean weiboBean) {
        mContext = context;
        mData = data;
        mWeiboBean = weiboBean;
    }

    @Override
    public WeiboCommentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHolder == null) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.comment_item_layout, parent, false);
            mHolder = new WeiboCommentListViewHolder(view);
            mHolder.mGoodAttitudeIv.setOnClickListener(this);
            initView(mHolder);
        }

        return mHolder;
    }

    private void initView(WeiboCommentListViewHolder holder) {
        AutoLinkTextView autoLinkTextView = holder.mCommentText;
        autoLinkTextView.addAutoLinkMode(
                AutoLinkMode.MODE_HASHTAG,
                AutoLinkMode.MODE_PHONE,
                AutoLinkMode.MODE_URL,
                AutoLinkMode.MODE_MENTION);
        autoLinkTextView.setWeiboEmojiAdd(true);
        autoLinkTextView.setHashtagModeColor(mContext.getResources().getColor(R.color.md_pink_100));
        autoLinkTextView.setPhoneModeColor(mContext.getResources().getColor(R.color.md_green_100));
        autoLinkTextView.setUrlModeColor(mContext.getResources().getColor(R.color.md_light_blue_400));
        autoLinkTextView.setMentionModeColor(mContext.getResources().getColor(R.color.md_deep_purple_100));
        autoLinkTextView.setEmailModeColor(ContextCompat.getColor(mContext, R.color.md_deep_orange_800));
        autoLinkTextView.setAutoLinkOnClickListener(new AutoLinkOnClickListener() {
            @Override
            public void onAutoLinkTextClick(AutoLinkMode autoLinkMode, String matchedText) {
                switch (autoLinkMode) {
                    case MODE_URL:
                        Intent intent = BrowseActivity.getIntent(matchedText, true);
                        intent.setClass(mContext, BrowseActivity.class);
                        mContext.startActivity(intent);
                        break;

                }
            }
        });
    }

    @Override
    public void onBindViewHolder(WeiboCommentListViewHolder holder, int position) {
        mHolder.setViewTag(position);
        holder.mAuthor.setText(mData.get(position).getUser().getName());
        Glide.with(mContext).load(mData.get(position).getUser().getProfile_image_url()).into(holder.mImageView);
        holder.mCommentText.setAutoLinkText(mData.get(position).getText());
        holder.mCreateTime.setText(mData.get(position).getCreated_at());
        holder.mGoodAttitudeTv.setText(mData.get(position).getFloor_number()+"");

    }


    public void setData(List<WeiboCommontBean> data) {
        mData = data;
        notifyItemChanged(0);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.comment_digger_iv:
                int pos = mHolder.getPos();
                mHolder.mGoodAttitudeIv.setSelected(!mHolder.mGoodAttitudeIv.isSelected());
                int num = mData.get(pos).getFloor_number() + (mHolder.mGoodAttitudeIv.isSelected() ? 1 : -1);
                mHolder.mGoodAttitudeTv.setText(num);
                dealWithAttitudeUpload(mHolder.mGoodAttitudeIv.isSelected(), mWeiboBean.getIdstr());
                if (mHolder.mGoodAttitudeIv.isSelected()) {
                    showEnlargeAnimation(mHolder.mGoodAttitudeIv);
                }
                break;

        }


    }

    private void showEnlargeAnimation(final ImageView needAnimationView) {
        needAnimationView.animate().scaleX(1.5f).scaleY(1.5f).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {
                needAnimationView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500).start();
            }
        });
    }


    private void dealWithAttitudeUpload(boolean b, final String id) {
        final String url = b ? Constants.WEIBO_GOOD_ATTITUDE : Constants.WEIBO_NEGETIVE_ATTITUDE;
        TaskManager.inst().commit(new Callable() {
            @Override
            public Object call() throws Exception {
                OkHttpUtils.post().url(url).addParams(Constants.WB_REQUEST_PARAMETER.ACCESS_TOKEN, SharePreferenceUtil.getStringPreference(MyApplication.sApplication,
                        SpConstants.WEIBO_AUTHEN_TOKEN)).addParams(Constants.WB_REQUEST_PARAMETER.ATTITUDE, "simle").addParams(Constants.WB_REQUEST_PARAMETER.ID,
                        id).build().execute();
                return null;
            }
        });
    }
}
