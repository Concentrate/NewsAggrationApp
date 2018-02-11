package com.interestcontent.liudeyu.contents.zhihu.contents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.gson.Gson;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.base.baseUiKit.aboutGlide.GlideRoundTransform;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.contents.zhihu.bean.StoriesBean;
import com.interestcontent.liudeyu.contents.zhihu.bean.ZhihuThemeContentRequest;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by liudeyu on 2018/2/9.
 */

public class ZhihuThemeGarallyActivity extends BaseActivity {

    public static final String THEME_ID = "THEME_ID";

    private Context mContext;
    private ImageView mImageView;
    private TextView mTitle;

    public static void start(Context context, String id) {
        Intent starter = new Intent(context, ZhihuThemeGarallyActivity.class);
        starter.putExtra(THEME_ID, id);
        context.startActivity(starter);
    }


    @BindView(R.id.recycleview)
    PullLoadMoreRecyclerView mPullReflashRv;


    @Override
    protected boolean isUseToolBar() {
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mContext = this;
        Intent intent = getIntent();
        reInitUiState();
        if (intent != null) {
            String themeId = intent.getStringExtra(THEME_ID);
            startRequestData(themeId);
        }
    }

    private void reInitUiState() {
        mToolbar.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        mImageView = new CircleImageView(this);
        int imageSize = getResources().getDimensionPixelSize(R.dimen.wb_avater_size);
        mImageView.setLayoutParams(new LinearLayout.LayoutParams(imageSize, imageSize));
        linearLayout.addView(mImageView);
        mTitle = new TextView(this);
        LinearLayout.LayoutParams tvlayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvlayoutParams.leftMargin = SizeUtils.dp2px(10);
        mTitle.setLayoutParams(tvlayoutParams);
        mTitle.setTextSize(12);
        mTitle.setTextColor(getResources().getColor(R.color.md_blue_grey_800));
        linearLayout.addView(mTitle);
        HorizontalDividerItemDecoration.Builder builder = new HorizontalDividerItemDecoration.Builder(this);
        HorizontalDividerItemDecoration itemDecoration = builder.margin(SizeUtils.dp2px(10))
                .size(SizeUtils.dp2px(1)).colorResId(R.color.md_grey_100).build();
        mPullReflashRv.getRecyclerView().addItemDecoration(itemDecoration);
        mPullReflashRv.getRecyclerView().setItemAnimator(new SlideInDownAnimator());
        mToolbar.addView(linearLayout);
        mPullReflashRv.getRecyclerView().setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void startRequestData(String themeId) {
        OkHttpUtils.get().url(Constants.ZHIHU_THEME_ID_LIST_BASE + themeId).build().execute(new Callback<ZhihuThemeContentRequest>() {

            @Override
            public ZhihuThemeContentRequest parseNetworkResponse(Response response, int id) throws Exception {
                return new Gson().fromJson(response.body().string(), ZhihuThemeContentRequest.class);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ZhihuThemeContentRequest response, int id) {
                if (response != null) {
                    getDataSuccess(response);
                }

            }
        });
    }

    private void getDataSuccess(ZhihuThemeContentRequest response) {
        mTitle.setText(response.getName());
        if (!TextUtils.isEmpty(response.getImage())) {
            Glide.with(this).load(response.getImage()).centerCrop().into(mImageView);
        }
        mPullReflashRv.setAdapter(new CommonAdapter<StoriesBean>(this, R.layout.zhihu_feed_cell,
                response.getStories()) {
            @Override
            protected void convert(ViewHolder holder, final StoriesBean storiesBean, int position) {
                TextView textView1 = holder.getView(R.id.title);
                textView1.setText(storiesBean.getTitle());
                ImageView imageView1 = holder.getView(R.id.image_iv);
                if (storiesBean.getImages() != null && !storiesBean.getImages().isEmpty()) {
                    Glide.with(ZhihuThemeGarallyActivity.this).load(storiesBean.getImages().get(0))
                            .transform(new GlideRoundTransform(mContext, 5), new CenterCrop(mContext)).into(imageView1);
                } else {
                    imageView1.setVisibility(View.GONE);
                }
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ZhihuContentActivity.start(mContext, storiesBean.getId() + "");
                    }
                });
            }

        });
        mPullReflashRv.setHasMore(false);
        mPullReflashRv.setSwipeRefreshEnable(false);
        mPullReflashRv.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.activity_zhihu_theme_garally_layout, null);
    }


}
