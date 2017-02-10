package es.telefonica.talentum.pingmyserver.activities.servicies;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;


public class PingService extends IntentService { //TODO 1: Todos los servicios extienden de IntentService

    EditText editText;

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

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("PingService", "It's Alive");

    }

    @Override
    public void onDestroy() {

        Log.d("PingService", "Hello World" + "I'm dieee");

    }
}
