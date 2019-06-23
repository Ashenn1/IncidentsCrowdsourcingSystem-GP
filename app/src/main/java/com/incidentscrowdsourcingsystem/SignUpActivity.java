package com.incidentscrowdsourcingsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_AREA_NAME = "AreaName";
    private static final String KEY_EMPTY = "";

    private EditText inputEmail, inputPassword , inputUsername , inputConfirmPass;
    private Spinner chooseAreaDropdown;
    private String areaChosen;
    private Button btnSignIn, btnSignUp;
    private ProgressBar progressBar;
    private String register_url = "https://crowd-sourcing-system.herokuapp.com/register.php";
    //private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //session = new SessionHandler(getApplicationContext());

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputUsername = (EditText) findViewById(R.id.username);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputConfirmPass  = (EditText) findViewById(R.id.confirm_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        chooseAreaDropdown = (Spinner) findViewById(R.id.ChooseAreaDropDown);

        String Areas[] = new String[]{"Choose an Area", "Zamalek", "Al Haram", "Al Omraneyah"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Areas);

        chooseAreaDropdown.setAdapter(adapter);

        chooseAreaDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                areaChosen = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                areaChosen = "Choose an Area";
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String username = inputUsername.getText().toString();
                String confirm_pass = inputConfirmPass.getText().toString();

                progressBar.setVisibility(View.VISIBLE);


                if (validateInputs(username, email,areaChosen , password, confirm_pass)) {

                    JSONObject request = new JSONObject();
                    try {
                        request.put(KEY_USERNAME, username);
                        request.put(KEY_EMAIL, email);
                        request.put(KEY_AREA_NAME, areaChosen);
                        request.put(KEY_PASSWORD, password);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                    JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, register_url, request, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("PHP response" ,"php respose");
                            try {
                                //Check if user got registered successfully
                                if (response.getInt(KEY_STATUS) == 0) {
                                    //start dashboad/Timeline activity
                                    //session.loginUser(response.getString(KEY_USERNAME),response.getInt(Ke));
                                    //LoadTimeline();
                                    Toast.makeText(getApplicationContext(), "SignUp Successful", Toast.LENGTH_LONG).show();
                                    Log.d("DEBUG" ,"Sign up Successful");

                                } else if (response.getInt(KEY_STATUS) == 1) {
                                    //Display error message if username or email already exists

                                    Toast.makeText(getApplicationContext(),
                                            response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                Log.d("DEBUG" ,"ERROR!");
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                            //Display error message whenever an error occurs
                            Toast.makeText(getApplicationContext(),
                                    error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                    // Access the RequestQueue through your singleton class.
                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsArrayRequest);
                }


            }
        });
    }

    private boolean validateInputs(String username ,String email ,String chosenArea, String password , String confirm_pass){
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getApplicationContext(), "Enter a username!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return false ;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return false ;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return false ;
        }
        if(!password.equals(confirm_pass)){
            Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(chosenArea.equals("Choose an Area")){
            Toast.makeText(getApplicationContext(), "Please choose an area!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
      }


    }
