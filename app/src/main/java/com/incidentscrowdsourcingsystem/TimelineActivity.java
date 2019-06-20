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
import java.util.List;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

public class TimelineActivity extends AppCompatActivity {

    private List<String> IncidentTitle;
    private List<String> IncidentDescription;
    private List<String> IncidentSeverity;
    private List<String> IncidentCategory;
    private List<Integer> IncidentUpVote;
    private List<Integer> IncidentDownVote;
    private List<String> IncidentDate;
    private static final String KEY_UserId = "User-Id";
    //private String timeline_url = "https://crowd-sourcing-system.herokuapp.com/Timeline.php";
    private  String timeline_url="http://localhost/ICS-Web/Timeline.php";

    private List<String> User_Name;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);


        IncidentTitle= new ArrayList<String>();
        User_Name= new ArrayList<String>();
        recyclerView =findViewById(R.id.recyclerviewid);
        DataBaseHandling();

    }
   /* private  void DataBase ()
    {
        IncidentTitle.add("XYZ");
        IncidentTitle.add("XYZ");
        IncidentTitle.add("XYZ");
        IncidentTitle.add("XYZ");
        User_Name.add("abc");
        User_Name.add("abc");
        User_Name.add("abc");
        User_Name.add("abc");
        initRecylerView(IncidentTitle,User_Name);
    }*/

    private void DataBaseHandling ()

    {
        int id = getIntent().getIntExtra("User-Id",1);
        JSONObject request = new JSONObject();
        try {
            request.put(KEY_UserId,id);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, timeline_url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            IncidentDescription.add(response.getString("incidentContent"));
                            IncidentTitle.add(response.getString("incidentTitle"));
                            IncidentCategory.add(response.getString("incidentCategory"));
                            IncidentSeverity.add(response.getString("incidentSeverity"));
                            User_Name.add(response.getString("userName"));
                            IncidentUpVote.add(response.getInt("UpVote"));
                            IncidentDownVote.add(response.getInt("DownVote"));
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
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsArrayRequest);
        initRecyclerView(IncidentTitle,User_Name,IncidentDescription,IncidentSeverity,IncidentCategory,IncidentUpVote,IncidentDownVote);
    }



    private void initRecyclerView(List<String>Incident_Title,List<String>UserNAME,List<String>Incident_Descritpin,List<String>Incident_Severity,List<String>Incident_Category,List<Integer>Incident_UpVote,List<Integer>Incident_DownVote)
    {
        IncidentsViewAdapter MyAdapter = new IncidentsViewAdapter(Incident_Title,UserNAME,Incident_Descritpin,Incident_Severity,Incident_Category,Incident_UpVote,Incident_DownVote,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(MyAdapter);
    }
}



    /*private List<String> IncidentDescription =new ArrayList<>();
    private List<String> Incident_title =new ArrayList<>();
    private List<String> IncidentSeverity =new ArrayList<>();
    private List<String> IncidentCategory=new ArrayList<>();
    private List<String> UserName =new ArrayList<>();
    private ArrayList <Integer> UpVote = new ArrayList <>();
    private ArrayList <Integer >DownVote =new ArrayList <>();
    private static final String KEY_UserId = "User-Id";
    private  List<Date> IncidentDateTime= new ArrayList<>();
    //JsonArrayRequest JSrequest;
    //RequestQueue request ;
    private String timeline_url = "https://crowd-sourcing-system.herokuapp.com/Timeline.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
       // request= Volley.newRequestQueue(this);
        DataBaseHandling();
        //initRecyclerView();

    }
   private void DataBaseHandling ()

    {
        int id = getIntent().getIntExtra("User-Id",1);
        JSONObject request = new JSONObject();
        try {
            request.put(KEY_UserId,id);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, timeline_url, null, new Response.Listener<JSONObject>() {
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
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsArrayRequest);
        initRecyclerView();
    }
  private void DataBaseHandling()
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
   }
    private void initRecyclerView(){
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        IncidentsViewAdapter adapter = new IncidentsViewAdapter(this,IncidentDescription,Incident_title,IncidentSeverity,IncidentCategory,UserName,UpVote,DownVote);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}*/


















