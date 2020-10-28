package com.example.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(@NonNull Context context, ArrayList<Movie> movieAdapter) {
        super(context,0, movieAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        Movie movie = getItem(position);

        TextView textViewtitle = listItemView.findViewById(R.id.textView_title_movie);
        textViewtitle.setText(movie.getTitle());

        TextView textViewPopularity = listItemView.findViewById(R.id.textView_popularity);
        textViewPopularity.setText(movie.getPopularity().toString());

        return listItemView;

    }


}
