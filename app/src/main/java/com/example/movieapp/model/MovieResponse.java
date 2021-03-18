package com.example.movieapp.model;

import java.util.ArrayList;

public class MovieResponse {
    private ArrayList < Movies > results = new ArrayList< Movies>();

    public ArrayList<Movies> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movies> results) {
        this.results = results;
    }

    public MovieResponse(ArrayList<Movies> results) {
        this.results = results;

    }
}
