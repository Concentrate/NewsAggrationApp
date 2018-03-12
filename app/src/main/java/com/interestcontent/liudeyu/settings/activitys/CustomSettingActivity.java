package com.interestcontent.liudeyu.settings.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.settings.ThemeDataManager;
import com.interestcontent.liudeyu.settings.components.NewsSourceFilterManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liudeyu on 2018/3/12.
 */

public class CustomSettingActivity extends BaseActivity {

    @BindView(R.id.news_source_switch)
    SwitchCompat mSwitchCompat;

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomSettingActivity.class);
        context.startActivity(starter);

    }

    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.custom_setting_activity_layout, null);
    }

    @Override
    protected int provideToolbarColor() {
        return getResources().getColor(R.color.white);
    }

    @Override
    protected int getStatusBarColor() {
        return ThemeDataManager.getInstance().getThemeColorInt();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mSwitchCompat.setChecked(!NewsSourceFilterManager.getInstance().isNewsOriginWebFilter());
        mToolbarTitle.setText("通用设置");
    }

    @OnClick(value = {R.id.news_source_switch})
    void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.news_source_switch:
                if (mSwitchCompat.isChecked() && SharePreferenceUtil.getBooleanPreference(this,
                        SpConstants.NEWS_SOURCE_DIALOG_FIRST_SHOW, true)) {
                    SharePreferenceUtil.setBooleanPreference(this, SpConstants.NEWS_SOURCE_DIALOG_FIRST_SHOW, false);
                    showAlertDialog();
                } else {
                    NewsSourceFilterManager.getInstance().setNewsOriginWebFilter(!mSwitchCompat.isChecked());
                }
                break;
        }
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this).setMessage("广泛采取新闻来源，可能造成有些新闻阅读体验不那么好,造成一些新闻重复，但是新闻资源却能变多").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                NewsSourceFilterManager.getInstance().setNewsOriginWebFilter(!mSwitchCompat.isChecked());
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }


}
