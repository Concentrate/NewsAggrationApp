package com.interestcontent.liudeyu.base.mvp;

import android.os.Handler;
import android.os.Message;

import com.example.commonlib.components.WeakHandler;
import com.interestcontent.liudeyu.base.thread.TaskManager;

import java.util.concurrent.Callable;

/**
 * Created by liudeyu on 2018/1/21.
 */

public abstract class MvpPresenter<T, V extends IMvpView<T>> implements WeakHandler.IHandler {
    protected static final int MSG_EXECUTE = 1001;

    protected V mMvpView;
    protected boolean isLoading;
    protected Handler mHandler = new WeakHandler(this);

    public boolean execute(final Object... params) {
        return executeWithForceTag(false, params);
    }

    public boolean executeWithForceTag(boolean isForce, final Object... params) {
        if (isLoading && !isForce) {
            return false;
        }
        isLoading = true;
        TaskManager.inst().commit(mHandler, new Callable() {
            @Override
            public Object call() throws Exception {
                return doWork(params);
            }
        }, MSG_EXECUTE);
        return true;
    }

    public abstract T doWork(Object... params) throws Exception;

    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    public void detachView() {
        mMvpView = null;
    }

    @Override
    public void handleMsg(Message msg) {
        if (msg.what == MSG_EXECUTE) {
            isLoading = false;
            if (mMvpView != null) {
                if (msg.obj instanceof Exception) {
                    mMvpView.onQueryError((Exception) msg.obj);
                } else {
                    mMvpView.onQueryResult((T) msg.obj);
                }
            }
        }
    }
}