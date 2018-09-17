package com.example.android.intergrupopruebatecnica.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;

import java.util.List;

@Dao
public interface ProspectDAO {

    @Insert
    void insertProspect(Prospect prospect);

    @Insert
    void insertAllProspects(List<Prospect> prospects);

    @Query("SELECT * FROM Prospect WHERE id = :id")
    Prospect fetchOneProspectByProspectId(int id);

    @Query("SELECT * FROM Prospect")
    List<Prospect> fetchAllProspects();

    @Update
    void updateProspect(Prospect prospect);

    @Delete
    void deleteProspect(Prospect prospect);

    @Query("DELETE FROM Prospect")
    void deleteAll();


}