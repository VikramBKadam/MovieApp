package com.example.movieapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {
    @GET("list/1?page=1&api_key=1ec27e3cc13e3c84201e2c7cfc7bfb12")
    Single<MovieResponse> getMovieResponse();

}
