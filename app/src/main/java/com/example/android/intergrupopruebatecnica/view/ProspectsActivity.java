package com.example.android.intergrupopruebatecnica.view;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.intergrupopruebatecnica.R;
import com.example.android.intergrupopruebatecnica.model.MyAppDatabase;
import com.example.android.intergrupopruebatecnica.model.Prospect;
import com.example.android.intergrupopruebatecnica.model.User;
import com.example.android.intergrupopruebatecnica.rest.response.ProspectsResponse;
import com.example.android.intergrupopruebatecnica.rest.service.ProspectsService;
import com.example.android.intergrupopruebatecnica.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProspectsActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String DATABASE_NAME = "db";
    boolean loggedStatus;
    private MyAppDatabase mDatabase;
    private List<Prospect> prospects = new ArrayList<>();
    ProspectsService prospectsService;

    User user;
    Prospect prospect;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospects);

        mDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        user = new User();
        prospect = new Prospect();
        prospectsService = new ProspectsService();

        getTokenSubscription(user);
        prospects = getProspects(prospectsService, token, prospects);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.prospects, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_prospects) {
            // Handle the action
        } else if (id == R.id.nav_logout) {
            // Handle the action
            loggedStatus = false;
            savePreferences(loggedStatus);
            Intent intent = new Intent(ProspectsActivity.this, MainActivity.class);
            intent.putExtra("loggedStatus", loggedStatus);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private List<Prospect> getProspects(ProspectsService prospectsService, String token, final List<Prospect> prospects) {
        prospectsService.getProspects(token, new Callback<ProspectsResponse>() {
            @Override
            public void onResponse(Call<ProspectsResponse> call, Response<ProspectsResponse> response) {
                if (response.body() == null) {
                    Log.d("ERROR: ", "empty response");
                } else {
                    prospects.addAll(response.body().getProspects());
                    addProspectsSubscription(prospects);
                    addProspects(prospects);
                }
            }

            @Override
            public void onFailure(Call<ProspectsResponse> call, Throwable t) {
                Log.e("GET PROSPECTS: ", t.getMessage());
            }
        });

        return prospects;

    }

    private Completable addProspects(final List<Prospect> prospects) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {

                if (mDatabase.prospectAccess().countRows() > 0){
                    for (int i = 0; i < prospects.size(); i++) {
                        prospect.setProspectId(String.valueOf(prospects.get(i).getId()));
                        prospect.setName(prospects.get(i).getName());
                        prospect.setSurname(prospects.get(i).getSurname());
                        prospect.setTelephone(prospects.get(i).getTelephone());
                        prospect.setStatusCd(prospects.get(i).getStatusCd());
                        mDatabase.prospectAccess().updateProspect(prospect);
                    }
                } else {
                    for (int i = 0; i < prospects.size(); i++) {
                        prospect.setProspectId(String.valueOf(prospects.get(i).getId()));
                        prospect.setName(prospects.get(i).getName());
                        prospect.setSurname(prospects.get(i).getSurname());
                        prospect.setTelephone(prospects.get(i).getTelephone());
                        prospect.setStatusCd(prospects.get(i).getStatusCd());
                        mDatabase.prospectAccess().insertProspect(prospect);
                    }
                }

                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private void addProspectsSubscription(List<Prospect> prospects) {
        try {
            addSubscription(addProspects(prospects).subscribe());
        } catch (Throwable exception) {
            Log.e("DATABASE ERROR", exception.getMessage());
        }
    }

    private Completable getToken(final User user) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {

                token = mDatabase.daoAccess().fetchFirstUserFromTable().getToken();

                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private void getTokenSubscription(User user) {
        try {
            addSubscription(getToken(user).subscribe());
        } catch (Throwable exception) {
            Log.e("DATABASE ERROR", exception.getMessage());
        }
    }

    private void savePreferences(boolean loggedStatus) {
        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("status", loggedStatus);
        editor.apply();
    }

}
