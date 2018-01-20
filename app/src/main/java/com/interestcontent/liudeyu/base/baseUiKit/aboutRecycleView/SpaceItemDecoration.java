package com.interestcontent.liudeyu.base.baseUiKit.aboutRecycleView;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liudeyu on 2018/1/15.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int ItemLeftRightSpace;  //位移间距

    private int itemTopBottomSpace;

    public SpaceItemDecoration(int itemLeftRightSpace, int itemTopBottomSpace) {
        this.ItemLeftRightSpace = itemLeftRightSpace;
        this.itemTopBottomSpace = itemTopBottomSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int cloumnIndex = parent.getChildAdapterPosition(view) % 3;
        if (cloumnIndex != 0) {
            outRect.left = ItemLeftRightSpace;
        }
        if (parent.getChildAdapterPosition(view) >= 3) {
            outRect.top = itemTopBottomSpace;
        }
    }

}
