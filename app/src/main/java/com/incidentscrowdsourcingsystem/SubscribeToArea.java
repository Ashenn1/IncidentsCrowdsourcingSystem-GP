package com.incidentscrowdsourcingsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SubscribeToArea extends AppCompatActivity {

    private String areaChosen;
    SharedPreferences areaSubscribed ;
    SharedPreferences.Editor editor ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_to_area);


        final Button subscribeBtn = findViewById(R.id.SubscribeBtn);
        Spinner dropdownArea = (Spinner) findViewById(R.id.ChooseAreaSpinner);
        areaSubscribed = getSharedPreferences("areaSubscribedPreferences", Context.MODE_PRIVATE);
        editor = getSharedPreferences("areaSubscribedPreferences", MODE_PRIVATE).edit();

        String Areas[] = new String[]{"None", "Zamalek", "Al Haram", "Al Omraneyah"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Areas);

        dropdownArea.setAdapter(adapter);

        dropdownArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                areaChosen = adapterView.getItemAtPosition(pos).toString();

                areaChosen = areaChosen.replaceAll("\\s","");

                if(areaSubscribed.getInt(areaChosen,0)==0){
                    subscribeBtn.setText("Subscribe");
                }
                else if(areaSubscribed.getInt(areaChosen,0)==1){
                    subscribeBtn.setText("Unsubscribe");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                areaChosen="None";
            }
        });

        subscribeBtn.setOnClickListener(new View.OnClickListener() {

            String msg;

            @Override
            public void onClick(View view) {

                if(areaChosen.equals("None")){
                    Toast.makeText(getApplicationContext(), "Choose an Area to subscribe to!", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(areaSubscribed.getInt(areaChosen,0)==0){

                        FirebaseMessaging.getInstance().subscribeToTopic(areaChosen)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (!task.isSuccessful()) {
                                            msg = "Subscription Failed!";
                                        }
                                        else{

                                            msg = "Subscribed Succesfully!";
                                            editor.putInt(areaChosen,1);
                                            subscribeBtn.setText("Unsubscribe");
                                            editor.commit();
                                        }
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                    else if(areaSubscribed.getInt(areaChosen,0)==1){

                        FirebaseMessaging.getInstance().unsubscribeFromTopic(areaChosen)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (!task.isSuccessful()) {
                                            msg = "UnSubscribing Failed!";
                                        }
                                        else{
                                            msg ="Unsubscribed Succesfully!";
                                            editor.putInt(areaChosen,0);
                                            subscribeBtn.setText("Subscribe");
                                            editor.commit();
                                        }
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                }

            }
        });
    }

}
