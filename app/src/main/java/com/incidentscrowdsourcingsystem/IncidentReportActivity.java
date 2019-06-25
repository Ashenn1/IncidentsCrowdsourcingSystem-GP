package com.incidentscrowdsourcingsystem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
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

import static com.incidentscrowdsourcingsystem.R.drawable.ic_verified_user_black_24dp;

public class IncidentReportActivity extends AppCompatActivity {
    Button upVotebtn;
    Button downVotebtn ;

    public Integer getDownVote() {
        return DownVote;
    }

    public void setDownVote(Integer downVote) {
        DownVote = downVote;
    }

    public Integer getUpVote() {
        return UpVote;
    }

    public void setUpVote(Integer upVote) {
        UpVote = upVote;
    }

    Integer DownVote;
    Integer UpVote;
    Integer IncidentId;
    String Username;
    String Title;
    String Category ;
    String Severity ;
    String Description;
    String Incident_Date ;



    String incidentImageStr;

    public Bitmap getIncidentImage() {
        return IncidentImage;
    }

    public void setIncidentImage(Bitmap incidentImage) {
        IncidentImage = incidentImage;
    }

    Bitmap IncidentImage;
    String VoteUpdatingurl="https://crowd-sourcing-system.herokuapp.com/updateVotes.php";

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.incident_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                Intent intent = new Intent(getApplicationContext(), TimelineActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        getIncomingIntent();


    }
    private void getIncomingIntent(){

        boolean imageExist=false;


        if(getIntent().hasExtra("IncidentTitle")&&getIntent().hasExtra("IncidentCategory")&&getIntent().hasExtra("IncidentSeverity")&&getIntent().hasExtra("UserName"))
        {

            if(getIntent().hasExtra("IncidentImage"))
            {
                incidentImageStr=getIntent().getStringExtra("IncidentImage");
                byte [] StringToByte = Base64.decode(incidentImageStr,Base64.DEFAULT);
                IncidentImage = BitmapFactory.decodeByteArray(StringToByte,0,StringToByte.length);
                setIncidentImage(IncidentImage);
                imageExist=true;
            }

            Title = getIntent().getStringExtra("IncidentTitle");
            Category = getIntent().getStringExtra("IncidentCategory");
            Severity = getIntent().getStringExtra("IncidentSeverity");
            Description = getIntent().getStringExtra("IncidentDescription");
            Username = getIntent().getStringExtra("UserName");
            Incident_Date = getIntent().getStringExtra("IncidentDate");
            UpVote = getIntent().getIntExtra("UpVoteNum", 0);
            DownVote = getIntent().getIntExtra("DownVoteNum", 0);
            IncidentId = getIntent().getIntExtra("IncidentId", 1);

        }
           /* SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            try {
                Date Incident_date =format.parse(Incident_Date);
                incident_date= format.format(Incident_date);

            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }*/
            setData(Title,Category,Severity,Description,Username,Incident_Date,UpVote,DownVote,imageExist);

    }



    private void setData(String title, String category, String severity,String description ,String username,String incidentDate,int up_vote, int down_vote,Boolean imgExist)
    {
        TextView Title, Category, Severity,UserName, Description, date;
        ImageView image;
        image =findViewById(R.id.incidentImage);
        Title= findViewById(R.id.incTitle);
        Category= findViewById(R.id.category);
        Severity=findViewById(R.id.severity);
        UserName = findViewById(R.id.userName);
        date = findViewById(R.id.incDate);
        Description=findViewById(R.id.incContent);
        upVotebtn=findViewById(R.id.upVoteButton);
        downVotebtn= findViewById(R.id.DownVoteButton);
        Title.setText(title);
        Category.setText(category);
        Severity.setText(severity);
        UserName.setText(username);
        date.setText(incidentDate);
        Description.setText(description);
       // int vote= increase(up_vote);
        setUpVote(up_vote);
        if(up_vote>7&&down_vote<4)
        {
            Title.setCompoundDrawablesWithIntrinsicBounds(ic_verified_user_black_24dp,0,0,0);
        }

        if(imgExist==true)
        {
            Bitmap img= getIncidentImage();
            image.setImageBitmap(img);
        }
        else image.setVisibility(View.GONE);


        upVotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type ="UpVote";
                int vote=getUpVote();
                if(upVotebtn.getText()==type)
                {
                     vote++;
                     upVotebtn.setText(vote);
                     setUpVote(vote);
                }
                else {

                    upVotebtn.setText(type);
                    vote--;
                    setUpVote(vote);
                }
                UpdateDataBase(vote,IncidentId,type);
            }

        });
        downVotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type ="DownVote";
                int vote=getUpVote();

                if(downVotebtn.getText()==type)
                {
                    vote++;
                    downVotebtn.setText(vote);
                    setDownVote(vote);
                }
                else {
                    vote--;
                    downVotebtn.setText(type);
                    setDownVote(vote);
                }
                UpdateDataBase(vote,IncidentId,type);
            }

        });


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
