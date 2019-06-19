package com.incidentscrowdsourcingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

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

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

public class TimelineActivity extends AppCompatActivity {

    private ArrayList<String> IncidentDescription =new ArrayList<>();
    private ArrayList<String> Incident_title =new ArrayList<>();
    private ArrayList<String> IncidentSeverity =new ArrayList<>();
    private ArrayList<String> IncidentCategory=new ArrayList<>();
    private ArrayList<String> UserName =new ArrayList<>();
    private ArrayList <Integer> UpVote = new ArrayList <>();
    private ArrayList <Integer >DownVote =new ArrayList <>();
    private  ArrayList<Date> IncidentDateTime= new ArrayList<>();
    JsonArrayRequest JSrequest;
    RequestQueue request ;
    private String timeline_url = "http://localhost/ICS-Web/Timeline.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        request= Volley.newRequestQueue(this);
        DataBaseHandling();
        //initRecyclerView();

    }
    private void DataBaseHandling ()

    {
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.GET, timeline_url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            IncidentDescription.add(response.getString("incidentContent"));
                            Incident_title.add(response.getString("incidentTitle"));
                            IncidentCategory.add(response.getString("incidentCategory"));
                            IncidentSeverity.add(response.getString("incidentSeverity"));
                            UserName.add(response.getString("userName"));
                            UpVote.add(response.getInt("UpVote"));
                            DownVote.add(response.getInt("DownVote"));
                            //IncidentDateTime.add(response.getInt(""))

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        request.add(jsArrayRequest );
        initRecyclerView();
    }
  /*private void DataBaseHandling()
   {
       Incident_title.add("Assult");
       Incident_title.add("Robbery");
       Incident_title.add("Power outage");
       IncidentCategory.add("Assult");
       IncidentCategory.add("Robbery");
       IncidentCategory.add("Power outage");
       IncidentSeverity.add("High");
       IncidentSeverity.add("low");
       Incident_title.add("Normal");
       UserName.add("Eslam");
       UserName.add("Menna");
       UserName.add("Salah");
       UpVote.add(5);
       UpVote.add(7);
       UpVote.add(14);
       DownVote.add(13);
       DownVote.add(10);
       DownVote.add(5);

    initRecyclerView();
   }*/
    private void initRecyclerView(){
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        IncidentsViewAdapter adapter = new IncidentsViewAdapter(this,IncidentDescription,Incident_title,IncidentSeverity,IncidentCategory,UserName,UpVote,DownVote);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}


















