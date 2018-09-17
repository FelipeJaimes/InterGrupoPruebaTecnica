package com.example.android.intergrupopruebatecnica;

import com.example.android.intergrupopruebatecnica.data.local.Database;
import com.example.android.intergrupopruebatecnica.data.local.DatabaseConnection;
import com.example.android.intergrupopruebatecnica.data.remote.ApiConnection;
import com.facebook.stetho.Stetho;

public class Application extends android.app.Application {

    /*  We want to reuse database and network connections across the app
        because these components handle threads and some heavy stuff
        so having multiple instances of this is a big problem.
     */
    private DatabaseConnection databaseConnection;
    private ApiConnection apiConnection;

    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    public Database getDatabase() {
        if (databaseConnection == null) {
           databaseConnection = new DatabaseConnection(getApplicationContext());
        }
        return databaseConnection.getConnection();
    }

    public ApiConnection getApiConnection() {
        if (apiConnection == null) {
            apiConnection = new ApiConnection();
        }
        return apiConnection;
    }
}
