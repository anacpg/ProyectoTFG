package com.example.anacristinapg.proyectotfg.Interfaz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anacristinapg.proyectotfg.BD.DBManager;
import com.example.anacristinapg.proyectotfg.BD.User;
import com.example.anacristinapg.proyectotfg.R;


public class SettingsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btn_continuar = (Button) findViewById(R.id.btn_continuar); //Definimos el boton
        final TextView lbl_distancia = (TextView)findViewById(R.id.txt_distancia);
        final TextView lbl_tiempo = (TextView)findViewById(R.id.txt_tiempo);
        final TextView lbl_reposo = (TextView)findViewById(R.id.txt_reposo);
        final TextView lbl_telefono = (TextView)findViewById(R.id.txt_telefono);
        final DBManager manager = User.getInstance().getManager(this);
        //manager.insertarDatos(600, 50, 30, 666666666);


        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double distancia = Double.parseDouble(lbl_distancia.getText().toString());
                int tiempo = Integer.parseInt(lbl_tiempo.getText().toString());
                int reposo = Integer.parseInt(lbl_reposo.getText().toString());
                int telefono = Integer.parseInt(lbl_telefono.getText().toString());

                manager.insertarDatos(distancia, tiempo, reposo, telefono);

                Intent i = new Intent(SettingsActivity.this, LocalizationActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
