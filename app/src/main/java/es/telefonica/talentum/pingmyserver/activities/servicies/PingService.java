package es.telefonica.talentum.pingmyserver.activities.servicies;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;


public class PingService extends IntentService { //TODO 1: Todos los servicios extienden de IntentService

    @Override
    public void onCreate() {  //Esto se ejecutará primero
        super.onCreate();
        Log.d("PingService", "It's Alive");

    }
    public PingService(){
            this("PingService");
        }

    public PingService(String name) { //TODO 3: De nuevo alt intro para crear el constructor
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) { //TODO 2: Implementamos el método con alt intro
        String s = intent.getStringExtra("texto");
        Log.d("PingService", "Hello World" + s);

        SystemClock.sleep(3000);
        Log.d("PingService", "Tarea finalizada");
    }


    @Override
    public void onDestroy() { //Se ejecutará cuando termine el onHandleIntent
        Log.d("PingService", "Hello World" + "I'm dieee");
    }
}
