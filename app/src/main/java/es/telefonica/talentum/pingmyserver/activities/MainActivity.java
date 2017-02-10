package es.telefonica.talentum.pingmyserver.activities;

import android.content.Intent;
import android.os.Bundle;
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

                startService(i);
            }
        });




    }
}
