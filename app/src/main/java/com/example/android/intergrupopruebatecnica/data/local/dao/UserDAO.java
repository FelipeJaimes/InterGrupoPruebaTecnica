package com.example.android.intergrupopruebatecnica.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.intergrupopruebatecnica.data.local.entity.User;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM User")
    User fetchUser();

    @Query("SELECT * FROM User ORDER BY userId ASC LIMIT 1")
    User fetchFirstUserFromTable();

    @Update
    void updateUser(User user);

    @Query("DELETE FROM User")
    void deleteAll();

    @Delete
    void deleteUser(User user);

}
