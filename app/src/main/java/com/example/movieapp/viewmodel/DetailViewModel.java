package com.example.movieapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.model.MovieDao;
import com.example.movieapp.model.Movies;
import com.example.movieapp.model.MoviesDatabase;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends AndroidViewModel {


    public DetailViewModel(@NonNull Application application) {
        super(application);
    }
    MutableLiveData<Movies> movie=new MutableLiveData<>();
    MovieDao dao = MoviesDatabase.getInstance(getApplication()).movieDao();

    public MutableLiveData<Movies>fetchMovie(float id) {
        dao.getMovieById(id).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleObserver<Movies>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@io.reactivex.annotations.NonNull Movies movies) {
                movie.setValue(movies);

            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                e.printStackTrace();

            }
        });

return movie;
    }
}