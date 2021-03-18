package com.example.movieapp.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Movie;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.model.MovieDao;
import com.example.movieapp.model.MovieResponse;
import com.example.movieapp.model.Movies;
import com.example.movieapp.model.MoviesApiService;
import com.example.movieapp.model.MoviesDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends AndroidViewModel {

    public MutableLiveData<List<Movies>> movies = new MutableLiveData<>();

    private MoviesApiService moviesApiService = new MoviesApiService();
    private CompositeDisposable disposable = new CompositeDisposable();
    MovieDao dao = MoviesDatabase.getInstance(getApplication()).movieDao();

   public MovieViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh() {
        fetchFromRemote();
    }

    private void fetchFromRemote() {
        disposable.add(
                moviesApiService.getMovieResponse()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<MovieResponse>() {

                            @Override
                            public void onSuccess(@io.reactivex.annotations.NonNull MovieResponse movieResponse) {
                                movies.setValue(movieResponse.getResults());
                               // saveToDatabase(movies);
                            }
                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                e.printStackTrace();
                            }

                        })
        );

    }


    @SuppressLint("CheckResult")
    public void saveToDatabase(List<Movies> movies) {
        ;

        ArrayList<Movies> moviesList = new ArrayList<>(movies);



                dao.deleteAllMovies().subscribeOn(Schedulers.newThread()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        dao.insertAll(moviesList).subscribeOn(Schedulers.newThread()).subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                            }
                            @Override
                            public void onComplete() {
                            }
                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        });

                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();

                    }
                }
                );




   }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();

    }
}