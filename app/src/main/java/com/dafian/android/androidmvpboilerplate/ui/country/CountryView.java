package com.dafian.android.androidmvpboilerplate.ui.country;

import com.dafian.android.androidmvpboilerplate.base.BaseView;
import com.dafian.android.androidmvpboilerplate.data.entity.Country;

import java.util.List;

/**
 * @author Dafian on 13/03/18
 */
public interface CountryView extends BaseView {

    void showCountryList(List<Country> countryList);
}
