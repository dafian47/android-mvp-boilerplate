package com.dafian.android.androidmvpboilerplate.base;

/**
 * @author Dafian on 13/03/18
 */
public interface Presenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
