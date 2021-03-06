package com.incidentscrowdsourcingsystem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class SignInActivity extends AppCompatActivity {

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_USERID ="UserId";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_UserPhoto = "UserImage";
    private static final String KEY_EMPTY = "";
    private String login_url = "https://crowd-sourcing-system.herokuapp.com/login.php";


    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    private SessionHandler session;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        session = new SessionHandler(getApplicationContext());
        if(session.isLoggedIn()){
            LoadTimeline();
        }


        // set the view now
        setContentView(R.layout.activity_sign_in);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);




        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                JSONObject request = new JSONObject();
                try {
                    //Populate the request parameters
                    request.put(KEY_EMAIL, email);
                    request.put(KEY_PASSWORD, password);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                        (Request.Method.POST, login_url, request, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    //Check if user got logged in successfully

                                    if (response.getInt(KEY_STATUS) == 0) {
                                       // session.loginUser(username,response.getString(KEY_FULL_NAME));
                                        //redirect to timeline

                                        //UserData user=  new UserData();
                                        //user.setName(response.getString(KEY_USERNAME));
                                        //user.setId(response.getInt(KEY_USERID));
                                       // user.setEmail(response.getString(KEY_EMAIL));
                                        //String StringImg = response.getString(KEY_UserPhoto);

                                        session.loginUser(response.getString(KEY_USERNAME),response.getString(KEY_USERID));
                                        LoadTimeline();
                                         Toast.makeText(getApplicationContext(),
                                                "Successfully Logged in", Toast.LENGTH_SHORT).show();

                                        progressBar.setVisibility(View.GONE);
                                        //Context context ;
                                        //Intent i= new Intent(SignInActivity.this,TimelineActivity.class);
                                        //i.putExtra("userId",user.getId());
                                        //i.putExtra(KEY_USERNAME,user.getName());
                                        //i.putExtra(KEY_EMAIL,user.getEmail());
                                        //i.putExtra(KEY_UserPhoto,StringImg);
                                        //startActivity(i);

                                    }else{
                                        Toast.makeText(getApplicationContext(),
                                                response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //progressBar.setVisibility(View.GONE);
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {

                                progressBar.setVisibility(View.GONE);
                                //Display error message whenever an error occurs
                                Toast.makeText(getApplicationContext(),
                                        error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

                // Access the RequestQueue through your singleton class.
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsArrayRequest);

            }
        });

    }

    private void LoadTimeline() {
        Intent i = new Intent(getApplicationContext(), TimelineActivity.class);
        startActivity(i);
        finish();

    }

}
