package com.example.movieapp.repository.retrofit;

import com.example.movieapp.repository.retrofit.MovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface MoviesApi {
    @GET("list/1?page=1&api_key=1ec27e3cc13e3c84201e2c7cfc7bfb12")
    Single<MovieResponse> getMovieResponse();

}
