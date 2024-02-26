package com.lap.egzaminas;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button btn_logout, btn_addItem;

    TextView search_Artist;

    RecyclerView recyclerView;

    ArrayList<String> artistText = new ArrayList<>();
    ArrayList<String> songText = new ArrayList<>();
    ArrayList<String> uidText = new ArrayList<>();

    private String searchText;

    private DataSnapshot snapshot_edit;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_logout = findViewById(R.id.btn_logout);
        btn_addItem = findViewById(R.id.btn_addItem);
        search_Artist =findViewById(R.id.search_Artist);
        recyclerView = findViewById(R.id.items_view);
        mDatabase = FirebaseDatabase.getInstance("https://egzas-e0b51-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Music");


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ValueEventListener musicListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot_edit = snapshot;
                for(DataSnapshot dataSnapshot1:snapshot.getChildren())
                {
                    if (searchText == null || searchText.isEmpty()) {
                        artistText.add(dataSnapshot1.child("Artist").getValue().toString());
                        songText.add(dataSnapshot1.child("Song").getValue().toString());
                        uidText.add(dataSnapshot1.getKey());
                    }else if (dataSnapshot1.child("Artist").getValue().toString().contains(searchText)) {
                        artistText.add(dataSnapshot1.child("Artist").getValue().toString());
                        songText.add(dataSnapshot1.child("Song").getValue().toString());
                        uidText.add(dataSnapshot1.getKey());
                    }

                }
                MusicAdapter music_Adapter = new MusicAdapter( artistText, songText, uidText,MainActivity.this, mDatabase);
                recyclerView.setAdapter(music_Adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadMusic:onCancelled", error.toException());
            }

        };




        mDatabase.addValueEventListener(musicListener);


        search_Artist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchText = search_Artist.getText().toString();
                artistText.clear();
                musicListener.onDataChange(snapshot_edit);
            }
        });

        btn_addItem.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Add.class));
        });


        btn_logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext()
                    , Login.class));
            finish();
        });



    }

}