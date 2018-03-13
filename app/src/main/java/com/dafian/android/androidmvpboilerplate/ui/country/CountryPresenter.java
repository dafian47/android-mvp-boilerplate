package com.dafian.android.androidmvpboilerplate.ui.country;

import com.dafian.android.androidmvpboilerplate.base.BasePresenter;
import com.dafian.android.androidmvpboilerplate.data.DataManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Dafian on 13/03/18
 */
public class CountryPresenter extends BasePresenter<CountryView> {

    private Disposable disposable;

    private DataManager manager;

    CountryPresenter(DataManager manager) {
        this.manager = manager;
    }

    @Override
    public void attachView(CountryView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) disposable.dispose();
    }

    void getDataAll() {

        disposable = manager.getCountryList()
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribeOn(Schedulers.io())
                .subscribe(countryList -> {
                    if (isViewAttached()) {
                        getView().showCountryList(countryList);
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().showError(throwable.getMessage());
                    }
                });
    }
}
