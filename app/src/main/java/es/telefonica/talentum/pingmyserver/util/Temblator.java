package es.telefonica.talentum.pingmyserver.util;


import android.content.Context;
import android.os.Vibrator;

public class Temblator {
    public static void tremble(Context context, int milisec){
        Vibrator v = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);

        v.vibrate(milisec);

    }
}
