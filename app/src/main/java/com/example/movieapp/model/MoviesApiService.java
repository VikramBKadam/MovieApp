package com.example.movieapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesApiService {
    private static final String BASE_URL = "https://api.themoviedb.org/4/";

    private MoviesApi api;

    public MoviesApiService() {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MoviesApi.class);
    }

    public Single<MovieResponse> getMovieResponse() {
        return api.getMovieResponse();
    }

}
