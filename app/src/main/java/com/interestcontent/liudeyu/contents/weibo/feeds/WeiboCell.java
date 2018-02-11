package com.interestcontent.liudeyu.contents.weibo.feeds;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseUiKit.aboutRecycleView.BaseRecyclerView;
import com.interestcontent.liudeyu.base.baseUiKit.aboutRecycleView.GridManagerSpaceItemDecoration;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.interestcontent.liudeyu.contents.weibo.contents.PictureBrowseActivity;
import com.interestcontent.liudeyu.contents.weibo.contents.WeiboContentActivity;
import com.interestcontent.liudeyu.contents.weibo.data.WeiboLoginManager;
import com.interestcontent.liudeyu.contents.weibo.data.bean.WeiboBean;
import com.interestcontent.liudeyu.contents.weibo.data.bean.WeiboUserBean;
import com.interestcontent.liudeyu.contents.weibo.util.MyWeiboPageUtils;
import com.interestcontent.liudeyu.contents.weibo.util.WeiboUrlsUtils;
import com.luseen.autolinklibrary.AutoLinkMode;
import com.luseen.autolinklibrary.AutoLinkOnClickListener;
import com.luseen.autolinklibrary.AutoLinkTextView;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import static com.interestcontent.liudeyu.contents.weibo.util.WeiboUrlsUtils.MIDDLE;

/**
 * Created by liudeyu on 2018/1/2.
 */

public class WeiboCell extends RVBaseCell<WeiboBean> {


    private static final String TAG = WeiboCell.class.getSimpleName();

    private Context mActivity;
    private android.support.v4.app.Fragment mFragment;


    public WeiboCell(WeiboBean weiboBean, Fragment fragment) {
        super(weiboBean);
        mFragment = fragment;
        mActivity = mFragment.getActivity();
    }

