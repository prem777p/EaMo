package com.prem.eamo.Utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.prem.eamo.Activity.Starting_Activity.Login;

import java.util.HashMap;

public class SessionManager {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private final String KEY_IF_LOGGED_IN = "false";
    private final String PRE_FILE_NAME = "EamoUser";
    private final int PRIVATE_MODE = 0;

    private final String KEY_NAME = "key_session_name";
    private final String KEY_EMAIL = "key_session_email";
    private final String KEY_PHONE = "key_session_phone";


    // constructor for session management
    public SessionManager(Context context){

        this.context = context;

        sharedPreferences = context.getSharedPreferences(PRE_FILE_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    //    first check data is exist or not
    public boolean isLogin()
    {
        return sharedPreferences.contains(KEY_IF_LOGGED_IN);
    }

    //    create or store value in share preference
    public void setUserData(String name, String email, String phone)
    {
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PHONE,phone);
        editor.putBoolean(KEY_IF_LOGGED_IN, true);

        editor.commit(); // store in share preference
    }
    public void setUserData( String email)
    {
        editor.putString(KEY_EMAIL,email);
        editor.putBoolean(KEY_IF_LOGGED_IN, true);

        editor.commit(); // store in share preference
    }

    public HashMap<String, String> getUserData(){
        return (HashMap<String, String>) sharedPreferences.getAll();
    }

    //    for logout to delete all data from share preferecne
    public void logout()
    {
        editor.clear();
        editor.commit();

//        goes to login activity
        context.startActivity(new Intent(context, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    //    if want to get data from share preference
    public String geSessionDetail(String key)
    {
        String value = sharedPreferences.getString(key, null);
        return value;
    }
}

