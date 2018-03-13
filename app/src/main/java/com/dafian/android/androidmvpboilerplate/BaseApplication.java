package com.dafian.android.androidmvpboilerplate;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.yokeyword.fragmentation.Fragmentation;
import timber.log.Timber;

/**
 * @author Dafian on 13/03/18
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Configuration Realm
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build());

        // Configuration Fragmentation
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .handleException(e -> {
                    // Handle error fragment stack
                    // Report to Crash reporting

                })
                .install();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
