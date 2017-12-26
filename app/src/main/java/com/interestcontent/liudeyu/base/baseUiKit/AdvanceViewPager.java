package com.interestcontent.liudeyu.base.baseUiKit;

/**
 * Created by liudeyu on 2017/12/26.
 */

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.interestcontent.liudeyu.base.WeakHandler;


public class AdvanceViewPager extends SSViewPager implements WeakHandler.IHandler {

    private boolean canScroll;
    private GestureDetector mGestureDetector;
    private Runnable mCheckLongPressRunnable;
    private WeakHandler mHandler = new WeakHandler(Looper.getMainLooper(), this);

    private float mDownX, mDownY;
    private float mMoveMargin = 10;

    private void initGesture() {
        mGestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                Log.e("viewpager", "onDown");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.e("viewpager", "onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.e("viewpager", "onSingleTapUp");
                if (mOnSingleTapListener != null) {
                    mOnSingleTapListener.singleTap(e.getX(), e.getY());
                }
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.e("viewpager", "onScroll");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.e("viewpager", "long press");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.e("viewpager", "onFling");
                return false;
            }
        });
    }

    public AdvanceViewPager(Context context) {
        super(context);
        canScroll = true;
        initGesture();
    }

    public AdvanceViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        canScroll = true;
        initGesture();
    }

    @Override
    public boolean onInterceptTouchEvent(final MotionEvent ev) {
        if (canScroll) {
            try {
                if(false) {
                    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                        if (mCheckLongPressRunnable != null) {
                            mHandler.removeCallbacks(mCheckLongPressRunnable);
                        }
                        mCheckLongPressRunnable = new Runnable() {
                            @Override
                            public void run() {
//							Log.e("viewpager", "long press");
                                mCheckLongPressRunnable = null;
                                if (mLongPressListener != null) {
                                    mLongPressListener.longPress(ev.getX(), ev.getY());
                                }
                            }
                        };
                        mDownX = ev.getX();
                        mDownY = ev.getY();
                        mHandler.postDelayed(mCheckLongPressRunnable, 600);
                    } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                        float x = ev.getX();
                        float y = ev.getY();
                        if (Math.abs(x - mDownX) >= mMoveMargin ||
                                Math.abs(y - mDownY) >= mMoveMargin) {
                            mHandler.removeCallbacks(mCheckLongPressRunnable);
                            mCheckLongPressRunnable = null;
//						Log.e("viewpager", "scroll too large");
                        }
                    } else {
//					Log.e("viewpager", "action else");
                        mHandler.removeCallbacks(mCheckLongPressRunnable);
                        mCheckLongPressRunnable = null;
                    }
                }
                mGestureDetector.onTouchEvent(ev);
                return super.onInterceptTouchEvent(ev);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (canScroll) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    public void toggleLock() {
        canScroll = !canScroll;
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    public boolean isCanScroll() {
        return canScroll;
    }

    @Override
    public void handleMsg(Message msg) {
        Log.e("viewpager", msg.toString());
    }

    public interface OnLongPressListener {
        void longPress(float x, float y);
    }
    public interface OnSingleTapListener {
        void singleTap(float x, float y);
    }
    private OnLongPressListener mLongPressListener;
    private OnSingleTapListener mOnSingleTapListener;

    public void setLongPressListener(OnLongPressListener longPressListener) {
        mLongPressListener = longPressListener;
    }

    public void setOnSingleTapListener(OnSingleTapListener onSingleTapListener) {
        mOnSingleTapListener = onSingleTapListener;
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        if(mGestureDetector != null) {
            mGestureDetector.setOnDoubleTapListener(onDoubleTapListener);
        }
    }
}
