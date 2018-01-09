package com.interestcontent.liudeyu.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.AbsFragment;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.weibo.data.WeiboLoginManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liudeyu on 2017/12/25.
 */

public class SettingFragment extends AbsFragment {

    @BindView(R.id.cancel_wb_authen)
    Button mCancelWbAuthen;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_layout,null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    @OnClick(value = R.id.cancel_wb_authen)
    void onCancelWeiboAuthen(){
        SharePreferenceUtil.setStringPreference(getActivity(), SpConstants.WEIBO_AUTHEN_TOKEN,"");
        Toast.makeText(getActivity(), "cancel now", Toast.LENGTH_SHORT).show();
        WeiboLoginManager.getInstance().resetLoginState();
    }
}
