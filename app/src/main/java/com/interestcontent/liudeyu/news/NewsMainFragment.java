package com.interestcontent.liudeyu.news;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.AbsTopTabFragment;
import com.interestcontent.liudeyu.base.tabs.ItemTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2017/12/25.
 */

public class NewsMainFragment extends AbsTopTabFragment {


    @Override
    protected List<ItemTab> provideItemTabs() {
        List<ItemTab> itemTabs = new ArrayList<>();
//        itemTabs.add(new ItemTab(ItemTab.NEWS_TECHNOLEGE, "科技"));
        // TODO: 2018/2/4 这里暂时做测试看看效果

        String[] nameArray = getActivity().getResources().getStringArray(R.array.news_top_tab_name);
        int[] itemTabKeyArray = getActivity().getResources().getIntArray(R.array.news_item_tab);
        for (int i = 0; i < nameArray.length; i++) {
            itemTabs.add(new ItemTab(itemTabKeyArray[i], nameArray[i]));
        }
        return itemTabs;
    }

    @Override
    protected int viewpagerLimitNum() {
        return 2;
    }
}
