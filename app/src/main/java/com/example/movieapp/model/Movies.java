package com.example.movieapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movies {
    @ColumnInfo(name="IsAdult")
    private boolean adult;
    @ColumnInfo(name = "backdrop_path")
    private String backdrop_path;
    @PrimaryKey(autoGenerate = true)
    public int uuid;
    @ColumnInfo(name = "id")
    private float id;
    @ColumnInfo(name = "original_title")
    private String original_title;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "poster_pat")
    private String poster_path;
    @ColumnInfo(name = "release_date")
    private String release_date;

    public Movies(boolean adult, String backdrop_path, float id, String original_title, String overview, String poster_path, String release_date) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}

