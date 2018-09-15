package com.example.android.intergrupopruebatecnica.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface ProspectAccess {

    //PROSPECT:
    @Insert
    void insertProspect(Prospect prospect);

    @Query("SELECT count(*) FROM Prospect")
    int countRows();

    @Query("SELECT * FROM Prospect WHERE id = :id")
    Prospect fetchOneProspectByProspectId(int id);

    @Update
    void updateProspect(Prospect prospect);

    @Delete
    void deleteProspect(Prospect prospect);

}