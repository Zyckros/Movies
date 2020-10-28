package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import org.json.JSONObject;

import java.util.ArrayList;


public class MoviesListActivity extends AppCompatActivity {

    //https://api.themoviedb.org/3/movie/550?api_key=bf609558decf0378637cd30a5f0e04f2

    TextView textViewTitleMovie;
    ArrayList<Movie> movies;
    private RequestQueue mQeue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);


        getMovies();

    }

    /**
     * This function make a request to api.themoviedb.org and get JSON Movies
     */
    private void getMovies() {

         movies = new ArrayList<Movie>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=bf609558decf0378637cd30a5f0e04f2&language=en-US&query=1&page=1&include_adult=false";
        mQeue = Volley.newRequestQueue(this);
        textViewTitleMovie = findViewById(R.id.textView);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject movie = jsonArray.getJSONObject(i);
                                movies.add(new Movie(movie.getInt("id"), movie.getString("title"), movie.getString("overview"), movie.getDouble("popularity"), movie.getInt("vote_count"), movie.getString("poster_path"), movie.getString("original_title"), movie.getString("release_date"), movie.getDouble("vote_average")));
                            }

                            for(Movie movie: movies){
                                textViewTitleMovie.append(movie.toString());
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




/*
        final TextView textView = findViewById(R.id.textView_title_movie);
        final ListView listView = findViewById(R.id.list);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.themoviedb.org/3/search/movie?api_key=bf609558decf0378637cd30a5f0e04f2&language=en-US&query=1&page=1&include_adult=false";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                             JSONObject jsonObject = new JSONObject(response);
                             JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("results"));

                             //textView.setText(jsonArray.toString());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
            }
        });


        queue.add(stringRequest);
*/
