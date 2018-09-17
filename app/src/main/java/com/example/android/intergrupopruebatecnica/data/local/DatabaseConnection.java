package com.example.android.intergrupopruebatecnica.data.local;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseConnection {
    private static final String DATABASE_NAME = "AppDB";

    private Database database;

    public DatabaseConnection(Context context) {
         database = Room.databaseBuilder(context, Database.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public Database getConnection() {
        return database;
    }
}
