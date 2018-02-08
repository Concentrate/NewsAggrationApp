package com.interestcontent.liudeyu.contents.news.cells;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.specificComponent.BrowseActivity;
import com.interestcontent.liudeyu.contents.news.beans.NewsTechnoBean;
import com.interestcontent.liudeyu.base.constants.FeedConstants;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;
import com.zhouwei.rvadapterlib.base.RVBaseCell;
import com.zhouwei.rvadapterlib.base.RVBaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liudeyu on 2018/2/5.
 */

public class MutilepleImageNewsCell extends RVBaseCell<NewsTechnoBean> {

    private Context mContext;
    private Fragment mFragment;

    public MutilepleImageNewsCell(Context context, NewsTechnoBean newsTechnoBean) {
        super(newsTechnoBean);
        mContext = context;
    }

    public MutilepleImageNewsCell setFragment(Fragment fragment) {
        mFragment = fragment;
        return this;
    }

    public MutilepleImageNewsCell(NewsTechnoBean newsTechnoBean) {
        super(newsTechnoBean);
    }

    @Override
    public int getItemType() {
        return FeedConstants.NEWS_MULTIPLE_IMAGE_TYPE;
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_multiple_image_display_cell, parent, false);
        return new RVBaseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        holder.getTextView(R.id.news_title_tv).setText(mData.getTitle());
        holder.getTextView(R.id.news_publish_source_tv).setText(mData.getPosterScreenName());
        holder.getTextView(R.id.news_comment_count_tv).setText("评论数:" + mData.getCommentCount());
        long publishTime = mData.getPublishDate();
        Date date = new Date(publishTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMMdd日");
        holder.getTextView(R.id.news_publish_time_tv).setText(dateFormat.format(date));
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = BrowseActivity.getIntent(mData.getUrl(), true);
                intent.setClass(mContext, BrowseActivity.class);
                mContext.startActivity(intent);
            }
        });
        RecyclerView recycleView = (RecyclerView) holder.getView(R.id.news_image_preview_listview);
        MultipleImageRecycleAdapter adapter = new MultipleImageRecycleAdapter(mContext, mData.getImageUrls());
        adapter.setFragment(mFragment);
        recycleView.setAdapter(adapter);
        VerticalDividerItemDecoration verticalDividerItemDecoration = new VerticalDividerItemDecoration.Builder(mContext)
                .size(SizeUtils.dp2px(6)).colorResId(R.color.white).build();
        recycleView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        recycleView.addItemDecoration(verticalDividerItemDecoration);
    }
}
