package com.interestcontent.liudeyu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;

import com.example.commonlib.components.WeakHandler;

/**
 * Created by liudeyu on 2018/4/12.
 */

public class SplashActivity extends Activity implements WeakHandler.IHandler {

    private WeakHandler mWeakHandler = new WeakHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Message msg = new Message();
        msg.what = 1;
        mWeakHandler.sendMessageDelayed(msg, 1500);
    }

    @Override
    public void handleMsg(Message msg) {
        switch (msg.what) {
            case 1:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;// 拦截用户点击返回键
        }
        return super.onKeyDown(keyCode, event);
    }
}
