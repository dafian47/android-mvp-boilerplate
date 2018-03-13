package com.dafian.android.androidmvpboilerplate.data;

import com.dafian.android.androidmvpboilerplate.data.entity.Country;

import java.util.List;

import io.realm.Realm;

/**
 * @author Dafian on 13/03/18
 */
public class DatabaseManager {

    void saveCountry(List<Country> countryList) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(countryList));
        realm.close();
    }

    List<Country> getCountryList() {
        Realm realm = Realm.getDefaultInstance();
        List<Country> countryList = realm.copyFromRealm(
                realm.where(Country.class).findAllSorted("name"));
        realm.close();
        return countryList;
    }
}
