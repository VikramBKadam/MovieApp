package com.example.movieapp.repository;

import android.content.Context;


import com.example.movieapp.model.Movies;
import com.example.movieapp.repository.retrofit.MovieResponse;
import com.example.movieapp.repository.retrofit.MoviesApiService;
import com.example.movieapp.repository.room.MovieDao;
import com.example.movieapp.repository.room.MoviesDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class LocalRepository{
    MovieDao dao;
    private MoviesApiService moviesApiService = new MoviesApiService();
    public LocalRepository(Context context){
        dao =MoviesDatabase.getInstance(context).movieDao();
    }

    public Completable deleteAllMovies(){
        return  dao.deleteAllMovies();
    }
    public Completable insertAllMovies(List<Movies> movieList){
        return dao.insertAll(movieList);
    }
    public Single getMovieById(float id){
        return dao.getMovieById(id);
    }
    public Single<MovieResponse> getMovieResponse() {
        return moviesApiService.getMovieResponse();
    }

}
