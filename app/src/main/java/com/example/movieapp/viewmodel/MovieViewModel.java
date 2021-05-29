package com.example.movieapp.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.repository.LocalRepository;
import com.example.movieapp.repository.room.MovieDao;
import com.example.movieapp.repository.retrofit.MovieResponse;
import com.example.movieapp.model.Movies;
import com.example.movieapp.repository.retrofit.MoviesApiService;
import com.example.movieapp.repository.room.MoviesDatabase;

import java.util.ArrayList;
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

    private CompositeDisposable disposable = new CompositeDisposable();
    LocalRepository repository =new LocalRepository(getApplication());
    MutableLiveData<Movies> movie=new MutableLiveData<>();

   public MovieViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh() {
        fetchFromRemote();
    }

    private void fetchFromRemote() {
        disposable.add(
                repository.getMovieResponse()
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



                repository.deleteAllMovies().subscribeOn(Schedulers.newThread()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        repository.insertAllMovies(moviesList).subscribeOn(Schedulers.newThread()).subscribe(new CompletableObserver() {
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
    public MutableLiveData<Movies>fetchMovie(float id) {
        repository.getMovieById(id).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
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

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();

    }
}