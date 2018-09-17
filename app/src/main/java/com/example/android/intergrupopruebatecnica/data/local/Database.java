package com.example.android.intergrupopruebatecnica.data.local;

import android.arch.persistence.room.RoomDatabase;

import com.example.android.intergrupopruebatecnica.data.local.dao.ProspectDAO;
import com.example.android.intergrupopruebatecnica.data.local.dao.UserDAO;
import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;
import com.example.android.intergrupopruebatecnica.data.local.entity.User;

@android.arch.persistence.room.Database(entities = {User.class, Prospect.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract ProspectDAO prospectDAO();
}
