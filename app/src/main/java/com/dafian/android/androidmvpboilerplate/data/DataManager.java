package com.dafian.android.androidmvpboilerplate.data;

import com.dafian.android.androidmvpboilerplate.data.api.RestService;
import com.dafian.android.androidmvpboilerplate.data.entity.Country;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Dafian on 13/03/18
 */
public class DataManager {

    private RestService restService;
    private DatabaseManager dbManager;

    public DataManager(RestService restService, DatabaseManager dbManager) {
        this.restService = restService;
        this.dbManager = dbManager;
    }

    public Observable<List<Country>> getCountryList() {
        return Observable.mergeDelayError(
                getCountryFromDatabase(),
                getCountryFromNetwork()
        );
    }

    private Observable<List<Country>> getCountryFromDatabase() {
        return Observable.just(dbManager.getCountryList())
                .filter(countries -> countries.size() != 0);
    }

    private Observable<List<Country>> getCountryFromNetwork() {
        return restService.getCountryAll()
                .doOnNext(countries -> dbManager.saveCountry(countries));
    }
}
