package com.dafian.android.androidmvpboilerplate.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.dafian.android.androidmvpboilerplate.R;
import com.dafian.android.androidmvpboilerplate.base.BaseActivity;
import com.dafian.android.androidmvpboilerplate.base.BaseFragment;
import com.dafian.android.androidmvpboilerplate.ui.country.CountryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        BaseFragment.OnFragmentOpenDrawerListener {

    @BindView(R.id.content_frame)
    FrameLayout contentFrame;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Timber.tag("MainActivity");

        BaseFragment fragment = findFragment(CountryFragment.class);
        if (fragment == null) {
            loadRootFragment(contentFrame.getId(), CountryFragment.newInstance());
        }

        initView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawers();
        drawerLayout.postDelayed(() -> {

            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {

                start(CountryFragment.newInstance(), SupportFragment.SINGLETASK);

            }

            if (navigationView.getMenu().findItem(itemId) != null) {
                navigationView.getMenu().findItem(itemId).setChecked(true);
            }

        }, 300);

        return true;
    }

    @Override
    public void onOpenDrawer() {

        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressedSupport() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            ISupportFragment topFragment = getTopFragment();
            if (topFragment instanceof BaseFragment) {
                navigationView.setCheckedItem(R.id.nav_home);
            }

            int stack = getSupportFragmentManager().getBackStackEntryCount();
            if (stack > 1) {
                pop();

            } else {
                finish();
            }
        }
    }

    private void initView() {

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
            }
        };

//        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        // Set Fragment Animator
        setFragmentAnimator(new DefaultHorizontalAnimator());
    }
}
