package com.example.android.intergrupopruebatecnica.view;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.intergrupopruebatecnica.R;
import com.example.android.intergrupopruebatecnica.model.MyAppDatabase;
import com.example.android.intergrupopruebatecnica.model.User;
import com.example.android.intergrupopruebatecnica.rest.response.LoginResponse;
import com.example.android.intergrupopruebatecnica.rest.service.LoginService;
import com.example.android.intergrupopruebatecnica.view.base.BaseActivity;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    public static final String PREFS_NAME = "StatusPrefs";
    private static final String DATABASE_NAME = "db";

    EditText mEditTextMail;
    EditText mEditTextPassword;
    Button mButtonLogin;
    Boolean loggedStatus;

    User user;
    String token;
    LoginService loginService;

    private MyAppDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restorePreferences();

        if (loggedStatus == true) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        }

        mDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        mEditTextMail = findViewById(R.id.EditText_Mail);
        mEditTextPassword = findViewById(R.id.EditText_Password);
        mButtonLogin = findViewById(R.id.Button_Login);

        user = new User();
        loginService = new LoginService();

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(mEditTextMail.getText().toString(), mEditTextPassword.getText().toString(), loginService);
            }
        });

    }

    private void login(String email, String password, LoginService loginService) {
        loginService.getLogin(email, password, new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.body() == null) {
                    Log.e("TOKEN:", "Login fail");
                    Toast.makeText(getApplicationContext(), "Wrong mail or password", Toast.LENGTH_SHORT).show();
                } else {
                    token = response.body().getAuthToken().toString();
                    loggedStatus = true;
                    savePreferences(loggedStatus);
                    addSubscription(user);
                    Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                    Log.e("TOKEN:", token);
                    Log.e("STATUS:", loggedStatus.toString());

                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    intent.putExtra("loggedStatus", loggedStatus);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LOGIN ERROR:", t.getMessage());
            }
        });
    }

    private Completable addUser(final User user) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {

                if(mDatabase.daoAccess().countRows() > 0){
                    user.setEmail(mEditTextMail.getText().toString());
                    user.setPassword(mEditTextPassword.getText().toString());
                    user.setToken(token);
                    mDatabase.daoAccess().updateUser(user);
                } else {
                    user.setEmail(mEditTextMail.getText().toString());
                    user.setPassword(mEditTextPassword.getText().toString());
                    user.setToken(token);
                    mDatabase.daoAccess().insertUser(user);
                }

                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private void addSubscription(User user) {
        try {
            addSubscription(addUser(user).subscribe());
        } catch (Throwable exception){
            Log.e("DATABASE ERROR", exception.getMessage());
        }
    }

    private void restorePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        loggedStatus = settings.getBoolean("status", false);
    }

    private void savePreferences(boolean loggedStatus) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("status", loggedStatus);
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
