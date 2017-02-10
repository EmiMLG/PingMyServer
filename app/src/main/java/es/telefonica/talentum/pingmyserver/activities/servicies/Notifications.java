package es.telefonica.talentum.pingmyserver.activities.servicies;


import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class Notifications {


    public static void postNotification(Context context, Class <? extends Activity> activity, String title, String ticker, int icon, int color) {
        Intent i = new Intent(context, activity);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,76,i,0);
        Notification notification = new NotificationCompat.Builder(context).
                setContentTitle(title)
                .setTicker(ticker)
                .setSmallIcon(icon)
                .setAutoCancel(true)
                .setColor(color)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(23423423, notification);
    }
}
