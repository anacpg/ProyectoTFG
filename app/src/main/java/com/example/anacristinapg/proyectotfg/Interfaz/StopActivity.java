package com.example.anacristinapg.proyectotfg.Interfaz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.anacristinapg.proyectotfg.R;
import com.example.anacristinapg.proyectotfg.ServiceMain;

public class StopActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        String font_path="font/gotham-light.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        Button btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_stop.setTypeface(TF);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                stopService(new Intent(StopActivity.this, ServiceMain.class));
                Intent i = new Intent(StopActivity.this,
                        MainActivity.class);
                startActivity(i);

            }
        });

        Button btn_configurar = (Button) findViewById(R.id.btn_configurar); //Definimos el boton

        btn_configurar.setOnClickListener  (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(StopActivity.this, ServiceMain.class));

                Intent i = new Intent(StopActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stop, menu);
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
