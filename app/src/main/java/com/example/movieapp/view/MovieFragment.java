package com.example.movieapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp.R;
import com.example.movieapp.adapters.MovieListAdapter;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {
Toolbar toolbar;
    private MovieViewModel mViewModel;
    @BindView(R.id.movie_recycler_view)
    RecyclerView MovieList;

    private MovieListAdapter movieListAdapter = new MovieListAdapter (new ArrayList<>());


    public static MovieFragment newInstance() {
        return new MovieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment, container, false);
        ButterKnife.bind(this, view);
        toolbar =view.findViewById(R.id.tool_bar);
        AppCompatActivity activity =(AppCompatActivity)getActivity();
        toolbar.setTitle("Movies");
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        mViewModel.refresh();

        MovieList.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieList.setAdapter(movieListAdapter);

        observeViewModel();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mViewModel.refresh();

        MovieList.setLayoutManager(new LinearLayoutManager(getContext()));
       MovieList.setAdapter(movieListAdapter);

        observeViewModel();


    }

    private void observeViewModel() {
        mViewModel.movies.observe(this, movies -> {
            if(movies != null && movies instanceof List) {
                movieListAdapter.updateMovieList(movies);
                mViewModel.saveToDatabase(movies);
            }
        });

    }

}