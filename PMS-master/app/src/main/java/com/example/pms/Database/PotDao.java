package com.example.pms.Database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pms.Model.Pot;

import java.util.List;

@Dao
public interface PotDao {

    @Query("SELECT * FROM Pot WHERE uid = (:uid)")
    List<Pot> getAllPots(int uid);

    @Query("SELECT potId FROM Pot WHERE potName = (:potName) ")
    int getPotId(String potName);

    @Insert
    void insertPot(Pot... pot);

    @Delete
    void delete(Pot pot);
}
