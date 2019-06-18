package com.incidentscrowdsourcingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SubscribeToArea extends AppCompatActivity {

    private String areaChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_to_area);


        Button subscribeBtn = findViewById(R.id.SubscribeBtn);
        Spinner dropdownArea = (Spinner) findViewById(R.id.ChooseAreaSpinner);

        String Areas[] = new String[]{"None", "Zamalek", "Al Haram", "Al Omraneyah"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Areas);

        dropdownArea.setAdapter(adapter);

        dropdownArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                areaChosen = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                areaChosen="None";
            }
        });

    }

}
