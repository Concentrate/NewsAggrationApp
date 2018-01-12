package com.interestcontent.liudeyu.weibo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.AbsTopTabFragment;
import com.interestcontent.liudeyu.weibo.data.WeiboLoginManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiboMainFragment extends AbsTopTabFragment {

    ImageView mLoginView;
    TextView mLoginText;
    private MyClickListener mMyClickListener;
    private View mloginView;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLoginState();
    }

    private void initLoginState() {
        if (mLoginView == null) {
            mloginView = LayoutInflater.from(getContext()).inflate(R.layout.weibo_login_layout, mEmptyContainer);
            mLoginText = mloginView.findViewById(R.id.login_button_tv);
            mLoginView = mloginView.findViewById(R.id.login_button);
            if (mMyClickListener == null) {
                mMyClickListener = new MyClickListener(this);
            }
            mLoginView.setOnClickListener(mMyClickListener);
            mLoginText.setOnClickListener(mMyClickListener);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!WeiboLoginManager.getInstance().isLogin) {
            hideContentAndShowEmptyView();
        } else {
            showContentHideEmptyView();
        }
    }

    @Override
    protected int viewpagerLimitNum() {
        return 3;
    }

    @Override
    protected List<Fragment> provideContents() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new WeiboFollowFragment());
        fragments.add(new WeiboFollowFragment());
        fragments.add(new WeiboFollowFragment());
        fragments.add(new WeiboFollowFragment());
        return fragments;
    }

    @Override
    protected List<String> provideTitles() {
        String[] tabName = getActivity().getResources().getStringArray(R.array.weibo_top_tab);
        return Arrays.asList(tabName);
    }

    private static class MyClickListener implements View.OnClickListener {
        private Fragment mFragment;

        private MyClickListener(Fragment fragment) {
            mFragment = fragment;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.login_button:
                case R.id.login_button_tv:
                    WeiboLoginManager.getInstance().startLoginAuthen(mFragment.getActivity());
                    break;

            }
        }
    }
}
