package com.lap.egzaminas;

import android.view.Display;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Music {
    public String Artist;
    public String Song;
    public String Uid;
    public Map<String, Boolean> stars = new HashMap<>();

    public Music(String Artist, String Song, String Uid) {
        this.Uid = Uid;
        this.Artist = Artist;
        this.Song = Song;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Artist", Artist);
        result.put("Song", Song);
        result.put("stars", stars);

        return result;
    }

}