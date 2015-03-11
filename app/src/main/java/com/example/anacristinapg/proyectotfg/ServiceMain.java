package com.example.anacristinapg.proyectotfg;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceMain extends Service {
    public ServiceMain() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
