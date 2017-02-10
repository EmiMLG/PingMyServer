package es.telefonica.talentum.pingmyserver.activities.servicies;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

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

        pingServer();

        Log.d("PingService", "Tarea finalizada");


    }

    private void pingServer() {
        Socket socket;
        final String host = "82.98.136.90"; //coreta.com
        final int port = 80;
        final int timeout = 30000;   // 30 seconds
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
            Notifications.postNotification(this, MainActivity.class, "Cocreta viva", "Esto es el texto", android.R.drawable.ic_menu_call, 0xFF00FF00);

        }
        catch (UnknownHostException uhe) {
            Log.e("ServerSock", "I couldn't resolve the host you've provided!");
        }
        catch (SocketTimeoutException ste) {
            Log.e("ServerSock", "After a reasonable amount of time, I'm not able to connect, Server is probably down!");
        }
        catch (IOException ioe) {
            Log.e("ServerSock", "Hmmm... Sudden disconnection, probably you should start again!");
            Notifications.postNotification(this, MainActivity.class, "Cocreta muerta", "Sin Conexion", android.R.drawable.ic_delete, 0xFF00FF00);

        }
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
