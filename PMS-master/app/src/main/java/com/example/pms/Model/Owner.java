package com.example.pms.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Owner {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "user_name")
    private String userName;
    @ColumnInfo(name = "password")
    private String password;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}

