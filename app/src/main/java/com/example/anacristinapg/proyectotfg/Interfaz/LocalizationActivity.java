package com.example.anacristinapg.proyectotfg.Interfaz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.anacristinapg.proyectotfg.R;
import com.example.anacristinapg.proyectotfg.ServiceMain;


public class LocalizationActivity extends Activity {
    TextView latitud, longitud ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization);


        latitud = (TextView) findViewById(R.id.txtLatitud);
        longitud = (TextView) findViewById(R.id.txtLongitud);

        final DBManager manager = User.getInstance().getManager(LocalizationActivity.this);

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿Es esta la localización de la casa destino?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                configGPS();
            }
        });
        dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();

            }


        });
        dialogo1.show();





        Button btn_guardar = (Button) findViewById(R.id.btnGuardar); //Definimos el boton


        btn_guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (latitud.getText() != ""){
                    Double lat = Double.parseDouble(latitud.getText().toString());
                    Double lon = Double.parseDouble(longitud.getText().toString());

                    manager.modificarLocalizacion(lat,lon);
                    Log.i("LocalizationActivity", "Coordenadas: " + manager.get_latitud() +
                            ", "+ manager.get_longitud());

                    startService(new Intent(LocalizationActivity.this,
                            ServiceMain.class));

                    Intent i = new Intent(LocalizationActivity.this, StopActivity.class);
                    startActivity(i);

                }else{



                }


                //startService(new Intent(LocalizationActivity.this,
                  //      ServiceWIFI.class));
                //Intent i = new Intent(LocalizationActivity.this,
                 //       MyService2.class);
                //startActivity(i);

            }
        });

    }
    public void cancelar(){
            AlertDialog.Builder builder = new AlertDialog.Builder(LocalizationActivity.this);
            builder.setMessage("Vuelva a intentarlo cuando se encuentre en el hogar de partida.")
                    .setTitle("Aviso")
                    .setCancelable(false)
                    .setNeutralButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(LocalizationActivity.this, MainActivity.class);
                                    startActivity(i);
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();


        //finish();
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
