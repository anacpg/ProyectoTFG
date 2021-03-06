package com.example.anacristinapg.proyectotfg.Interfaz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anacristinapg.proyectotfg.BD.DBManager;
import com.example.anacristinapg.proyectotfg.BD.User;
import com.example.anacristinapg.proyectotfg.R;


public class SettingsActivity extends Activity {

    String distancia, reposo, tiempo, telefono;
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


        String font_path="font/gotham-light.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        btn_continuar.setTypeface(TF);
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            boolean datosVacios = false;
            @Override
            public void onClick(View v) {
                lbl_distancia.getText().toString().isEmpty();


                distancia = lbl_distancia.getText().toString();
                tiempo = lbl_tiempo.getText().toString();
                reposo = lbl_reposo.getText().toString();
                telefono = lbl_telefono.getText().toString();

                Log.i("SettingsActivity","Distancia: " + distancia);
                if (distancia.equals("") || tiempo.equals("") || reposo.equals("") || telefono.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                    builder.setMessage("Algún dato sin introducir. Por favor, vuelva a introducir los datos.")
                            .setTitle("Atención!!")
                            .setCancelable(false)
                            .setNeutralButton("Aceptar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

                if (!datosVacios){
                    manager.insertarDatos(Double.parseDouble(distancia), Integer.parseInt(tiempo),
                            Integer.parseInt(reposo) , Integer.parseInt(telefono));

                    Intent i = new Intent(SettingsActivity.this, LocalizationActivity.class);
                    startActivity(i);
                }
            }

        });



        Button btn_ayuda = (Button) findViewById(R.id.btn_ayuda);
        btn_ayuda.setTypeface(TF);
        btn_ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SettingsActivity.this, AyudaActivity.class);
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
