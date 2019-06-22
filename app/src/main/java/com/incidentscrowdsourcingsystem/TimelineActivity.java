package com.incidentscrowdsourcingsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
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

public class TimelineActivity extends AppCompatActivity {

    private static final String TAG ="Check " ;
    private List<String> IncidentTitle;
    private List<String> IncidentDescription;
    private List<String> IncidentSeverity;
    private List<String> IncidentCategory;
    private List<String> IncidentDate;
    private List<Integer> IncidentUpVote;
    private List<Integer> IncidentDownVote;
    private List<Integer> IncidentID;
    JsonArrayRequest req;
    TextView test;
    private static final String KEY_UserId = "userId";
    private String timeline_url = "https://crowd-sourcing-system.herokuapp.com/Timeline.php";
    //private  String timeline_url="https://localhost/ICS-Web/Timeline.php";

    private List<String> User_Name;
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


        recyclerView =findViewById(R.id.recyclerviewid);
        mDrawerLayout= findViewById(R.id.drawer);
        Toggle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.Open,R.string.Close);
        mDrawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();
        NavigationView nav= findViewById(R.id.navigationId);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawerContent(nav);

        DataBaseHandling();

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
             /* case R.id.maps:
                  startActivity(new Intent(TimelineActivity.this,));
                  break;
              case R.id.shortestPath:
                  startActivity(new Intent(TimelineActivity.this,));*/
              case R.id.home :
                  startActivity(new Intent(TimelineActivity.this,TimelineActivity.class));
                  break;
              case R.id.additem :
                  startActivity(new Intent(TimelineActivity.this, ReportIncidentActivity.class));
                  break;
              default:
                  startActivity(new Intent(TimelineActivity.this, ReportIncidentActivity.class));

          }
          item.setChecked(true);
          setTitle(item.getTitle());
          mDrawerLayout.closeDrawers();

    }

    private void setUpDrawerContent(NavigationView navigationView)
    {
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
        Toast.makeText(getApplicationContext(), "Start DataBase Function !", Toast.LENGTH_SHORT).show();
        int id = getIntent().getIntExtra("userId",1);

         JSONObject request = new JSONObject();
        try {
            request.put(KEY_UserId,id);
            Toast.makeText(getApplicationContext(), "Put Request is Done!", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplicationContext(), "Start To get Data !", Toast.LENGTH_SHORT).show();
                            if (Incident.length()>0)
                               {
                                   for (int i=0;i<Incident.length()-1;i++) {
                                       JSONObject IncidentObject = Incident.getJSONObject(i);

                                      // IncidentDescription.add(IncidentObject.getString("incidentDescription"));
                                       IncidentTitle.add(IncidentObject.getString("incidentTitle"));
                                       IncidentCategory.add(IncidentObject.getString("incidentCategory"));
                                       IncidentSeverity.add(IncidentObject.getString("incidentSeverity"));
                                       User_Name.add(IncidentObject.getString("userName"));
                                       IncidentDate.add(IncidentObject.getString("incidentDateTime"));
                                       IncidentUpVote.add(IncidentObject.getInt("UpVote"));
                                       IncidentDownVote.add(IncidentObject.getInt("DownVote"));
                                       IncidentID.add(IncidentObject.getInt("IncidentID"));

                                   }
                                   initRecyclerView(IncidentTitle,User_Name,IncidentDescription,IncidentSeverity,IncidentCategory,IncidentDate,IncidentUpVote,IncidentDownVote,IncidentID);

                               }
                            else{
                                Toast.makeText(getApplicationContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "There is problem", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsArrayRequest);

    }



    private void initRecyclerView(List<String>Incident_Title,List<String>UserNAME,List<String>Incident_Descritpion,List<String>Incident_Severity,List<String>Incident_Category,List<String>Incident_Date,List<Integer>Incident_UpVote,List<Integer>Incident_DownVote,List<Integer>Incident_Id)
    {
        IncidentsViewAdapter MyAdapter = new IncidentsViewAdapter(Incident_Title,UserNAME,Incident_Descritpion,Incident_Severity,Incident_Category,Incident_Date,Incident_UpVote,Incident_DownVote,Incident_Id,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(MyAdapter);
    }
}




















