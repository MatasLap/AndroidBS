package com.lap.egzaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit extends AppCompatActivity {

    EditText mEdit_Artist, mEdit_Song;

    Button btn_edit;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mEdit_Artist = findViewById(R.id.edit_Artist);
        mEdit_Song = findViewById(R.id.edit_Song);

        btn_edit = findViewById(R.id.btn_edit);

        mDatabase = FirebaseDatabase.getInstance("https://egzas-e0b51-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Music");

        Bundle bundle = getIntent().getExtras();

        String Uid = bundle.getString("Uid");
        String Artist = bundle.getString("Artist");
        String Song = bundle.getString("Song");

        mEdit_Artist.setText(Artist);
        mEdit_Song.setText(Song);

        btn_edit.setOnClickListener(view -> {
            DatabaseReference ref = mDatabase.child(Uid);

            String edit_Artist = mEdit_Artist.getText().toString();
            String edit_Song = mEdit_Song.getText().toString();

            ref.child("Artist").setValue(edit_Artist);
            ref.child("Song").setValue(edit_Song);

            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        });


    }
}