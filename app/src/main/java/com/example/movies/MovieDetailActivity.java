package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent i = getIntent();
        Movie movie = (Movie) i.getSerializableExtra("object");

        if (movie != null) {

            TextView textViewTitle = findViewById(R.id.textView_detail_title);
            textViewTitle.setText(movie.getTitle());
            TextView textViewPopularity = findViewById(R.id.textView_detail_popularity);
            textViewPopularity.setText(movie.getPopularity().toString());
            TextView textViewOverview = findViewById(R.id.textView_detail_overview);
            textViewOverview.setText(movie.getOverView());
            TextView textViewReleaseDate = findViewById(R.id.textView_detail_release_date);
            textViewReleaseDate.setText(movie.getReleaseDate());
            TextView textViewVoteAverage = findViewById(R.id.textView_detail_vote_average);
            textViewVoteAverage.setText(movie.getVoteAverage().toString());
            TextView textViewVoteCount = findViewById(R.id.textView_detail_vote_count);
            textViewVoteCount.setText(String.valueOf(movie.getVote_count()));

        }


    }
}
