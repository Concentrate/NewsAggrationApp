package com.interestcontent.liudeyu.contents.news;

import com.interestcontent.liudeyu.base.baseComponent.AbsTopTabFragment;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.base.utils.RamUtil;
import com.interestcontent.liudeyu.settings.components.NewsTopicManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2017/12/25.
 */

public class NewsMainFragment extends AbsTopTabFragment {


    @Override
    protected List<ItemTab> provideItemTabs() {
        List<ItemTab> itemTabs = new ArrayList<>();
        List<String> titles = NewsTopicManager.getInstance().getNewsCatetory();
        List<Integer> integers = NewsTopicManager.getInstance().getNewsItemTabKeys();
        for (int i = 0; i < titles.size(); i++) {
            itemTabs.add(new ItemTab(integers.get(i),titles.get(i)));
        }
        return itemTabs;
    }

    @Override
    protected int viewpagerLimitNum() {
        if (RamUtil.getMaxMemoryCanGet() >= 192) {
            return 2;
        }
        return 1;
    }
}
