package com.incidentscrowdsourcingsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

import de.hdodenhof.circleimageview.CircleImageView;

public class TimelineActivity extends AppCompatActivity {

    private List<String> IncidentTitle;
    private List<String> IncidentDescription;
    private List<String> IncidentSeverity;
    private List<String> IncidentCategory;
    private List<String> IncidentDate;
    private List<Integer> IncidentUpVote;
    private List<Integer> IncidentDownVote;
    private List<Integer> IncidentID;
    private  List<String>IncidentImageStr;
    JsonArrayRequest req;
    private static final String KEY_UserId = "userId";
    private String timeline_url = "https://crowd-sourcing-system.herokuapp.com/Timeline.php";
    //private  String timeline_url="https://localhost/ICS-Web/Timeline.php";
    int id;
    String UserName;
    String email;
    String StringImg;

    private List<String> User_Name;
    private CircleImageView img;
    TextView name;
    private RecyclerView recyclerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle Toggle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);


        IncidentTitle= new ArrayList<String>();
        User_Name= new ArrayList<String>();
        IncidentDescription= new ArrayList<String>();
        IncidentSeverity= new ArrayList<String>();
        IncidentCategory= new ArrayList<String>();
        IncidentDate = new ArrayList<String>();
        IncidentUpVote= new ArrayList<Integer>();
        IncidentDownVote = new ArrayList<Integer>();
        IncidentID = new ArrayList<Integer>();
        IncidentImageStr = new ArrayList<String>();


//Fetching User Data from Sign in Page:
        id = getIntent().getIntExtra("userId",0);
       // UserName=getIntent().getStringExtra("Username");
        UserName="Menna Mohamed";
        email=getIntent().getStringExtra("Email");
        StringImg= getIntent().getStringExtra("UserImage");



        recyclerView =findViewById(R.id.recyclerviewid);
        mDrawerLayout= findViewById(R.id.drawer);
        Toggle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.Open,R.string.Close);
        mDrawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();
        NavigationView nav= findViewById(R.id.navigationId);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpDrawerContent(nav);

        DataBaseHandling();


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),ReportIncidentActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(a);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(Toggle.onOptionsItemSelected(item))
          {
              return true;
          }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem item)
    {

        switch (item.getItemId())
          {
              case R.id.home :
                  startActivity(new Intent(TimelineActivity.this,TimelineActivity.class));
                  break;
              case R.id.notification_history :
                  startActivity(new Intent(TimelineActivity.this, NotificationHistoryActivity.class));
                  break;
              case R.id.maps:
                  startActivity(new Intent(TimelineActivity.this, PermissionLocationActivity.class));
                  break;
              case R.id.shortestPath:
                  startActivity(new Intent(TimelineActivity.this, PermissionLocationActivity.class));
                  break;
              case R.id.setting:
                  startActivity(new Intent(TimelineActivity.this, SubscribeToArea.class));
                  break;
              default:
                  startActivity(new Intent(TimelineActivity.this, ReportIncidentActivity.class));
                  break;

          }
          item.setChecked(true);
          setTitle(item.getTitle());
          mDrawerLayout.closeDrawers();

    }

    private void setUpDrawerContent(NavigationView navigationView)
    {
        /*img =findViewById(R.id.userImage);
       // name=findViewById(R.id.navUsername);
        //name.setText(UserName);
        byte [] StringToByte = Base64.decode(StringImg,Base64.DEFAULT);
        Bitmap image= BitmapFactory.decodeByteArray(StringToByte,0,StringToByte.length);
        img.setImageBitmap(image);*/

      navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              selectItemDrawer(menuItem);
              return true;
          }
      });

    }

    private void DataBaseHandling ()
    {
        //Toast.makeText(getApplicationContext(), "Start DataBase Function !", Toast.LENGTH_SHORT).show();

        //int id = getIntent().getIntExtra("userId",1);


         JSONObject request = new JSONObject();
        try {
            request.put(KEY_UserId, id);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, timeline_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray Incident = response.getJSONArray("Incident");
                            //Toast.makeText(getApplicationContext(), "Start To get Data !", Toast.LENGTH_SHORT).show();
                            if (Incident.length() > 0)
                               {
                                   for (int i = 0; i < Incident.length() - 1; i++) {
                                       JSONObject IncidentObject = Incident.getJSONObject(i);
                                       if(IncidentObject.has("incidentDescription"))
                                       {
                                           IncidentDescription.add("There's no description for this incident: ");

                                       }
                                       else {
                                           IncidentDescription.add(IncidentObject.getString("incidentDescription"));
                                       }
                                       IncidentTitle.add(IncidentObject.getString("incidentTitle"));
                                       IncidentCategory.add(IncidentObject.getString("incidentCategory"));
                                       IncidentSeverity.add(IncidentObject.getString("incidentSeverity"));
                                       User_Name.add(IncidentObject.getString("userName"));
                                       IncidentDate.add(IncidentObject.getString("incidentDateTime"));
                                       IncidentUpVote.add(IncidentObject.getInt("UpVote"));
                                       IncidentDownVote.add(IncidentObject.getInt("DownVote"));
                                       IncidentID.add(IncidentObject.getInt("IncidentID"));
                                       //IncidentImageStr.add(IncidentObject.getString("IncidentImage"));


                                   }
                                   initRecyclerView(IncidentTitle,User_Name,IncidentDescription,IncidentSeverity,IncidentCategory,IncidentDate,IncidentUpVote,IncidentDownVote,IncidentID,IncidentImageStr);

                               }
                            else{
                                Toast.makeText(getApplicationContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsArrayRequest);
        initRecyclerView(IncidentTitle,User_Name,IncidentDescription,IncidentSeverity,IncidentCategory,IncidentDate,IncidentUpVote,IncidentDownVote,IncidentID,IncidentImageStr);
    }



    private void initRecyclerView(List<String>Incident_Title,List<String>UserNAME,List<String>Incident_Descritpion,List<String>Incident_Severity,List<String>Incident_Category,List<String>Incident_Date,List<Integer>Incident_UpVote,List<Integer>Incident_DownVote,List<Integer>Incident_Id,List<String>image)
    {
        IncidentsViewAdapter MyAdapter = new IncidentsViewAdapter(Incident_Title,UserNAME,Incident_Descritpion,Incident_Severity,Incident_Category,Incident_Date,Incident_UpVote,Incident_DownVote,Incident_Id,image,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(MyAdapter);
    }
}




















