package com.incidentscrowdsourcingsystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NotificationHistoryActivity extends AppCompatActivity {

    private NotificationAdapter mAdapter;

    SQLiteDatabase ics_DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                Intent intent = new Intent(getApplicationContext(), TimelineActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        ics_DB = openOrCreateDatabase("ics_db_local",MODE_PRIVATE,null);
        ics_DB.execSQL(
                "CREATE TABLE IF NOT EXISTS notification_history (\n" +
                        "    notificationId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                        "    title varchar(200),\n" +
                        "    message varchar(500),\n" +
                        "    notificationDatetime varchar(200) NOT NULL\n" +
                        ");"
        );

        //get notification data info
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getString("title")!=null) {
            //bundle must contain all info sent in "data" field of the notification
            String title,message;
            title = bundle.getString("title");
            message = bundle.getString("message");
            Date currentTime = Calendar.getInstance().getTime();

            Format dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateText = dateformat.format(currentTime);

            String insertSQL = "INSERT INTO notification_history (title,message,notificationDatetime) " +
                    "values (?,?,?);";

            ics_DB.execSQL(insertSQL,new String[]{title,message,dateText});
            Log.d("Notification History", "Insertion Successful ");

        }

        ListView notificationHistoryList = findViewById(R.id.NotificationHistoryList);


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
