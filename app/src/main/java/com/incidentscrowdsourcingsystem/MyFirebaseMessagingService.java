package com.incidentscrowdsourcingsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final String TOPIC_GLOBAL="global";
    public SQLiteDatabase icsDB;
    int dbflag;
    public final String dbPATH = "app/";

   // LocalDBUtil db;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        icsDB = openOrCreateDatabase("ics_db_local", MODE_PRIVATE,null);
        icsDB.execSQL(
                "CREATE TABLE IF NOT EXISTS notification_history (\n" +
                        "    notificationId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                        "    title varchar(200),\n" +
                        "    message varchar(500),\n" +
                        "    notificationDatetime varchar(200) NOT NULL\n" +
                        ");"
        );

        Log.d(TAG, "Message received");

        Date date = new Date(remoteMessage.getSentTime());
        Format dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateText = dateformat.format(date);

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            String insertSQL = "INSERT INTO notification_history (title,message,notificationDatetime) " +
                    "values (?,?,?);";

            icsDB.execSQL(insertSQL,new String[]{remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),dateText});
            Log.d(TAG, "Insertion Successful ");
        }


    }


    @Override
    public void onNewToken(String token) {

        // Get updated InstanceID token.
        Log.d(TAG, "Refreshed token: " + token);

        // now subscribe to `global` topic to receive app wide notifications
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_GLOBAL)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String msg;
                if (!task.isSuccessful()) {
                    msg = "Subscription Failed!";
                }
                else{
                    msg = "Subscribed Succesfully!";
                }
                Log.d(TAG, msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        Log.d("Token: " , token);
    }


}
