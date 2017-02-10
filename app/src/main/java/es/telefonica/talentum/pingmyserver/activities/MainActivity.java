package es.telefonica.talentum.pingmyserver.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.telefonica.talentum.pingmyserver.R;
import es.telefonica.talentum.pingmyserver.activities.servicies.PingService;

public class MainActivity extends AppCompatActivity {

    Button launchServiceButton;
    Button stopServiceButton;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        launchServiceButton = (Button) findViewById(R.id.activity_main___launch_button);
        stopServiceButton = (Button) findViewById(R.id.activity_main___stop_service);
        editText = (EditText) findViewById(R.id.activity_main___edit_text);


        launchServiceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PingService.starPingService(view.getContext(), editText.getText().toString(), 5 * 1000);
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PingService.stopPingService(view.getContext());

            }
        });
    }
}