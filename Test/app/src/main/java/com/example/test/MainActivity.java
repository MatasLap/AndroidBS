package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    Button writeBtn, readBtn;
    TextView text;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeBtn = findViewById(R.id.write);
        input = findViewById(R.id.input);
        text = findViewById(R.id.text);
        readBtn = findViewById(R.id.read);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = input.getText().toString();
                writeToFile("file.txt", content);
            }
        });
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = readFromFile("file.txt");
                text.setText(content);
            }
        });






    }

    public String readFromFile(String filename){
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, filename);
        byte[] content = new byte[(int) readFrom.length()];
        try {
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            return new String(content);
        } catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }

    }



    private void writeToFile(String filename, String content){
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, filename));
            writer.write(content.getBytes());
            writer.close();
            Toast.makeText(getApplicationContext(), "Wrote to file: " + filename, Toast.LENGTH_LONG).show();
        } catch (Exception e){
            e.printStackTrace();
        }




    }
}