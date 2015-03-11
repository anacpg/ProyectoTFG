package com.example.anacristinapg.proyectotfg.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by anacristinapg on 10/3/15.
 */
public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    private static final String NAME_TABLE = "DatosUsuario";

    public static final String CREATE_TABLE = "CREATE TABLE "+ NAME_TABLE +
            " (_id integer primary key autoincrement, distancia real, tiempo integer, " +
            "reposo integer, telefono integer, latitud real, longitud real)";

    public DBManager(Context context) {

        helper = new DBHelper(context);

        helper.onCreate(db);

    }

    public void insertarDatos(double distancia, int tiempo, int reposo, int telefono){

        ContentValues values = new ContentValues();
        values.put("distancia", distancia);
        values.put("tiempo", tiempo);
        values.put("reposo", reposo);
        values.put("telefono", telefono);
        values.put("latitud",0);
        values.put("longitud",0);

        db.insert(NAME_TABLE, null, values);
    }

    public void modificarLocalizacion (double latitud, double longitud){

        String sql = "UPDATE "+ NAME_TABLE+ " SET latitud=" +latitud+ ", longitud="+longitud +";";

        db.execSQL(sql);

    }
    public void modificarDatos(double distancia, int tiempo, int reposo, int telefono){

        String sql = "UPDATE "+ NAME_TABLE+ " SET distancia=" + distancia + ", tiempo=" + tiempo +
                ", reposo=" +reposo+ ",telefono" + telefono + ";";

        db.execSQL(sql);
    }


}
