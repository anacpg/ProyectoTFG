package com.example.anacristinapg.proyectotfg.BD;

import android.content.Context;

/**
 * Created by anacristinapg on 11/3/15.
 */
public class User {
    private static User ourInstance = null;

    private DBManager manager ;

    public static User getInstance() {
        if (ourInstance == null) {
            ourInstance = new User();

        }
        return ourInstance;
    }

    public DBManager getManager(Context context){

        if (manager == null){
            manager = new DBManager(context);
        }

        return manager;
    }

    private User() {

    }
}