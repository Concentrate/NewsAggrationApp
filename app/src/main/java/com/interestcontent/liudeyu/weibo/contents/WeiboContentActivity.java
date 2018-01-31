package com.interestcontent.liudeyu.weibo.contents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.base.baseUiKit.aboutRecycleView.SpaceItemDecoration;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.dataManager.FeedDataManager;
import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.base.thread.TaskManager;
import com.interestcontent.liudeyu.weibo.contents.comment.WeiboCommentListAdapter;
import com.interestcontent.liudeyu.weibo.data.WeiboLoginManager;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboCommontBean;
import com.interestcontent.liudeyu.weibo.feeds.OnWeiboOperationBottomClickListener;
import com.interestcontent.liudeyu.weibo.feeds.WeiboImageRecycleViewAdapter;
import com.interestcontent.liudeyu.weibo.util.MyWeiboPageUtils;
import com.interestcontent.liudeyu.weibo.util.WeiboParameter;
import com.interestcontent.liudeyu.weibo.util.WeiboUrlsUtils;
import com.luseen.autolinklibrary.AutoLinkMode;
import com.luseen.autolinklibrary.AutoLinkOnClickListener;
import com.luseen.autolinklibrary.AutoLinkTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.interestcontent.liudeyu.weibo.util.WeiboUrlsUtils.MIDDLE;

/**
 * Created by liudeyu on 2018/1/20.
 */


public class WeiboContentActivity extends BaseActivity {

    private static final String WEIBO_ID = "WEIBO_ID";
    public static final String WEIBO_ITEM = "WEIBO_ITEM".toLowerCase();
    public static final int WHAT = 2;


