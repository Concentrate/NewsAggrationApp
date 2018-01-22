package com.interestcontent.liudeyu.base.thread;

/**
 * Created by liudeyu on 2018/1/21.
 */

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TaskManager {

    private final static int DEFAULT_THREAD_POOL_SIZE = 4;
    private final static Executor DEFAULT_EXECUTOR = new ScheduledThreadPoolExecutor(DEFAULT_THREAD_POOL_SIZE);

    public static TaskManager sInst;
    private static Handler handlerMain;

    public static synchronized TaskManager inst() {
        if (sInst == null) {
            sInst = new TaskManager();
        }
        return sInst;
    }

    private static Runnable async(final Handler handler, final Callable c, final int what) {
        return new Runnable() {
            public void run() {
                if (handler == null) {
                    try {
                        c.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                }

                Message m = handler.obtainMessage(what);
                try {
                    m.obj = c.call();
                } catch (Exception e) {
                    m.obj = e;
                }
                handler.sendMessage(m);
            }
        };
    }

    public static class TaskManagerConfig {

        private Executor mExecutor;

        public TaskManagerConfig() {
        }

        public Executor getExecutor() {
            return mExecutor;
        }

        public TaskManagerConfig setExecutor(Executor executor) {
            if (executor == null) {
                executor = TaskManager.DEFAULT_EXECUTOR;
            }
            mExecutor = executor;
            return this;
        }
    }

    private boolean mInit = false;
    private Executor mExecutor;

    public void init(TaskManagerConfig config) {
        mExecutor = config.getExecutor();
        handlerMain = new Handler(Looper.getMainLooper());
        mInit = true;
    }

    private static void checkInited(TaskManager manager) {
        if (!manager.mInit) {
            throw new IllegalStateException("TaskManager not init");
        }
    }

    public void commit(Handler handler, Callable callable, int msgWhat) {
        checkInited(this);
        mExecutor.execute(async(handler, callable, msgWhat));
    }

    public void commit(Callable callable) {
        commit(null, callable, 0);
    }

    public void postMain(Runnable r) {
        checkInited(this);

        if (handlerMain != null) {
            handlerMain.post(r);
        }
    }

}
