package com.incidentscrowdsourcingsystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NotificationHistoryActivity extends AppCompatActivity {

    private NotificationAdapter mAdapter;

    SQLiteDatabase ics_DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_history);

        ListView notificationHistoryList = findViewById(R.id.NotificationHistoryList);
        ics_DB = openOrCreateDatabase("ics_db_local",MODE_PRIVATE,null);

        ArrayList<Notification>notif = new ArrayList<Notification>();

        String selectNotifications = "SELECT * FROM notification_history ORDER BY notificationId DESC";
        Cursor cur = ics_DB.rawQuery(selectNotifications,null);

        //loop through all rows and add them to the list
        if(cur.moveToFirst()){

            do{
                String title = cur.getString(cur.getColumnIndex("title"));
                String message = cur.getString(cur.getColumnIndex("message"));
                Notification nf = new Notification(title,message);
                notif.add(nf);
            }while(cur.moveToNext());
        }
        else{
            Log.d("Notification Activity","Didnt get anything out of the db");
        }
/*
        for(int i=0;i<1;i++){
            String title="Department of Electricity";
            String Description = "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the " +
                    "industry's standard dummy text ever since the 1500s, when " +
                    "an unknown printer took a galley of type and scrambled it to make a type specimen book";

            Notification nf = new Notification(title,Description);
            notif.add(nf);
        }
*/
        //ArrayAdapter<Notification>arrayAdapter = new ArrayAdapter<Notification>(this,android.R.layout.simple_list_item_1,notif);
        //notificationHistoryList.setAdapter(arrayAdapter);
        mAdapter = new NotificationAdapter(this , notif);
        notificationHistoryList.setAdapter(mAdapter);

    }


}
