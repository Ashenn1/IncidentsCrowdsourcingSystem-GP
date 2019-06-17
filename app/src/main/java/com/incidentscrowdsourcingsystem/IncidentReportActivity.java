package com.incidentscrowdsourcingsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class IncidentReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident_report);
        getIncomingIntent();

    }
    private void getIncomingIntent(){

        if(getIntent().hasExtra("Incident-Title")&&getIntent().hasExtra("Incident-Category")&&getIntent().hasExtra("Incident-Severity")&&getIntent().hasExtra("UserName"))
        {
            if(getIntent().hasExtra("Incident-Description"))
            {
               String Description =getIntent().getStringExtra("Incident-Description");
            }
            else {
                String Title= getIntent().getStringExtra("Incident-Title");
                String Category = getIntent().getStringExtra("Incident-Category");
                String Severity = getIntent().getStringExtra("Incident-Severity");
                String Username=getIntent().getStringExtra("UserName");
               // Integer UpVote=getIntent().getIntExtra("UpVote",1);
                //Integer DownVote=getIntent().getIntExtra("DownVote",1);
                setData(Title,Category,Severity,Username);
            }


        }

    }
    private void setData(String title, String category, String severity, String username)
    {
        TextView Title, Category, Severity,UserName;
        ImageView image ;
        Title= findViewById(R.id.incTitle);
        Category= findViewById(R.id.category);
        Severity=findViewById(R.id.incident_severity);
        UserName = findViewById(R.id.userName);
        Title.setText(title);
        Category.setText(category);
        Severity.setText(severity);
        UserName.setText(username);

    }
}
