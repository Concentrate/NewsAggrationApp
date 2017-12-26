package com.interestcontent.liudeyu.base.baseUiKit;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by liudeyu on 2017/12/25.
 */

public class SSViewPager extends ViewPager {
    public SSViewPager(Context context) {
        super(context);
    }

    public SSViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return super.onTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
