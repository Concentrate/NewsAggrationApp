package com.interestcontent.liudeyu.base.mvp;

public interface IMvpView<T> {

    void onQueryResult(T result);

    void onQueryError(Exception e);
}
