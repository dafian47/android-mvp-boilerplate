package com.dafian.android.androidmvpboilerplate.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dafian.android.androidmvpboilerplate.data.DataManager;
import com.dafian.android.androidmvpboilerplate.data.DatabaseManager;
import com.dafian.android.androidmvpboilerplate.data.api.RestFactory;
import com.dafian.android.androidmvpboilerplate.data.api.RestService;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author Dafian on 13/03/18
 */
public abstract class BaseActivity extends SupportActivity {

    private RestService restService;
    private DatabaseManager databaseManager;
    private DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public RestService getRestService() {
        if (restService == null) {
            restService = RestFactory.create();
        }
        return restService;
    }

    public DatabaseManager getDatabaseManager() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public DataManager getDataManager() {
        if (dataManager == null) {
            dataManager = new DataManager(getRestService(), getDatabaseManager());
        }
        return dataManager;
    }
}
