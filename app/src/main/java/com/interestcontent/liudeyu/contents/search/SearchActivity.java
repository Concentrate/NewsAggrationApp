package com.interestcontent.liudeyu.contents.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.contents.news.NewsFeedBaseFragment;
import com.interestcontent.liudeyu.settings.ThemeDataManager;

/**
 * Created by liudeyu on 2018/3/11.
 */

public class SearchActivity extends BaseActivity {


    FrameLayout mFrameLayout;

    private EditText mSearchEditText;
    private TextView mSearchTv;
    private ImageView mBackIv;


    public static void start(Context context) {
        Intent starter = new Intent(context, SearchActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFrameLayout = findViewById(R.id.parent_container);
        initToolbars();

    }


    @Override
    protected int getStatusBarColor() {
        return ThemeDataManager.getInstance().getThemeColorInt();
    }

    private void initToolbars() {
        mToolbarCustomContainer.removeAllViews();
        mToolbar.setBackgroundColor(getResources().getColor(R.color.white));
        View view = LayoutInflater.from(this).inflate(R.layout.search_actvity_search_bar_layout, null);
        mSearchEditText = view.findViewById(R.id.search_edit_text);
        mSearchTv = view.findViewById(R.id.sear_tv);
        mBackIv = view.findViewById(R.id.back_iv);
        mSearchTv.setOnClickListener(this);
        mBackIv.setOnClickListener(this);
        mToolbarCustomContainer.addView(view);
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    //处理事件
                    dealWithSearch();
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.sear_tv:
                dealWithSearch();
                break;
            case R.id.back_iv:
                onBackPressed();
                break;
        }
    }

    private void dealWithSearch() {
        String text = mSearchEditText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            ToastUtils.showShort("请输入关键词");
            return;
        }
        mFrameLayout.removeAllViews();
        Fragment fragment = new NewsFeedBaseFragment();
        ItemTab itemTab = new ItemTab(text.hashCode() + 1, text);
        Bundle bundle = NewsFeedBaseFragment.getTopicBundle(itemTab);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.parent_container,
                fragment).commitAllowingStateLoss();
        mSearchEditText.setText("");
        mSearchEditText.clearFocus();
        KeyboardUtils.hideSoftInput(this);

    }

    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.activity_search_layout, null);
    }
}
