package com.interestcontent.liudeyu.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.example.commonlib.components.AbsFragment;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.weibo.data.WeiboLoginManager;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudeyu on 2017/12/25.
 */

public class SettingFragment extends AbsFragment {


    @BindView(R.id.theme_setting_lv)
    ListView mSettingList;
    @BindView(R.id.background_iv)
    ImageView mBackground;
    @BindView(R.id.avater_iv)
    ImageView mAvater;
    private boolean isFirstTime = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_setting, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            initViews();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isFirstTime) {
            initViews();
            isFirstTime = false;
        }
    }

    private void initViews() {
        mSettingList.setAdapter(new CommonAdapter<SettingItem>(getActivity(), R.layout.setting_item, !WeiboLoginManager.getInstance().isLogin ? provideNotAuthenSettings()
                : provideAuthenedSettings()) {
            @Override
            protected void convert(ViewHolder viewHolder, SettingItem item, int position) {
                ImageView icon = viewHolder.getView(R.id.icon_iv);
                icon.setImageResource(item.iconResId);
                TextView textView = viewHolder.getView(R.id.title_tv);
                textView.setText(item.title);
                viewHolder.getConvertView().setOnClickListener(item);
            }
        });
        if (WeiboLoginManager.getInstance().isLogin) {
            if (WeiboLoginManager.getInstance().getLoginUser() != null) {
                Glide.with(this).load(WeiboLoginManager.getInstance().getLoginUser().getAvatar_large()).into(mAvater);
                Glide.with(this).load(WeiboLoginManager.getInstance().getLoginUser().getCover_image_phone()).centerCrop()
                        .into(mBackground);
            }
        }
    }

    private List<SettingItem> provideAuthenedSettings() {

        List<SettingItem> settingItems = new ArrayList<>();
        SettingItem settingItem = new SettingItem(R.drawable.login_out, "退出当前微博") {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确定退出?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        WeiboLoginManager.getInstance().loginOutWeibo();
                        ToastUtils.showShort("退出成功");
                        dialogInterface.dismiss();
                        initViews();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        };
        settingItems.add(settingItem);
        return settingItems;

    }

    private List<SettingItem> provideNotAuthenSettings() {
        List<SettingItem> settingItems = new ArrayList<>();
        SettingItem settingItem = new SettingItem(R.drawable.login_user, "请登录微博,查看微博精彩") {
            @Override
            public void onClick(View view) {
                WeiboLoginManager.getInstance().startLoginAuthen(getActivity());
            }
        };
        settingItems.add(settingItem);
        return settingItems;
    }


}
