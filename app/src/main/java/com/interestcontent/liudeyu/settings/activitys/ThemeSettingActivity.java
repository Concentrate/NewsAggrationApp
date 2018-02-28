package com.interestcontent.liudeyu.settings.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.settings.ThemeDataManager;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/2/27.
 */

public class ThemeSettingActivity extends BaseActivity {

    private ListView mListView;

    public static void start(Context context) {
        Intent starter = new Intent(context, ThemeSettingActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.activity_setting_theme_layout, null, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int[] colorList = {R.color.md_red_200, R.color.md_red_300,
                R.color.md_red_400,
                R.color.md_green_200,
                R.color.md_green_300,
                R.color.md_green_400,
                R.color.md_blue_200,
                R.color.md_blue_300,
                R.color.md_blue_400,
                R.color.md_cyan_100,
                R.color.md_cyan_200,
                R.color.md_cyan_300,
                R.color.md_cyan_400,
                R.color.md_yellow_200,
                R.color.md_yellow_300,
                R.color.md_orange_100,
                R.color.md_orange_200,
                R.color.md_orange_300,
                R.color.md_orange_400,
                R.color.md_deep_orange_200,
                R.color.md_deep_orange_300,
                R.color.md_deep_orange_400,
                R.color.md_purple_100,
                R.color.md_purple_200,
                R.color.md_purple_300,
                R.color.md_grey_100,
                R.color.md_grey_200,
                R.color.md_grey_300};
        final List<Integer> array = new ArrayList<>();
        for (int i = 0; i < colorList.length; i++) {
            array.add(getResources().getColor(colorList[i]));
        }
        mToolbarTitle.setText("设置主题");
        mListView = findViewById(R.id.listview);
        final MyColorListAdapter myColorListAdapter = new MyColorListAdapter(this, R.layout.setting_theme_item_layout, array);
        mListView.setAdapter(myColorListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ThemeDataManager.getInstance().setThemeColor(array.get(i));
                myColorListAdapter.setData(array);
            }
        });

    }


    @Override
    protected void onBackButtonEvent() {
        finish();
    }


    private static class MyColorListAdapter extends CommonAdapter<Integer> {


        private List<Integer> mData;

        public MyColorListAdapter(Context context, int layoutId, List<Integer> datas) {
            super(context, layoutId, datas);
            mData = datas;
        }


        void setData(List<Integer> data) {
            mData = data;
            notifyDataSetChanged();
        }

        @Override
        protected void convert(ViewHolder viewHolder, Integer item, int position) {
            ImageView imageView = viewHolder.getView(R.id.color_iv);
            imageView.setBackgroundColor(item);
            final ImageView selectView = viewHolder.getView(R.id.select_iv);
            final int colorTmp = item;
            if (colorTmp == ThemeDataManager.getInstance().getThemeColorInt()) {
                selectView.setSelected(true);
            } else {
                selectView.setSelected(false);
            }
        }
    }
}
