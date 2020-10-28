package com.example.movies;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class MoviesListActivity extends AppCompatActivity {

    //https://api.themoviedb.org/3/movie/550?api_key=bf609558decf0378637cd30a5f0e04f2

    ArrayList<Movie> movies;
    private RequestQueue mQeue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        movies = new ArrayList<Movie>();
        mQeue = Volley.newRequestQueue(this);

        getMovies();

        MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(movieAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Get movie and send on intent to other activity
                //movies.get(i);

                Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);

                intent.putExtra("object", (Serializable) movies.get(i));

                startActivity(intent);
            }
        });


    }

    /**
     * This function make a request to api.themoviedb.org and get JSON Movies
     */
    private void getMovies() {

        String url = "https://api.themoviedb.org/3/search/movie?api_key=bf609558decf0378637cd30a5f0e04f2&language=en-US&query=1&page=1&include_adult=false";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject movie = jsonArray.getJSONObject(i);
                                movies.add(new Movie(movie.getInt("id"), movie.getString("original_title"), movie.getString("overview"), movie.getDouble("popularity"), movie.getInt("vote_count"), movie.getString("poster_path"), movie.getString("original_title"), movie.getString("release_date"), movie.getDouble("vote_average")));
                            }

                        } catch (Exception e) {
                            Log.e("JSON", "Exception inside jsonObjectRequest: " + e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("JSON", "Fallo comunicacion" + error);
                    }
                });
        mQeue.add(jsonObjectRequest);
    }

}
