package com.dafian.android.androidmvpboilerplate.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.dafian.android.androidmvpboilerplate.R;
import com.dafian.android.androidmvpboilerplate.data.DataManager;
import com.dafian.android.androidmvpboilerplate.data.DatabaseManager;
import com.dafian.android.androidmvpboilerplate.data.api.RestFactory;
import com.dafian.android.androidmvpboilerplate.data.api.RestService;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Dafian on 13/03/18
 */
public abstract class BaseFragment extends SupportFragment {

    private RestService restService;
    private DatabaseManager databaseManager;
    private DataManager dataManager;

    protected OnFragmentOpenDrawerListener openDrawerListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentOpenDrawerListener) {
            openDrawerListener = (OnFragmentOpenDrawerListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        openDrawerListener = null;
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

    protected void initToolbarNav(Toolbar toolbar, boolean isHome) {

        if (isHome) {
            toolbar.setNavigationIcon(R.drawable.ic_menu);
            toolbar.setNavigationOnClickListener(v -> {
                if (openDrawerListener != null) {
                    openDrawerListener.onOpenDrawer();
                }
            });

        } else {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(v -> pop());
        }
    }

    public interface OnFragmentOpenDrawerListener {

        void onOpenDrawer();
    }
}
