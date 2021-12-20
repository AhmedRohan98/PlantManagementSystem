package com.example.pms.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pms.Model.Owner;
import com.example.pms.Model.Pot;

@Database(entities = {Owner.class, Pot.class}, version  = 13)
public abstract class OwnerDatabase extends RoomDatabase {
    public abstract OwnerDao userDao();
    public abstract PotDao potDao();

    private static OwnerDatabase INSTANCE;

    public static OwnerDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, OwnerDatabase.class, "DB_NAME")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
