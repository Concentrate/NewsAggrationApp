package com.interestcontent.liudeyu.weibo.contents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.base.baseUiKit.aboutRecycleView.SpaceItemDecoration;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.interestcontent.liudeyu.base.thread.TaskManager;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;
import com.interestcontent.liudeyu.weibo.feeds.OnWeiboOperationBottomClickListener;
import com.interestcontent.liudeyu.weibo.feeds.WeiboImageRecycleViewAdapter;
import com.interestcontent.liudeyu.weibo.util.WeiboUrlsUtils;
import com.luseen.autolinklibrary.AutoLinkMode;
import com.luseen.autolinklibrary.AutoLinkOnClickListener;
import com.luseen.autolinklibrary.AutoLinkTextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.interestcontent.liudeyu.weibo.util.WeiboUrlsUtils.MIDDLE;

/**
 * Created by liudeyu on 2018/1/20.
 */

/**
 * 因为api 缘故，根本拿不到数据，本地的展示由此先不用，废弃了
 */
@Deprecated
public class WeiboContentActivity extends BaseActivity implements IMvpView<WeiboBean> {

    private static final String WEIBO_ID = "WEIBO_ID";


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
    private WeiboContentPresenter mWeiboContentPresenter;
    private Context mContext;
    private String mWeiboId;
    private Handler mHandler = new Handler() {

    };
    private WeiboImageRecycleViewAdapter mImageRecycleAdapter;

    public static void start(Context context, @NotNull String weiboId) {
        Intent starter = new Intent(context, WeiboContentActivity.class);
        starter.putExtra(WEIBO_ID, weiboId);
        context.startActivity(starter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWeiboContentPresenter != null) {
            mWeiboContentPresenter.detachView();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mContext = this;
        Intent intent = getIntent();
        if (intent != null) {
            mWeiboId = intent.getStringExtra(WEIBO_ID);
            startRequestData();

        }
        initViews();
    }

    private void startRequestData() {
        if (mWeiboContentPresenter == null) {
            mWeiboContentPresenter = new WeiboContentPresenter();
        }
        mWeiboContentPresenter.attachView(this);
        if (!TextUtils.isEmpty(mWeiboId)) {
            mWeiboContentPresenter.execute(mWeiboId);
            TaskManager.inst().commit(new Callable() {
                @Override
                public Object call() throws Exception {

                    // TODO: 2018/1/23   comment api
                    return "";
                }
            });

        }

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
        int itemDecortWidth = (int) ((ScreenUtils.getScreenWidth() - mContext.getResources().
                getDimension(R.dimen.wb_cell_image_size) * 3) / 2);
        recyclerView.addItemDecoration(new SpaceItemDecoration(itemDecortWidth, SizeUtils.dp2px(10)));
        mImageRecycleAdapter = new WeiboImageRecycleViewAdapter(mContext, new ArrayList<String>());
        recyclerView.setAdapter(mImageRecycleAdapter);

        OnWeiboOperationBottomClickListener bottomClickListener = new OnWeiboOperationBottomClickListener();
        mResendLayout.setOnClickListener(bottomClickListener);
        mGoodAttitudeLayout.setOnClickListener(bottomClickListener);
        mCommentLayout.setOnClickListener(bottomClickListener);
        bottomClickListener.setNecesseryViewTag(mWeiboId, mResendLayout, mGoodAttitudeLayout, mCommentLayout);
    }

    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected int getStatusBarColor() {
        return getResources().getColor(R.color.md_blue_grey_300);
    }

    @Override
    protected int setToolbarColor() {
        return getResources().getColor(R.color.md_pink_200);
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.weibo_main_content_layout, null);
    }

    @Override
    protected void onBackButtonEvent() {
        finish();
    }

    @Override
    public void onQueryResult(WeiboBean result) {
        initData(result);
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

    @Override
    public void onQueryError(Exception e) {
        ToastUtils.showShort("网络错误");
    }
}
