package com.example.movies;

import java.io.Serializable;

public class Movie  implements Serializable {

    private int id;
    private String title;
    private Double popularity;
    private long vote_count;
    private String poster_path;
    private String originalTitle;
    private String overView;
    private String releaseDate;
    private Double voteAverage;

    public Movie(int id, String title, String overView, Double popularity, long vote_count, String poster_path, String originalTitle, String releaseDate, Double voteAverage) {
        this.id = id;
        this.title = title;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.poster_path = poster_path;
        this.originalTitle = originalTitle;
        this.overView = overView;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public long getVote_count() {
        return vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverView() {
        return overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }



}
