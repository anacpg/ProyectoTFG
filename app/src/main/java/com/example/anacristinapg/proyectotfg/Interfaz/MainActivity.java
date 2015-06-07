package com.example.anacristinapg.proyectotfg.Interfaz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.anacristinapg.proyectotfg.BD.DBManager;
import com.example.anacristinapg.proyectotfg.BD.User;
import com.example.anacristinapg.proyectotfg.R;
import com.example.anacristinapg.proyectotfg.ServiceMain;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btn_comenzar = (Button) findViewById(R.id.btn_comenzar); //Definimos el boton

        String font_path="font/gotham-light.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        btn_comenzar.setTypeface(TF);

        final DBManager manager = User.getInstance().getManager(this);


        btn_comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (manager.consultar()) {
                    Intent i = new Intent(MainActivity.this,
                            StopActivity.class);
                    startActivity(i);

                    startService(new Intent(MainActivity.this,
                               ServiceMain.class));

                    //Intent i = new Intent(MainActivity.this,
                      //      MyService.class);
                    //startActivity(i);
                } else {

                    Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(i);

                }
            }
        });




        /*
        Button detener = (Button) findViewById(R.id.btn_parar);
        detener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this,
                        ServiceMain.class));
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
