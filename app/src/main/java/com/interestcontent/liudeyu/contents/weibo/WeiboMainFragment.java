package com.interestcontent.liudeyu.contents.weibo;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.AbsTopTabFragment;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.base.utils.RamUtil;
import com.interestcontent.liudeyu.contents.weibo.data.WeiboLoginManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiboMainFragment extends AbsTopTabFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initState();
    }

    private void initState() {

    }


    @Override
    protected int provideInitShowItem() {
        if (!WeiboLoginManager.getInstance().isLogin) {
            return 2;
        } else {
            return super.provideInitShowItem();
        }
    }

    @Override
    protected int provideTabMode() {
        return TabLayout.MODE_FIXED;
    }

    @Override
    protected List<ItemTab> provideItemTabs() {
        List<ItemTab> itemTabs = new ArrayList<>();
        String[] weiboTabName = getResources().getStringArray(R.array.weibo_top_tab);
        int[] tabKey = new int[]{ItemTab.WEIBO_SUB_FOLLOW, ItemTab.WEIBO_SUB_HOT, ItemTab.WEIBO_SUB_MESSAGE, ItemTab.WEIBO_SUB_MY_WEIBO_PAGE};
        for (int i = 0; i < weiboTabName.length; i++) {
            itemTabs.add(new ItemTab(tabKey[i], weiboTabName[i]));
        }
        return itemTabs;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!WeiboLoginManager.getInstance().isLogin) {
            ToastUtils.setBgColor(getActivity().getResources().getColor(R.color.md_blue_200));
            ToastUtils.showShort("请关联应用");
        }
    }

    @Override
    protected int viewpagerLimitNum() {
        if(RamUtil.getMaxMemoryCanGet()>=192) {
            return 2;
        }
        return 1;
    }


    private static class MyClickListener implements View.OnClickListener {
        private Fragment mFragment;

        private MyClickListener(Fragment fragment) {
            mFragment = fragment;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {


            }
        }
    }
}
