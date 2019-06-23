package com.incidentscrowdsourcingsystem;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG ="Check " ;

    private List<String> IncidentSeverity=new ArrayList<>();
    private List<String> IncidentCategory=new ArrayList<>();
    private List<Double> Lon=new ArrayList<>();
    private List<Double> Lat=new ArrayList<>();

    private List<String> categoryList = new ArrayList<>(Arrays.asList("Stray Dogs" , "Assault" ,"Harrasement","theft" ,"Robbery" , "Power Outage"
            ,"Water Outage" ,"Missing Person" ,"Missing Pet" ,"Grabage" ,"Sewer Leakage" ,"Dangerous Weather"
            ,"Fire" ,"unauthorized means of transportation"));
    private static final String KEY_UserId = "userId";
    private String Visualization_url = "https://crowd-sourcing-system.herokuapp.com/all_incidents_data.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("tracking","in n create");
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void JsonRequest ()
    {

        Toast.makeText(getApplicationContext(), "Start DataBase Function !", Toast.LENGTH_SHORT).show();
        int id = getIntent().getIntExtra("userId",1);

        JSONObject request = new JSONObject();
        try {
            request.put(KEY_UserId,id);
            //Toast.makeText(getApplicationContext(), "Put Request is Done!", Toast.LENGTH_SHORT).show();
            Log.d("Error message","hiii1");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, Visualization_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Error message","in response");
                            JSONArray Incident = response.getJSONArray("incidents");
                           // Toast.makeText(getApplicationContext(), "Start To get Data !", Toast.LENGTH_SHORT).show();

                            if (Incident.length()>0)
                            {

                                for (int i=0;i<Incident.length()-1;i++) {
                                    JSONObject IncidentObject = Incident.getJSONObject(i);

                                    IncidentCategory.add(IncidentObject.getString("Category"));
                                    IncidentSeverity.add(IncidentObject.getString("Severity"));
                                    Lon.add(IncidentObject.getDouble("Longitude"));
                                    Lat.add(IncidentObject.getDouble("Latitude"));
                                }


                            }
                            else{
                                Log.d("Error message","hiii2");
                                Toast.makeText(getApplicationContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Error message","in catch");
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Log.d("in map","in maps");
        //JsonRequest();

        //for(int i=0;i< IncidentID.size()-1;i++)
        //{
            //if(/*IncidentCategory.get(i).equals("Stray Dogs")*/ true)
            //{
               // LatLng place = new LatLng( Lat.get(0), Lon.get(0));
                //mMap.addMarker(new MarkerOptions().position(place).title(IncidentCategory.get(0)+","+IncidentSeverity.get(0)));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
           // }
        //}




        LatLng place = new LatLng(30.056582141259, 31.21909075986);
        mMap.addMarker(new MarkerOptions().position(place).title("Assault,High").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));

        LatLng place1 = new LatLng( 30.000426141469, 31.161111032308);
        mMap.addMarker(new MarkerOptions().position(place1).title("Garbage,Low").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place1));

        LatLng place2 = new LatLng( 30.006805271061, 31.121988161743);
        mMap.addMarker(new MarkerOptions().position(place2).title("Harassment,High").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place2));

        LatLng place3 = new LatLng(30.056582141259, 31.21909075986);
        mMap.addMarker(new MarkerOptions().position(place3).title("Assault,High").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place3));

        LatLng place4 = new LatLng(30.007823378394, 31.11615511947);
        mMap.addMarker(new MarkerOptions().position(place4).title("Sewer Leackage,High").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place4));

        LatLng place5 = new LatLng( 30.004672493205, 31.119047655478);
        mMap.addMarker(new MarkerOptions().position(place5).title("Dangerous Weather,Normal").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place5));

        LatLng place6 = new LatLng( 29.995268010215, 31.172579580879);
        mMap.addMarker(new MarkerOptions().position(place6).title("Fire,Urgent").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place6));

        LatLng place7 = new LatLng( 30.056439593011, 31.21933893409);
        mMap.addMarker(new MarkerOptions().position(place7).title("Theft,Normal").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place7));

    }
}
