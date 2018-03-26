package com.example.ryan.rcobb200_s1630846;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

public class PlannedRoadWorksSession extends ListActivity implements View.OnClickListener{
    //-Variable Declare-//
    private Button home_button;//Home Button to return to Main Menu
    private SearchView searchBar;//Search Bar for Current Incidents
    private ArrayAdapter<String> currentAdapter = null;//Declare Adapter
    private ListView listBox;//List Array box

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planned_road_works_session);

        //-Declare Buttons Control-//
        home_button = (Button) findViewById(R.id.home_button);//Maps Button
        listBox = (ListView) findViewById(android.R.id.list);//ListBox
        searchBar = (SearchView) findViewById(R.id.searchView);//Search View

        //-Functions of Buttons-//
        home_button.setOnClickListener(this);

        //-Get Road Works XML Data-//
        ArrayList<String> localData = new ArrayList<String>();//Create Local Holder
        RoadWorksFeed getXML = new RoadWorksFeed();//Caller to getXML
        getXML.execute();//Run Async Tasking
        localData = getXML.getRoadWorksData();//Return Planned Road Works

        //-Adapter Declare-//
        final ArrayAdapter PlannedRoadWorks = new ArrayAdapter(this, android.R.layout.simple_list_item_1, localData);
        listBox.setAdapter(PlannedRoadWorks);

        //-ListView Controller-//
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                text.replace(" ","");
                PlannedRoadWorks.getFilter().filter(text);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                text.replace(" ","");
                PlannedRoadWorks.getFilter().filter(text);
                return false;
            }
        });

    }
    //-Home Session Button-//
    @Override
    public void onClick(View view) {
        if (view == home_button){
            //-Planned Road Works Button-//
            startActivity(new Intent(PlannedRoadWorksSession.this, MainSession.class));
        }
    }
}
