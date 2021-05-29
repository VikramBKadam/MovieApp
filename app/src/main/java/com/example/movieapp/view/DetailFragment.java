package com.example.movieapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapp.R;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Util;
import com.example.movieapp.viewmodel.MovieViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {
    float id;
    private Movies movie;
   // public Context mContext;

    private MovieViewModel mViewModel;
    Toolbar toolbar;

    @BindView(R.id.text_view_movie_name)
    TextView movieName;
    @BindView(R.id.text_view_movie_description)
    TextView movieDescription;
    @BindView(R.id.text_view_movie_date)
    TextView movieDate;
    @BindView(R.id.image_view_movie)
    ImageView moviePoster;

    public static DetailFragment DetailFragmentInstance(float id) {
        Log.d("abc","print"+String.valueOf(id));
        DetailFragment fragment = new DetailFragment();
        Bundle bundle =new Bundle();
        bundle.putFloat("ID",id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
           id = getArguments().getFloat("ID");
            Log.d("abc",String.valueOf(id));
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
       // mContext=context;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);


        ButterKnife.bind(this, view);
        Log.d("abc","detailFragment movie ID "+String.valueOf(id));
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel= ViewModelProviders.of(this).get(MovieViewModel.class);
        //mViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        observeViewModel();

    }

    private void observeViewModel() {
        mViewModel.fetchMovie(id).observe(this,movie -> {
            movieName.setText(movie.getOriginal_title());
            movieDescription.setText(movie.getOverview());
            movieDate.setText(String.valueOf("Release Date :\n"+String.valueOf(movie.getRelease_date())));
            Util.loadImage(moviePoster,"https://image.tmdb.org/t/p/w500"+movie.getPoster_path());

        });

    }

}