package com.incidentscrowdsourcingsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ReportIncidentActivity extends AppCompatActivity {

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TITLE = "Title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_SEVERITY = "0";
    private static final String KEY_AREA = "area";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_EMPTY = "";

    private Button btnChoose, btnSubmit;
    private ImageView ImageView1;
    private Uri image;
    private Bitmap incidentImage;

    private Spinner areaSpinner, categorySpinner;
    private String categoryChosen;
    private String areaChosen;

    private EditText inputTitle, inputDescription;

    private RadioGroup severity;
    private RadioButton severityChoice;
    private int severitySelected;

    public static final int PICK_IMAGE = 1;

    private String register_url = "localhost/ICS_Web/reporting_incident.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incident);

//IMAGE PLUS BUTTON
        btnChoose = (Button) findViewById(R.id.btnChoose);
        ImageView1 = (ImageView) findViewById(R.id.imageView);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

 //TITLE
        inputTitle = (EditText) findViewById(R.id.titleBox);

 //DESCRIPTION
        inputDescription = (EditText) findViewById(R.id.descriptionBox);

//CATEGORY DROPDOWN MENU
        categoryDropDownMenu();

//INCIDENT SEVERITY RADIO BUTTONS
        addListenerOnButton();

//AREA DROPDOWN MENU
        areaDropDownMenu();

//SUBMIT THE FORM
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToDatabase();
            }
        });


    }

    /*public void goToNext(View view){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }*/

    private void chooseFile() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        // to display the image , make use of a method called onActivityResult.
        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            try {
                image = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(image);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                incidentImage = selectedImage;
                ImageView1.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }

    }

//SEVERITY RADIO BUTTON LISTENER
    private void addListenerOnButton(){
        severity = (RadioGroup) findViewById(R.id.severity_radio_group);

        severitySelected = severity.getCheckedRadioButtonId();
        severityChoice = (RadioButton) findViewById(severitySelected);

        Toast.makeText(getApplicationContext(),
                severityChoice.getText(), Toast.LENGTH_SHORT).show();
    }

//CATEGORY DROPDOWN MENU
    private void categoryDropDownMenu(){
        categorySpinner = (Spinner) findViewById(R.id.category_spinner);

        // Create an ArrayAdapter using the string array and a default CSspinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the CSspinner
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                categoryChosen = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categoryChosen = "None";
            }
        });
    }

//AREA DROPDOWN MENU
    private void areaDropDownMenu(){
        areaSpinner = (Spinner) findViewById(R.id.area_spinner);

        // Create an ArrayAdapter using the string array and a default CSspinner layout
        ArrayAdapter<CharSequence> areaAdapter = ArrayAdapter.createFromResource(this,
                R.array.area_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the CSspinner
        areaSpinner.setAdapter(areaAdapter);

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                areaChosen = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                areaChosen = "None";
            }
        });
    }

    private boolean validateInputs(String title, String category, String area){
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(getApplicationContext(), "Please enter a title!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (category == "None") {
            Toast.makeText(getApplicationContext(), "Please choose a category!", Toast.LENGTH_SHORT).show();
            return false ;
        }

        if(area == "None"){
            Toast.makeText(getApplicationContext(), "Please enter the area of the incident!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String BitMapToString(Bitmap bitmap){

        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,10, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);

        return temp;
    }

    private void uploadToDatabase(){
        String incidentTitle = inputTitle.getText().toString();
        String incidentDescription = inputDescription.getText().toString();
        String incidentPhoto = BitMapToString(incidentImage);

        if(validateInputs(incidentTitle, categoryChosen, areaChosen)){
            JSONObject request = new JSONObject();
            try {
                request.put(KEY_TITLE, incidentTitle);
                request.put(KEY_DESCRIPTION, incidentDescription);
                request.put(KEY_CATEGORY, categoryChosen);
                request.put(KEY_SEVERITY, Integer.toString(severitySelected));
                request.put(KEY_AREA, areaChosen);
                request.put(KEY_IMAGE, incidentPhoto);
            } catch (JSONException e) { e.printStackTrace(); }

            JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, register_url, request, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("PHP response" ,"php respose");
                    try {
                        //Check if user got registered successfully
                        if (response.getInt(KEY_STATUS) == 0) {
                            //start dashboad/Timeline activity
                            Toast.makeText(getApplicationContext(), "Incident is reported successfully", Toast.LENGTH_LONG).show();
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

}
