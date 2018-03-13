package com.dafian.android.androidmvpboilerplate.base;

import retrofit2.HttpException;

/**
 * @author Dafian on 13/03/18
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {

    private T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    protected boolean isEmptyData(Throwable throwable) {

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            int errorCode = httpException.code();

            return errorCode == 404;
        }

        return false;
    }

    protected boolean isTokenAvailable(Throwable throwable) {

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            int errorCode = httpException.code();

            return errorCode != 401;
        }

        return true;
    }

    public T getView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new BaseViewNotAttachedException();
    }

    public static class BaseViewNotAttachedException extends RuntimeException {

        BaseViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
