package com.lap.egzaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class Add extends AppCompatActivity {

    EditText mText_Artist, mText_Song;

    Button btn_save;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mText_Artist = findViewById(R.id.text_Artist);
        mText_Song = findViewById(R.id.text_Song);

        btn_save = findViewById(R.id.btn_save);


        mDatabase = FirebaseDatabase.getInstance("https://egzas-e0b51-default-rtdb.europe-west1.firebasedatabase.app/").getReference();


        btn_save.setOnClickListener(view -> {

            String text_Artist = mText_Artist.getText().toString().trim();
            String text_Song = mText_Song.getText().toString().trim();

            writeNewMusic(text_Artist, text_Song);

            startActivity(new Intent(getApplicationContext(), MainActivity.class));



        });




    }

    @IgnoreExtraProperties
    public class Music {

        public String Artist;
        public String Song;

        public Music(String Artist, String Song) {
            this.Artist = Artist;
            this.Song = Song;
        }

    }

    public void writeNewMusic( String Artist, String Song) {
        Music music = new Music(Artist, Song);

        mDatabase.child("Music").push().setValue(music);

    }
}