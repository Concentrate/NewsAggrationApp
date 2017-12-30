package com.interestcontent.liudeyu.weibo;


import android.support.v4.app.Fragment;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.AbsTopTabFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiboMainFragment extends AbsTopTabFragment {


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
}
