package com.example.pms.Database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pms.Model.Owner;

import java.util.List;

@Dao
public interface OwnerDao {
    @Query("SELECT * FROM Owner")
    List<Owner> getAllUsers();

    @Query("SELECT * FROM Owner WHERE user_name = (:userName) AND password = (:password)")
    Owner getOwner(String userName, String password);

    @Query("SELECT uid FROM OWNER WHERE user_name = (:userName)")
    int getId(String userName);

    @Insert
    void insertUser(Owner... users);

    @Delete
    void delete(Owner user);
}
