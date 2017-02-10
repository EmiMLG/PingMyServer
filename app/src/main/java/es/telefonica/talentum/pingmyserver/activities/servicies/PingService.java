package es.telefonica.talentum.pingmyserver.activities.servicies;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import es.telefonica.talentum.pingmyserver.activities.MainActivity;
import es.telefonica.talentum.pingmyserver.util.Temblator;


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
        Log.d("PingService", "------------ Hello World ----------" + s);
        Temblator.tremble(this, 500);

        SystemClock.sleep(3000);
        Log.d("PingService", "Tarea finalizada");

        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,76,i,0);
        Notification notification = new NotificationCompat.Builder(this).
                setContentTitle("Hola, Hola")
                .setContentText("Este es mi texto")
                .setSmallIcon(android.R.drawable.ic_menu_call)
                .setAutoCancel(true)
                .setColor(0xFF00FF00)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(23423423, notification);
    }


    @Override
    public void onDestroy() { //Se ejecutará cuando termine el onHandleIntent
        Log.d("PingService", "Hello World" + "I'm dieee");
    }

    public static void starPingService(Context context, String text, int millis) {
        Intent i = new Intent(context,PingService.class);
        i.putExtra("texto", text);

        PendingIntent pendingIntent = PendingIntent.getService(context,69,i,0); //El pendingIntent se queda guardado

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME, //Ejecuta desde el momento actual
                SystemClock.elapsedRealtime(),//Dame el tiempo actual del telefono en ms
                millis, //tiempo en milisegundos
                pendingIntent
        );

        // startService(i);

}

    public static void stopPingService(Context context) {

        Intent i = new Intent(context, PingService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 69, i, 0); //El pendingIntent se queda guardado
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}
