package com.incidentscrowdsourcingsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IncidentReportActivity extends AppCompatActivity {
    Button upVotebtn;
    Button downVotebtn ;
    Integer DownVote;
    Integer UpVote;
    Integer IncidentId;
    String Username;
    String Title;
    String Category ;
    String Severity ;
    String Incident_Date ;
    String incident_date;
    String VoteUpdatingurl="https://crowd-sourcing-system.herokuapp.com/updateVotes.php";
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident_report);
         getIncomingIntent();

        upVotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type ="UpVote";
                if(upVotebtn.getText()==type)
                {
                    UpVote+=1;
                    upVotebtn.setText(UpVote);
                }
                else {

                    upVotebtn.setText(type);
                    UpVote-=1;
                }
                UpdateDataBase(UpVote,IncidentId,type);
            }

        });
        downVotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type ="DownVote";

                if(downVotebtn.getText()==type)
                {
                    DownVote+=1;
                    downVotebtn.setText(DownVote);
                }
                else {
                    DownVote-=1;
                    downVotebtn.setText(type);
                }
                UpdateDataBase(DownVote,IncidentId,type);
            }

        });

    }
    private void getIncomingIntent(){

        if(getIntent().hasExtra("IncidentTitle")&&getIntent().hasExtra("IncidentCategory")&&getIntent().hasExtra("IncidentSeverity")&&getIntent().hasExtra("UserName"))
        {

            Severity = getIntent().getStringExtra("IncidentSeverity");
            Title= getIntent().getStringExtra("IncidentTitle");
            Category = getIntent().getStringExtra("IncidentCategory");

            Username=getIntent().getStringExtra("UserName");
            Incident_Date=getIntent().getStringExtra("IncidentDate");
            UpVote=getIntent().getIntExtra("UpVoteNum",0);
            DownVote=getIntent().getIntExtra("DownVoteNum",0);
            IncidentId=getIntent().getIntExtra("IncidentId",1);
            SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            try {
                Date Incident_date =format.parse(Incident_Date);
                incident_date= format.format(Incident_date);

            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            setData(Title,Category,Severity,Username,incident_date);
        }

    }
    private void setData(String title, String category, String severity, String username,String incidentDate)
    {
        TextView Title, Category, Severity,UserName;
        ImageView image ;
        EditText date;
        Title= findViewById(R.id.incTitle);
        Category= findViewById(R.id.category);
        Severity=findViewById(R.id.severity);
        UserName = findViewById(R.id.userName);
        date = findViewById(R.id.incDate);
        Title.setText(title);
        Category.setText(category);
        Severity.setText(severity);
        UserName.setText(username);
        date.setText(incidentDate);


    }
    private void UpdateDataBase(int vote,int id,String type)
    {
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put("IncidentId",id);
            request.put("Vote",vote);
            request.put("VoteType",type );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, VoteUpdatingurl, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //Check if user got logged in successfully

                            if (response.getInt(KEY_STATUS) == 0) {

                                Toast.makeText(getApplicationContext(),
                                        "Successfully vote updated ", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsArrayRequest);

    }
}
