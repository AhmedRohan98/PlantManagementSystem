package com.example.pms.Controller;

import android.content.Context;


import com.example.pms.Exceptions.PasswordException;
import com.example.pms.Exceptions.UserNameException;
import com.example.pms.Model.Owner;
import com.example.pms.Database.OwnerDatabase;

public class Credentials {

   private Owner owner;

    private static Credentials INSTANCE;

    public static Credentials getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new Credentials();

        }
        return INSTANCE;
    }

    public String getName(){
        return owner.getUserName();
    }
    public int getUid(){ return owner.getUid(); }

    public boolean signUp(String userName,String password, Context context){
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);


        try {
            //if(!validate(password)){
             //   return false;
                validate(password,userName);
           // }
        } catch (PasswordException | UserNameException e) {
            return false;
        }
        owner = new Owner();
       owner.setUserName(userName);
       owner.setPassword(password);
        db.userDao().insertUser(owner);
        int uid = db.userDao().getId(userName);
        owner.setUid(uid);
     //  owner.setUid;

        return true;
    }

    public boolean logIn(String userName,String password, Context context){
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        Owner userList =db.userDao().getOwner(userName,password);
        if(userList != null){
            owner = userList;
            return true;
        }
        else{
            return false;
        }




    }

    private boolean validate(String password,String username) throws PasswordException, UserNameException {
        if(password.length()<8){
            throw new PasswordException();
          //  return false;
        }
        if(username.length()<5){
            throw new UserNameException();
        }
        return true;
    }
}
