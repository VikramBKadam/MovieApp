package com.example.movieapp.repository.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movieapp.model.Movies;


@Database(entities = {Movies.class}, version = 1)
    public abstract class MoviesDatabase extends RoomDatabase {

        private static MoviesDatabase instance;

        public static MoviesDatabase getInstance(Context context) {
            if(instance == null) {
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        MoviesDatabase.class,
                        "moviesdatabase")
                        .build();
            }
            return instance;
        }

        public abstract MovieDao movieDao();
    }

