package com.incidentscrowdsourcingsystem;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@TargetApi(21)
public class NotificationJobService extends JobService {

    LocationManager locationManager;
    LocationListener locationListener;
    SharedPreferences currentAreaSubscribed;
    SharedPreferences.Editor editor ;
    String msg;
    String currentArea;


    @Override
    public boolean onStartJob(JobParameters params) {

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        currentAreaSubscribed = getSharedPreferences("currentAreaSubscribed", Context.MODE_PRIVATE);
        editor = getSharedPreferences("currentAreaSubscribed", MODE_PRIVATE).edit();


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location",location.toString());

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    //Getting the address from the user from their Coordinates using Geocoding
                    List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                    if( listAddresses != null && listAddresses.size()>0 ){
                         currentArea = "";

                        //Getting the area the user is in right now , then subscribing to it to receive notifications.
                        if(listAddresses.get(0).getLocality() != null){
                            currentArea = listAddresses.get(0).getLocality();

                          if(currentAreaSubscribed.getInt(currentArea,0)==0){

                             FirebaseMessaging.getInstance().subscribeToTopic(currentArea)
                                     .addOnCompleteListener(new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull Task<Void> task) {

                                             if (!task.isSuccessful()) {
                                                 msg = "Subscription Failed!";
                                             }
                                             else{

                                                 msg = "Subscribed Succesfully!";
                                                 editor.putInt(currentArea,1);
                                                 editor.commit();
                                             }
                                             //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                         }
                                     });
                             }
                         }
                        //keep being subscribed for 30 minutes.
                        try { Thread.sleep(1000*30); } catch (Exception e) {e.printStackTrace();}
                        //Then unsubscribe and get user location again.
                        FirebaseMessaging.getInstance().unsubscribeFromTopic(currentArea)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (!task.isSuccessful()) {
                                            msg = "Unsubscribing Failed !";
                                        }
                                        else{

                                            msg = "Subscription Successfull !";
                                            editor.putInt(currentArea,0);
                                            editor.commit();
                                        }
                                        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }


                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                }



            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED){
            Log.d("Access User Location","No Access to User Location");
        }
        // If i was granted permission to access user location then I'll go ahead and do just that
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10,locationListener);
        }


        //SchedulerUtil.scheduleJob(context);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }

}
