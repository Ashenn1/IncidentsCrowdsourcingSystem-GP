package com.incidentscrowdsourcingsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ReportIncidentActivity extends AppCompatActivity {

    private Button btnChoose, btnSubmit;
    private ImageView ImageView1;
    private Uri image;

    private Spinner areaSpinner, categorySpinner;
    private String categoryChosen;
    private String areaChosen;

    private EditText inputTitle, inputDescription;

    private RadioGroup severity;
    private RadioButton severityChoice;

    public static final int PICK_IMAGE = 1;

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
        String incidentTitle = inputTitle.getText().toString();

 //DESCRIPTION
        inputDescription = (EditText) findViewById(R.id.descriptionBox);
        String incidentDescription = inputDescription.getText().toString();

//CATEGORY DROPDOWN MENU
        categoryDropDownMenu();

//INCIDENT SEVERITY RADIO BUTTONS
        addListenerOnButton();

//AREA DROPDOWN MENU
        areaDropDownMenu();
    }

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
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            try {
                image = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(image);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
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
    public void addListenerOnButton(){
        severity = (RadioGroup) findViewById(R.id.severity_radio_group);

        int severitySelected = severity.getCheckedRadioButtonId();
        severityChoice = (RadioButton) findViewById(severitySelected);

        Toast.makeText(getApplicationContext(),
                severityChoice.getText(), Toast.LENGTH_SHORT).show();

    }

//CATEGORY DROPDOWN MENU
    public void categoryDropDownMenu(){
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
                //TODO
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categoryChosen = "None";
            }
        });
    }

//AREA DROPDOWN MENU
    public void areaDropDownMenu(){
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
                //TODO
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                areaChosen = "None";
            }
        });
    }

}
