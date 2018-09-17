package com.example.android.intergrupopruebatecnica.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.example.android.intergrupopruebatecnica.R;
import com.example.android.intergrupopruebatecnica.data.local.access.UserLocal;
import com.example.android.intergrupopruebatecnica.data.preference.access.UserPreferences;
import com.example.android.intergrupopruebatecnica.model.MenuModel;
import com.example.android.intergrupopruebatecnica.presenter.MenuPresenter;
import com.example.android.intergrupopruebatecnica.view.base.BaseActivity;
import com.example.android.intergrupopruebatecnica.view.contract.MenuView;
import com.example.android.intergrupopruebatecnica.view.fragment.ProspectsFragment;
import com.example.android.intergrupopruebatecnica.view.fragment.WelcomeFragment;
import com.example.android.intergrupopruebatecnica.view.utils.NavigationUtils;

import butterknife.BindView;

public class MenuActivity extends BaseActivity<MenuPresenter> implements MenuView {

    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNavDrawer();
    }

    private void initNavDrawer() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setupNavigationDrawerContent(navigationView);
        navigationView.setCheckedItem(0);
        goToNextFragment(WelcomeFragment.instantiate(this,
                WelcomeFragment.class.getName()));
    }

    @Override
    public MenuPresenter createPresenter() {
        return new MenuPresenter(this, new MenuModel(new UserPreferences(this), new UserLocal(getDatabase())));
    }

    @Override
    public int getLayout() {
        return R.layout.layout_drawer_menu;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    menuItem.setChecked(true);
                    drawer.closeDrawer(GravityCompat.START);
                    switch (menuItem.getItemId()) {
                        case R.id.nav_welcome:
                            goToNextFragment(WelcomeFragment.instantiate(this,
                                    WelcomeFragment.class.getName()));
                            break;
                        case R.id.nav_prospects:
                            goToNextFragment(ProspectsFragment.instantiate(this,
                                    ProspectsFragment.class.getName()));
                            break;
                        case R.id.nav_logout:
                            presenter.logout();
                            break;
                    }
                    return true;
                });
    }

    private void goToNextFragment(Fragment nextFragment) {
        NavigationUtils.changeRootFragment(R.id.menu_container,
                getSupportFragmentManager(),
                nextFragment);
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        finishAndRemoveTask();
        startActivity(intent);
    }
}
