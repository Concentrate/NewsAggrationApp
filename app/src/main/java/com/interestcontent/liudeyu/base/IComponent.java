package com.interestcontent.liudeyu.base;

import android.content.Intent;

/**
 * Created by liudeyu on 2017/12/23.
 */

public interface IComponent {

    public void startActivityForResult(Intent intent, int requestCode);
    public boolean isViewValid();
    public boolean isActive();

}

