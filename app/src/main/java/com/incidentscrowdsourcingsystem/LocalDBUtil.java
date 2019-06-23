package com.incidentscrowdsourcingsystem;

import android.database.sqlite.SQLiteDatabase;

import static android.content.Context.MODE_PRIVATE;

public class LocalDBUtil {

    public SQLiteDatabase icsDB;


    public SQLiteDatabase initializaDB(){
        icsDB = SQLiteDatabase.openOrCreateDatabase("ics_db_local",null);
        icsDB.execSQL(
                "CREATE TABLE IF NOT EXISTS notification_history (\n" +
                        "    notificationId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                        "    title varchar(200),\n" +
                        "    message varchar(500),\n" +
                        "    notificationDatetime varchar(200) NOT NULL\n" +
                        ");"
        );

        return icsDB;
    }

}
