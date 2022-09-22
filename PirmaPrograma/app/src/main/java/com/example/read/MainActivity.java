package com.example.read;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText txt1, txt2, txt3, txt4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        txt1=(EditText)findViewById(R.id.txt1);
        txt2=(EditText)findViewById(R.id.txt2);
        txt3=(EditText)findViewById(R.id.txt3);
        txt4=(EditText)findViewById(R.id.txt4);
        JSONArray jsonArray = new JSONArray();
        JSONObject studentsObj = new JSONObject();

        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("First Name:", txt1.getText().toString());
                    jsonObject.put("Last Name:", txt2.getText().toString());
                    jsonObject.put("Phone Nr:", txt3.getText().toString());
                    jsonObject.put("Email:", txt4.getText().toString());

                    // Json masyva įdedamas tarpinis objektas

                    jsonArray.put(jsonObject);

                    //į objkta pagal raktinnį zodi įdedamas masyvas

                    studentsObj.put("Students", jsonArray);

                    //Duomenys padaromi į String tipa

                    String jsonString = studentsObj.toString();

                    File file = new File("/sdcard/Documents","students.json");
                    FileWriter fileWriter = new FileWriter(file,false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(jsonString);
                    bufferedWriter.close();

                } catch (JSONException | IOException e) {
                    e.printStackTrace();

                }

            }
        });
    }
}