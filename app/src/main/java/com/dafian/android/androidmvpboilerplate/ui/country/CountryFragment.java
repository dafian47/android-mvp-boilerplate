package com.dafian.android.androidmvpboilerplate.ui.country;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dafian.android.androidmvpboilerplate.R;
import com.dafian.android.androidmvpboilerplate.base.BaseFragment;
import com.dafian.android.androidmvpboilerplate.data.entity.Country;
import com.dafian.android.androidmvpboilerplate.util.extension.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * @author Dafian on 13/03/18
 */
public class CountryFragment extends BaseFragment implements CountryView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sw_country)
    SwipeRefreshLayout swCountry;
    @BindView(R.id.rv_country)
    RecyclerView rvCountry;

    private Unbinder unbinder;
    private CountryPresenter presenter;

    private List<Country> countryList;
    private CountryAdapter adapter;

    private Context context;

    public static CountryFragment newInstance() {
        CountryFragment fragment = new CountryFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CountryPresenter(getDataManager());
        presenter.attachView(this);

        Timber.tag("CountryFragment");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initView();
        initEvent();
        loadingData();
    }

    @Override
    public void showMessage(String message) {

        Timber.d("showMessage %s", message);

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        swCountry.setRefreshing(false);
    }

    @Override
    public void showError(String error) {

        Timber.d("showMessage %s", error);

        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
        swCountry.setRefreshing(false);
    }

    @Override
    public void showCountryList(List<Country> countryList) {

        this.countryList.addAll(countryList);
        adapter.notifyDataSetChanged();

        swCountry.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initView() {

        toolbar.setTitle(R.string.app_name);
        initToolbarNav(toolbar, true);

        countryList = new ArrayList<>();
        adapter = new CountryAdapter(context, countryList);

        rvCountry.setLayoutManager(new LinearLayoutManager(context));
        rvCountry.setItemAnimator(new DefaultItemAnimator());
        rvCountry.addItemDecoration(new DividerItemDecoration(
                context, DividerItemDecoration.VERTICAL));
        rvCountry.setAdapter(adapter);
    }

    private void initEvent() {

        swCountry.setOnRefreshListener(() -> {
            this.countryList.clear();
            loadingData();
        });

        rvCountry.addOnItemTouchListener(new RecyclerTouchListener(
                context, (view, position) -> {
                    // Detail Country
                    showMessage(countryList.get(position).getName());
                }));
    }

    private void loadingData() {

        swCountry.setRefreshing(true);
        presenter.getDataAll();
    }
}
