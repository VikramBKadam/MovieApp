package com.example.movieapp.repository.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movieapp.model.Movies;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface MovieDao {
    @Insert
    Completable insertAll(List<Movies> movies);

   // @Query("SELECT * FROM Movies")
    //List<Movies> getAllMovies();
   @Query("SELECT * FROM Movies")
   Single<List<Movies>> getAllMovies();

@Query("Delete From Movies")
Completable deleteAllMovies();


    @Query("SELECT * FROM Movies WHERE id = :Id")
    //Movies getMovie(int Id);
    Single<Movies> getMovieById(float Id);


}
