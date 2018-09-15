package com.example.android.intergrupopruebatecnica.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class, Prospect.class}, version = 3, exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
    public abstract ProspectAccess prospectAccess();

}
