package com.example.jsonread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //widgets
    RecyclerView recyclerView;
    // masyvai duomenu gali but

    //Arraylists for data
    ArrayList<String> firstName = new ArrayList<>();
    ArrayList<String> lastName = new ArrayList<>();
    ArrayList<String> phone = new ArrayList<>();
    ArrayList<String> email = new ArrayList<>();

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



        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        try{
            File file = new File("/sdcard/Documents","students.json");
            FileReader fileReader = null;
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String response = stringBuilder.toString();
            JSONObject obj = new JSONObject(response);

            JSONArray userArray = obj.getJSONArray("Students");

            for(int i = 0; i<userArray.length(); i++){

                JSONObject userDetail = userArray.getJSONObject(i);

                firstName.add(userDetail.getString("First Name:"));
                lastName.add(userDetail.getString("Last Name:"));
                phone.add(userDetail.getString("Phone Nr:"));
                email.add(userDetail.getString("Email:"));
            }

        }catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        CustomAdapter customAdapter = new CustomAdapter(firstName, lastName,phone, email, MainActivity.this);
        recyclerView.setAdapter(customAdapter);

    }

    /* private String loadJSONfromAssets() {
        String json = null;
        try{
            InputStream is = getAssets().open("students.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        try {
            File file = new File("/sdcard/Documents","students.json");
            FileReader fileReader = null;
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }*/
}



