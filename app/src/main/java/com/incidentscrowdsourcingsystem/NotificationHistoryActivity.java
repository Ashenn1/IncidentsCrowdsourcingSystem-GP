package com.incidentscrowdsourcingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NotificationHistoryActivity extends AppCompatActivity {

    private NotificationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_history);

        ListView notificationHistoryList = findViewById(R.id.NotificationHistoryList);

        ArrayList<Notification>notif = new ArrayList<Notification>();

        for(int i=0;i<5;i++){
            String title="Department of Electricity";
            String Description = "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the " +
                    "industry's standard dummy text ever since the 1500s, when " +
                    "an unknown printer took a galley of type and scrambled it to make a type specimen book";

            Notification nf = new Notification(title,Description);
            notif.add(nf);
        }

        //ArrayAdapter<Notification>arrayAdapter = new ArrayAdapter<Notification>(this,android.R.layout.simple_list_item_1,notif);
        //notificationHistoryList.setAdapter(arrayAdapter);
        mAdapter = new NotificationAdapter(this , notif);
        notificationHistoryList.setAdapter(mAdapter);

    }


}
