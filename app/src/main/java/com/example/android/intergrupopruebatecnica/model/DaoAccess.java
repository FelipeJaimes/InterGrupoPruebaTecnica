package com.example.android.intergrupopruebatecnica.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface DaoAccess {

    //USER:
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM User WHERE userId = :userId")
    User fetchOneUserByUserId(int userId);

    @Query("SELECT count(*) FROM User")
    int countRows();

    @Query("SELECT * FROM User ORDER BY userId ASC LIMIT 1")
    User fetchFirstUserFromTable();

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

}
