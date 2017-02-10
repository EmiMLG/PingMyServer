package es.telefonica.talentum.pingmyserver.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.telefonica.talentum.pingmyserver.R;
import es.telefonica.talentum.pingmyserver.activities.servicies.PingService;

public class MainActivity extends AppCompatActivity {

    Button launchServiceButton;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        launchServiceButton = (Button) findViewById(R.id.activity_main___launch_button);
        editText = (EditText) findViewById(R.id.activity_main___edit_text);


        launchServiceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),PingService.class);
                i.putExtra("texto", editText.getText().toString());

                PendingIntent pendingIntent = PendingIntent.getService(view.getContext(),69,i,0); //El pendingIntent se queda guardado

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, //Ejecuta desde el momento actual
                        SystemClock.elapsedRealtime(),//Dame el tiempo actual del telefono en ms
                        5 * 1000, //tiempo en milisegundos
                        pendingIntent
                );

                // startService(i);
            }
        });




    }
}
