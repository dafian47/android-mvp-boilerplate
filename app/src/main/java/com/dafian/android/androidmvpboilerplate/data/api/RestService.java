package com.dafian.android.androidmvpboilerplate.data.api;

import com.dafian.android.androidmvpboilerplate.data.entity.Country;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Dafian on 13/03/18
 */
public interface RestService {

    @GET("v2/all")
    Observable<List<Country>> getCountryAll();
}
