package com.example.soccernews.ui.data.remote;

import com.example.soccernews.ui.news.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsAPI {
    @GET("news.json")
    Call<List<News>> getNews();
}
