package com.example.anacristinapg.proyectotfg;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.example.anacristinapg.proyectotfg.BD.DBManager;
import com.example.anacristinapg.proyectotfg.BD.User;

public class ServiceMain extends Service {

    private double lat_ini, lon_ini, d_max;
    private int r_max, t_max;
    private String phone;
    private double longitud, latitud,distancia;
    private boolean flag = true;
    private long time_ini;

    public ServiceMain() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //TODO obtener valores de la BD
        final DBManager manager = User.getInstance().getManager(this);

        manager.consultar();
        phone = manager.get_phone();
        latitud = manager.get_latitud();
        longitud = manager.get_longitud();
        distancia = manager.get_distancia();
        t_max = manager.get_tiempo();
        r_max = manager.get_reposo();

        Log.i("ServiceMain","tiempo: "+t_max);
        // obtener valores: phone,lat, lon, distancia, reposo, tiempo

        //configGPS();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("Servicio","Estoy en Servicio y funciona genial");
        time_ini = System.currentTimeMillis()/1000;
        configGPS();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    private void configGPS() {
        Toast.makeText(getApplicationContext(),"Estoy en configGPS", Toast.LENGTH_LONG).show();

        LocationManager mLocationManager;
        LocationListener mLocationListener;

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mLocationListener = new MyLocationListener();

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000*20, 0, mLocationListener);
    }

    private class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {

            //Log.d("MainActivity", "i =  " + (i++));
            Log.i("ServiceMain", "Estoy dentro de onLocationChanged\n");
            Log.i("ServiceMain", "Latitud: "+ String.valueOf(location.getLatitude()));


            Double lat = Double.parseDouble(String.valueOf(location.getLatitude()));
            Double lon = Double.parseDouble(String.valueOf(location.getLongitude()));

            controlarDistancia(lat,lon);
            controlarTiempo();


        }
        private void sendMess(String msg){
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, msg, null, null);
                Toast.makeText(getApplicationContext(), "SMS enviado", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }

        public double distFrom(double lat1, double lng1, double lat2, double lng2) {
            //double earthRadius = 3958.75;//miles
            double earthRadius = 6371;//kilometers
            double dLat = Math.toRadians(lat2 - lat1);
            double dLng = Math.toRadians(lng2 - lng1);
            double sindLat = Math.sin(dLat / 2);
            double sindLng = Math.sin(dLng / 2);
            double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                    * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double dist = earthRadius * c;

            return dist;
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

        public void controlarDistancia(double lat, double lon){

            if (flag){
                double d = distFrom(latitud,longitud,lat,lon);
                if (d >= distancia ){
                    flag = false;
                    Log.i("ServiceMain", "Distancia"+ d +" mayor que la máxima: "+distancia);
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Distancia"+ d +" mayor que la máxima: "+distancia, Toast.LENGTH_SHORT);
                    toast1.show();
                    //sendMess("Distancia recorrida: "+ d + "m." + "\nLatitud: "+ String.valueOf(location.getLatitude()) +"\nLongitud: "+ String.valueOf(location.getLongitude()) );
                }

            }
        }

        public void controlarTiempo(){

            long t = (System.currentTimeMillis()/1000) - time_ini;

            if (t/60 >= t_max){
                Log.d("Time","Time " + t_max +" t " + t/60 );
                Toast.makeText(getApplicationContext(),"Time " + t_max +" t " + t, Toast.LENGTH_LONG).show();
                //sendMess("Tiempo desde que salió de casa: min."  );
            }
        }

    }


}
