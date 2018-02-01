package com.interestcontent.liudeyu.base.baseUiKit.aboutRecycleView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liudeyu on 2018/1/20.
 */
public class BaseRecyclerView extends RecyclerView {

    private GestureDetectorCompat gestureDetector;
    private BlankListener listener;

    public BaseRecyclerView(Context context) {
        super(context);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setBlankListener(BlankListener listener) {
        this.listener = listener;
        this.gestureDetector = new GestureDetectorCompat(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    public interface BlankListener {
        //这里加上RecycleView 是为了获取点击上一级的点击位置，这里比较奇淫巧技
        void onBlankClick(RecyclerView recyclerView);

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (gestureDetector != null && gestureDetector.onTouchEvent(e)) {
            View childView = findChildViewUnder(e.getX(), e.getY());
            if (childView == null) {
                listener.onBlankClick(this);
                return true;
            }
        }
        return super.onTouchEvent(e);
    }
}
