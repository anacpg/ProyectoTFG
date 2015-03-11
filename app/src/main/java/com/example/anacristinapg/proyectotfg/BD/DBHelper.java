package com.example.anacristinapg.proyectotfg.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anacristinapg on 10/3/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "BD_USUARIO";
    private static final int DB_SCHEME_VERSION = 1;

    public DBHelper(Context context) {

        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}