    @Override
    public int getItemType() {
        return FeedConstants.FEED_NORMAL_WEIBO_TYPE;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case FeedConstants.FEED_NORMAL_WEIBO_TYPE:
                view = LayoutInflater.from(mActivity).inflate(R.layout.weibo_feed_cell_layout, parent, false);
                initViewState(view);
                break;
        }
        RVBaseViewHolder viewHolder = new RVBaseViewHolder(view);
        return viewHolder;
    }

    private void initViewState(final View view) {
        AutoLinkTextView autoLinkTextView = (AutoLinkTextView) view.findViewById(R.id.wb_content_tv);
        autoLinkTextView.addAutoLinkMode(
                AutoLinkMode.MODE_HASHTAG,
                AutoLinkMode.MODE_PHONE,
                AutoLinkMode.MODE_URL,
                AutoLinkMode.MODE_MENTION);
        autoLinkTextView.setWeiboEmojiAdd(true);
        autoLinkTextView.setHashtagModeColor(mActivity.getResources().getColor(R.color.md_pink_100));
        autoLinkTextView.setPhoneModeColor(mActivity.getResources().getColor(R.color.md_green_100));
        autoLinkTextView.setUrlModeColor(mActivity.getResources().getColor(R.color.md_light_blue_400));
        autoLinkTextView.setMentionModeColor(mActivity.getResources().getColor(R.color.md_deep_purple_100));
        autoLinkTextView.setEmailModeColor(ContextCompat.getColor(mActivity, R.color.md_deep_orange_800));
        autoLinkTextView.setAutoLinkOnClickListener(new AutoLinkOnClickListener() {
            @Override
            public void onAutoLinkTextClick(AutoLinkMode autoLinkMode, String matchedText) {
                switch (autoLinkMode) {
                    case MODE_URL:
                        Intent intent = BrowseActivity.getIntent(matchedText, true);
                        intent.setClass(mActivity, BrowseActivity.class);
                        mActivity.startActivity(intent);
                        break;

                }
            }
        });
        final BaseRecyclerView recyclerView = view.findViewById(R.id.wb_image_recyle_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3, GridLayoutManager.VERTICAL, false));
        view.post(new Runnable() {
            @Override
            public void run() {
                int itemDecortWidth = (int) ((view.getWidth() - mActivity.getResources().
                        getDimension(R.dimen.wb_cell_image_size) * 3) / 2);
                recyclerView.addItemDecoration(new GridManagerSpaceItemDecoration(itemDecortWidth, SizeUtils.dp2px(10)));
            }
        });

    }


    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        if (holder.getItemViewType() == FeedConstants.FEED_NORMAL_WEIBO_TYPE) {
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.root_container:
                        case R.id.wb_content_tv:
                            dealWithGoToSourceWeibo(mData.getIdstr());
                            break;
                        case R.id.avater_iv:
                        case R.id.author_tv:
                            dealWithGoToAuthorPage(mData.getUser().getIdstr());
                            break;
                    }

                }
            };
            BaseRecyclerView.OnBlankClickListener blankListener = new BaseRecyclerView.OnBlankClickListener() {
                @Override
                public void onBlankClick(RecyclerView recyclerView) {
                    dealWithGoToSourceWeibo(mData.getIdstr());
                }
            };

            OnWeiboOperationBottomClickListener onWeiboOperationBottomClickListener = new OnWeiboOperationBottomClickListener(mActivity, mData.getIdstr());
            holder.getItemView().setOnClickListener(onClickListener);
            holder.getImageView(R.id.avater_iv).setOnClickListener(onClickListener);
            holder.getTextView(R.id.author_tv).setOnClickListener(onClickListener);
            holder.getTextView(R.id.wb_content_tv).setOnClickListener(onClickListener);
            BaseRecyclerView baseRecyclerView = (BaseRecyclerView) holder.getView(R.id.wb_image_recyle_view);
            baseRecyclerView.setBlankListener(blankListener);
            holder.getView(R.id.good_fingger_layout).setOnClickListener(onWeiboOperationBottomClickListener);
            holder.getView(R.id.resend_layout).setOnClickListener(onWeiboOperationBottomClickListener);
            holder.getView(R.id.comment_layout).setOnClickListener(onWeiboOperationBottomClickListener);

            holder.getTextView(R.id.resend_count_tv).setText(mData.getReposts_count() + "");
            holder.getTextView(R.id.comment_count_tv).setText(mData.getComments_count() + "");
            holder.getTextView(R.id.good_fingger_count_tv).setText(mData.getAttitudes_count() + "");


            AutoLinkTextView autoLinkTextView = (AutoLinkTextView) holder.getTextView(R.id.wb_content_tv);
            autoLinkTextView.setAutoLinkText(mData.getText());
            holder.getTextView(R.id.create_time_tv).setText(mData.getCreated_at());
            WeiboUserBean userBean = mData.getUser();
            if (userBean != null) {
                holder.getTextView(R.id.author_tv).setText(userBean.getName());
                Glide.with(mFragment).load(userBean.getProfile_image_url()).into(holder.getImageView(R.id.avater_iv));
            }
            List<WeiboBean.PicUrlsBean> picUrlsBeans = mData.getPic_urls();
            RecyclerView recyclerView = (RecyclerView) holder.getView(R.id.wb_image_recyle_view);
            String originPicDomen = WeiboUrlsUtils.getOriginPicHost(mData.getOriginal_pic());
            if (picUrlsBeans != null && !picUrlsBeans.isEmpty()) {
                int limitPreivewSize = WeiboUrlsUtils.getLimitPreivewSize(picUrlsBeans);
                recyclerView.setVisibility(View.VISIBLE);
                List<String> urls = WeiboUrlsUtils.getOriginPicUrls(picUrlsBeans, originPicDomen, limitPreivewSize, MIDDLE);
                recyclerView.setAdapter(new CommonAdapter<String>(mActivity, R.layout.weibo_images_gallery, urls) {
                    @Override
                    protected void convert(ViewHolder holder, final String s, final int position) {
                        Glide.with(mContext).load(s)
                                .centerCrop().into((ImageView) holder.getView(R.id.image_iv));
                        holder.getView(R.id.image_iv).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PictureBrowseActivity.start(mContext, s.replace(WeiboUrlsUtils.MIDDLE, WeiboUrlsUtils.ORIGIN));
                            }
                        });
                    }
                });

            } else {
                recyclerView.setVisibility(View.GONE);
            }
        }

    }

    private void dealWithGoToAuthorPage(String profile) {
        if (!TextUtils.isEmpty(profile)) {
            MyWeiboPageUtils.getInstance(mActivity, WeiboLoginManager.getInstance().getAuthInfo())
                    .startOtherPage(WeiboUrlsUtils.getPersonalProfileUrl(profile));
        }
    }

    private void dealWithGoToSourceWeibo(String id) {
//        MyWeiboPageUtils.getInstance(mActivity, WeiboLoginManager.getInstance()
//                .getAuthInfo()).startWeiboDetailPage(mData.get(position).getMid(),
//                mData.get(position).getUser().getIdstr(), true);
        Intent intent = new Intent(mActivity, WeiboContentActivity.class);
        intent.putExtra(WeiboContentActivity.WEIBO_ITEM, id);
        mActivity.startActivity(intent);
    }


}
