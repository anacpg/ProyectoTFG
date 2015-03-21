package com.example.anacristinapg.proyectotfg.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by anacristinapg on 10/3/15.
 */
public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    private static final String NAME_TABLE = "DatosUsuario";

    public static final String CREATE_TABLE = "CREATE TABLE "+ NAME_TABLE +
            " (id integer primary key, distancia real, tiempo integer, " +
            "reposo integer, telefono integer, latitud real, longitud real)";

    public DBManager(Context context) {

        helper = new DBHelper(context);
        db = helper.getWritableDatabase();

    }

    public void insertarDatos(double distancia, int tiempo, int reposo, int telefono){

        db.execSQL("delete from "+NAME_TABLE);
        consultar();
/*
        ContentValues values = new ContentValues();
        values.put("id",4);
        values.put("distancia", distancia);
        values.put("tiempo", tiempo);
        values.put("reposo", reposo);
        values.put("telefono", telefono);
        values.put("latitud",0.0);
        values.put("longitud",0.0);
*/
        db.execSQL("INSERT INTO "+ NAME_TABLE+ " (id, distancia, tiempo, reposo, telefono, latitud, longitud) " +
               "VALUES (1, " + distancia + ", " + tiempo  + ", " + reposo  + ", " + telefono  +",0.0,0.0)");

        //db.insert(NAME_TABLE, null, values);
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
    public boolean consultar(){
        Cursor c = db.rawQuery(" SELECT * FROM "+NAME_TABLE, null);
        Boolean contiene = false;

        if (c.moveToFirst()) {
            contiene = true;
            //Recorremos el cursor hasta que no haya más registros
            do {
                //String codigo = c.getString(0);
                //String nombre = c.getString(1);
                Log.d("Dentro del do-while", "ID: " + c.getString(0) + " distancia " + c.getString(1)
                        + " tiempo " + c.getString(2)+ " reposo " + c.getString(3)+
                        " telefono " + c.getString(4) + " latitud " + c.getString(5)
                        + " longitud " + c.getString(6));

            } while(c.moveToNext());
        }
        return contiene;
    }

    public void eliminar(){
        db.execSQL("delete from "+NAME_TABLE);
    }

}
