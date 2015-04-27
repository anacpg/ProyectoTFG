package com.example.anacristinapg.proyectotfg;

/**
 * Created by anacristinapg on 11/3/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.anacristinapg.proyectotfg.BD.DBManager;

public class Prueba extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager manager = new DBManager(this);
        manager.eliminar();
        Log.d("boolean ", Boolean.toString(manager.consultar()));
        manager.insertarDatos(600, 50, 30, 666666666);
        Log.d("boolean ", Boolean.toString(manager.consultar()));
        manager.modificarLocalizacion(40, 50);
        Log.d("boolean ", Boolean.toString(manager.consultar()));
        Log.d("estoy en Prueba telefono:",manager.get_phone());
        /*
        manager.insertar("Jaime","11111111111");
        manager.insertar2("Juan","222222222");
        manager.insertar("Ana","3333333");
        manager.eliminar("juan");
        manager.modificarTelefono("Ana","55555555");
        */
    }
}