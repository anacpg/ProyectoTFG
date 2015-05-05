package com.example.anacristinapg.proyectotfg.Interfaz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anacristinapg.proyectotfg.BD.DBManager;
import com.example.anacristinapg.proyectotfg.BD.User;
import com.example.anacristinapg.proyectotfg.MyService2;
import com.example.anacristinapg.proyectotfg.R;


public class LocalizationActivity extends Activity {

    TextView latitud, longitud ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization);

        latitud = (TextView) findViewById(R.id.txtLatitud);
        longitud = (TextView) findViewById(R.id.txtLongitud);

        Button btn_guardar = (Button) findViewById(R.id.btnGuardar); //Definimos el boton


        final DBManager manager = User.getInstance().getManager(this);

        manager.consultar();
        manager.get_phone();

        configGPS();

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (latitud.getText() != ""){
                    Double lat = Double.parseDouble(latitud.getText().toString());
                    Double lon = Double.parseDouble(longitud.getText().toString());

                    manager.modificarLocalizacion(lat,lon);
                    Log.i("LocalizationActivity", "Coordenadas: " + manager.get_latitud() +
                            ", "+ manager.get_longitud());
                }else{
                    //TODO lanzar mensaje para introducir las coordenadas
                }
                ///startService(new Intent(LocalizationActivity.this,
                   //     ServiceWIFI.class));
                Intent i = new Intent(LocalizationActivity.this,
                        MyService2.class);
                startActivity(i);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_localization, menu);
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


    private void configGPS() {

        LocationManager mLocationManager;
        LocationListener mLocationListener;

        Log.i("ServiceMain", "Estoy dentro de configGPS\n");
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mLocationListener = new MyLocationListener();

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, mLocationListener);
    }


    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            //Log.d("MainActivity", "i =  " + (i++));
            Log.i("LocationActivity", "Estoy dentro de onLocationChanged\n");
            Log.i("LocationActivity", "Latitud: "+ String.valueOf(location.getLatitude()));


            latitud.setText(String.valueOf(location.getLatitude()));
            longitud.setText(String.valueOf(location.getLongitude()));


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }



}
