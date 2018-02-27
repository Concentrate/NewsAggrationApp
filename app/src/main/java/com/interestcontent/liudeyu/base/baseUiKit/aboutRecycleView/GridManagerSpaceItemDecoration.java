package com.interestcontent.liudeyu.base.baseUiKit.aboutRecycleView;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.commonlib.utils.Logger;

/**
 * Created by liudeyu on 2018/1/15.
 */

public class GridManagerSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int itemLeftRightSpace;  //位移间距

    private int itemTopBottomSpace;

    public GridManagerSpaceItemDecoration(int itemLeftRightSpace, int itemTopBottomSpace) {
        this.itemLeftRightSpace = itemLeftRightSpace;
        this.itemTopBottomSpace = itemTopBottomSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = gridLayoutManager.getSpanCount();
        int cloumnIndex = parent.getChildAdapterPosition(view) % spanCount;
        if (cloumnIndex != 0) {
            outRect.left = itemLeftRightSpace;
        }
        if (parent.getChildAdapterPosition(view) >= spanCount) {
            outRect.top = itemTopBottomSpace;
        }
        outRect.right = outRect.bottom = 0;
        Logger.d("GridManagerSpaceItemDecoration", String.format("cloumnIndex: %d,and res is %d %d %d %d ", cloumnIndex, outRect.left, outRect.top,
                outRect.right, outRect.bottom));

        // TODO: 2018/2/1 下面注释效果是，第一个上没space，下面都有space,然后左右都有space
//        int topBottom = itemTopBottomSpace;
//        int leftRight = itemLeftRightSpace;
//        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
//        //判断总的数量是否可以整除
//        int totalCount = layoutManager.getItemCount();
//        int surplusCount = totalCount % layoutManager.getSpanCount();
//        int childPosition = parent.getChildAdapterPosition(view);
//        if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {//竖直方向的
//            if (surplusCount == 0 && childPosition > totalCount - layoutManager.getSpanCount() - 1) {
//                //后面几项需要bottom
//                outRect.bottom = topBottom;
//            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
//                outRect.bottom = topBottom;
//            }
//            if ((childPosition + 1) % layoutManager.getSpanCount() == 0) {//被整除的需要右边
//                outRect.right = leftRight;
//            }
//            outRect.top = topBottom;
//            outRect.left = leftRight;
//        } else {
//            if (surplusCount == 0 && childPosition > totalCount - layoutManager.getSpanCount() - 1) {
//                //后面几项需要右边
//                outRect.right = leftRight;
//            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
//                outRect.right = leftRight;
//            }
//            if ((childPosition + 1) % layoutManager.getSpanCount() == 0) {//被整除的需要下边
//                outRect.bottom = topBottom;
//            }
//            outRect.top = topBottom;
//            outRect.left = leftRight;
//        }
//
//    }

    }

}