    @BindView(R.id.avater_iv)
    ImageView mAvater;
    @BindView(R.id.author_tv)
    TextView mAuthor;
    @BindView(R.id.create_time_tv)
    TextView mCreateTime;
    @BindView(R.id.wb_content_tv)
    AutoLinkTextView mContentView;
    @BindView(R.id.wb_image_recyle_view)
    RecyclerView mImageRecycleView;
    @BindView(R.id.resend_layout)
    LinearLayout mResendLayout;
    @BindView(R.id.resend_iv)
    ImageView mResendIv;
    @BindView(R.id.resend_count_tv)
    TextView mResendTv;
    @BindView(R.id.good_fingger_layout)
    LinearLayout mGoodAttitudeLayout;
    @BindView(R.id.good_finger_iv)
    ImageView mGoodAttitudeIv;
    @BindView(R.id.good_fingger_count_tv)
    TextView mGooAttitudeTv;
    @BindView(R.id.comment_layout)
    LinearLayout mCommentLayout;
    @BindView(R.id.comment_iv)
    ImageView mCommentIv;
    @BindView(R.id.comment_count_tv)
    TextView mCommentCountTv;
    @BindView(R.id.comment_recycle_layout)
    RecyclerView mCommentRecycleView;
    private Context mContext;
    private WeiboImageRecycleViewAdapter mImageRecycleAdapter;
    private WeiboBean mWeiboBean;
    private boolean isFirstTimeRequestComment = true;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT:
                    List<WeiboCommontBean> weiboCommontBeans = (List<WeiboCommontBean>) msg.obj;
                    showComments(weiboCommontBeans);
                    break;

            }
        }
    };
    private WeiboCommentListAdapter mWeiboCommentListAdapter;

    private void showComments(List<WeiboCommontBean> weiboCommontBeans) {
        if (mWeiboCommentListAdapter == null) {
            mWeiboCommentListAdapter = new WeiboCommentListAdapter(this, weiboCommontBeans, mWeiboBean);
            mCommentRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            mCommentRecycleView.setAdapter(mWeiboCommentListAdapter);
        }
        mWeiboCommentListAdapter.setData(weiboCommontBeans);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(WHAT);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mContext = this;
        Intent intent = getIntent();
        if (intent != null) {
            mWeiboBean = (WeiboBean) intent.getSerializableExtra(WEIBO_ITEM);
            if (mWeiboBean != null) {
                initViews();
                initData(mWeiboBean);
                startRequestCommentData();
            }
        }
    }

    private void startRequestCommentData() {
        TaskManager.inst().commit(mHandler, new Callable() {
            @Override
            public Object call() throws Exception {
                WeiboParameter.ParameterBuilder builder = new WeiboParameter.ParameterBuilder();
                builder.setEveryPageCount(100).setWeiboId(mWeiboBean.getId() + "");
                List<WeiboCommontBean> list = FeedDataManager.getInstance().getWeiboCommentListByNet(ItemTab.WEIBO_COMMENT,
                        Constants.WEIBO_COMMENT_API, builder.build().getParaMap(), isFirstTimeRequestComment);
                isFirstTimeRequestComment = false;
                return list;
            }
        }, WHAT);
    }


    private void initViews() {
        AutoLinkTextView autoLinkTextView = mContentView;
        autoLinkTextView.addAutoLinkMode(
                AutoLinkMode.MODE_HASHTAG,
                AutoLinkMode.MODE_PHONE,
                AutoLinkMode.MODE_URL,
                AutoLinkMode.MODE_MENTION);
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
                        Intent intent = BrowseActivity.getIntent(matchedText, false);
                        intent.setClass(WeiboContentActivity.this, BrowseActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });
        RecyclerView recyclerView = mImageRecycleView;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        final int itemDecortWidth = (int) ((ScreenUtils.getScreenWidth() - mContext.getResources().
                getDimension(R.dimen.wb_cell_image_size) * 3) / 2);
        recyclerView.addItemDecoration(new SpaceItemDecoration(itemDecortWidth, SizeUtils.dp2px(10)));
        mImageRecycleAdapter = new WeiboImageRecycleViewAdapter(mContext, new ArrayList<String>());
        recyclerView.setAdapter(mImageRecycleAdapter);
        OnWeiboOperationBottomClickListener bottomClickListener = new OnWeiboOperationBottomClickListener(this);
        mResendLayout.setOnClickListener(bottomClickListener);
        mGoodAttitudeLayout.setOnClickListener(bottomClickListener);
        mCommentLayout.setOnClickListener(bottomClickListener);
        bottomClickListener.setNecesseryViewTag(mWeiboBean.getIdstr(), mResendLayout, mGoodAttitudeLayout, mCommentLayout);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                boolean isHandle = false;
                switch (item.getItemId()) {
                    case R.id.source_weibo:
                        gotoSourceWeibo();
                        isHandle = true;
                        break;
                }
                return isHandle;
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mBackButton.setColorFilter(getResources().getColor(R.color.white));
        mToolbarTitle.setTextColor(getResources().getColor(R.color.white));
    }

    private void gotoSourceWeibo() {
        if (mWeiboBean == null) {
            return;
        }
        MyWeiboPageUtils.getInstance(mContext, WeiboLoginManager.getInstance()
                .getAuthInfo()).startWeiboDetailPage(mWeiboBean.getIdstr(), mWeiboBean.getUser().getIdstr(), true);
    }

    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected int getStatusBarColor() {
        return getResources().getColor(R.color.md_red_A100);
    }

    @Override
    protected int setToolbarColor() {
        return getResources().getColor(R.color.md_red_A100);
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.weibo_main_content_layout, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weibo_content_menu, menu);
        return true;
    }

    @Override
    protected void onBackButtonEvent() {
        finish();
    }


    private void initData(WeiboBean res) {
        if (res.getUser() != null) {
            Glide.with(this).load(res.getUser().getProfile_image_url()).into(mAvater);
            mAuthor.setText(res.getUser().getName());
            mToolbarTitle.setText(res.getUser().getName());
        }
        mCreateTime.setText(res.getCreated_at());
        mContentView.setAutoLinkText(TextUtils.isEmpty(res.getText()) ? "" : res.getText());
        mResendTv.setText(res.getReposts_count() + "");
        mCommentCountTv.setText(res.getComments_count() + "");
        mGooAttitudeTv.setText(res.getAttitudes_count() + "");
        List<WeiboBean.PicUrlsBean> picUrlsBeans = res.getPic_urls();
        RecyclerView recyclerView = mImageRecycleView;
        String originPicDomen = WeiboUrlsUtils.getOriginPicHost(res.getOriginal_pic());
        if (picUrlsBeans != null && !picUrlsBeans.isEmpty()) {
            int limitPreivewSize = WeiboUrlsUtils.getLimitPreivewSize(picUrlsBeans);
            recyclerView.setVisibility(View.VISIBLE);
            WeiboImageRecycleViewAdapter adapter = (WeiboImageRecycleViewAdapter) recyclerView.getAdapter();
            List<String> urls = WeiboUrlsUtils.getOriginPicUrls(picUrlsBeans, originPicDomen, limitPreivewSize, MIDDLE);
            adapter.setImageUrls(urls);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

}
