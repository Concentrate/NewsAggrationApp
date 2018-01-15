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
        if (parent.getChildAdapterPosition(view) % 3 == 0) {
            outRect.left = 0; //第一列左边贴边
        } else {
            if (parent.getChildAdapterPosition(view) % 3 == 1) {
                outRect.left = ItemLeftRightSpace;//第二列移动一个位移间距
            } else {
                outRect.left = ItemLeftRightSpace * 2;//由于第二列已经移动了一个间距，所以第三列要移动两个位移间距就能右边贴边，且item间距相等
            }
        }
        if (parent.getChildAdapterPosition(view) >= 3) {
            outRect.top = itemTopBottomSpace;
        } else {
            outRect.top = 0;
        }
    }

}
