package com.example.movieapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Util;
import com.example.movieapp.view.DetailFragment;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    private ArrayList<Movies> moviesList;

    public MovieListAdapter(ArrayList<Movies> moviesList) {
        this.moviesList = moviesList;
    }

    public void updateMovieList(List<Movies> newMoviesList) {
        moviesList.clear();
        moviesList.addAll(newMoviesList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movies movie =moviesList.get(position);
        holder.movieName.setText(movie.getOriginal_title());
        holder.movieDate.setText("Release Date :\n"+String.valueOf(movie.getRelease_date()));
        Util.loadImage(holder.moviePoster,"https://image.tmdb.org/t/p/w500"+movie.getPoster_path());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movieName;
        TextView movieDate;
        ImageView moviePoster;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName=itemView.findViewById(R.id.name_movie);
            movieDate=itemView.findViewById(R.id.date_movie);
            moviePoster =itemView.findViewById(R.id.image_movie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickPosition = getAdapterPosition();
                    Movies movie =moviesList.get(clickPosition);
                    Log.d("abc",String.valueOf(clickPosition));
                    Log.d("abc",String.valueOf(movie.getId()));


                    DetailFragment detailFragment = DetailFragment.DetailFragmentInstance(movie.getId());

                    AppCompatActivity activity =(AppCompatActivity)v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,detailFragment).addToBackStack(null).commit();
                    Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

}
