package com.interestcontent.liudeyu.weibo;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.data.WeiboDataManager;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

/**
 * Created by liudeyu on 2017/12/30.
 */

/**
 * 检测是否登录微博，并且给予一定操作
 */
public abstract class WeiboBaseTabFragment extends AbsFeedFragment implements View.OnClickListener {
    ImageView mLoginView;
    TextView mLoginText;

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            detectWeiboLoginState();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isResumed()) {
            detectWeiboLoginState();
        }
    }

    protected void detectWeiboLoginState() {
        if (!WeiboDataManager.getInstance().isLogin) {
            mSwipeRefreshLayout.setVisibility(View.GONE);
            mToolbarContainer.setVisibility(View.VISIBLE);
        } else {
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
            mToolbarContainer.removeAllViews();
            mToolbarContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public View addToolbar() {
        View loginView = LayoutInflater.from(getContext()).inflate(R.layout.weibo_login_layout, null);
        if (mLoginText == null || mLoginView == null) {
            mLoginText = loginView.findViewById(R.id.login_button_tv);
            mLoginView = loginView.findViewById(R.id.login_button);
            mLoginView.setOnClickListener(this);
            mLoginText.setOnClickListener(this);
        }
        return loginView;
    }

    @Override
    public void onRecyclerViewInitialized() {

    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onLoadMore() {

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
            case R.id.login_button_tv:
                WeiboLoginManager.getInstance().startLoginAuthen(getActivity());
                break;

        }
    }
}
