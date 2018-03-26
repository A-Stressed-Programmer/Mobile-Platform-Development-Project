package com.example.ryan.rcobb200_s1630846;

//-Project Imports-//
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//-MainSession-Home Screen-//
public class MainSession extends AppCompatActivity implements View.OnClickListener {
    //-Class Variables-//
    private Button prw_button;//Planned Road Works
    private Button ci_button;//Current Incidents
    private Button exit_button;//Exit Applications
    private Button maps_button;//Maps Button

    //-On Page Create do;-//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_session);

        //-Declare Buttons for HomeScreen Control-//
        prw_button = (Button) findViewById(R.id.prw_button);//Declare Planned Road Works Button
        ci_button = (Button) findViewById(R.id.ci_button);//Declare Current Incidents Button
        exit_button = (Button) findViewById(R.id.exit_button);//Declare Exit Application button
        maps_button = (Button) findViewById(R.id.maps_button);//Maps Button

        //-Functions of Buttons-//
        prw_button.setOnClickListener(this);
        ci_button.setOnClickListener(this);
        exit_button.setOnClickListener(this);
        maps_button.setOnClickListener(this);
    }

    //-Button Functionality-//
    @Override
    public void onClick(View view) {
        if (view == prw_button){
            //-Planned Road Works Button-//
            startActivity(new Intent(MainSession.this, PlannedRoadWorksSession.class));
        }
        else if (view == ci_button){
            //-Current Incidents-//
            startActivity(new Intent(MainSession.this, CurrentIncidentsSession.class));
        }
        else if (view == exit_button){
            //-Exit-//
        }
        else if(view == maps_button){
            //-Maps-//
            startActivity(new Intent(MainSession.this, MapsSession.class));
        }
    }
}
