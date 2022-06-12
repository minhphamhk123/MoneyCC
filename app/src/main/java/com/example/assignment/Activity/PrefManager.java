package com.example.assignment.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefManager {

    //
    Context context;

    PrefManager(Context context){
        this.context = context;
    }

    public void saveLoginDetail(String username, String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //
        editor.putString("Username", username);
        editor.putString("Password", password);
        editor.commit();
    }

    public String getUser(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Username", "");
    }

    public String getPass(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Password", "");
    }

    public boolean isUserLogedOut(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isUsernameEmpty = sharedPreferences.getString("Username", "Username").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "Password").isEmpty();
        return isUsernameEmpty || isPasswordEmpty;
    }
}
