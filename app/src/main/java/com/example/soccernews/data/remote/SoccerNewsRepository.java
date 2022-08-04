package com.example.soccernews.data.remote;

import androidx.room.Room;

import com.example.soccernews.App;
import com.example.soccernews.data.local.SoccerNewsDb;
import com.example.soccernews.data.local.SoccerNewsDb;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerNewsRepository {

    private static final String REMOTE_API_URL = "https://saviomesquitaa.github.io/soccer-news-api/";
    private static final String LOCAL_DB_NAME = "soccer-news";

    private SoccerNewsAPI remoteApi;
    private SoccerNewsDb localDb;

    public SoccerNewsAPI getRemoteApi(){return remoteApi;}
    public SoccerNewsDb getLocalDb(){return localDb;}

    private SoccerNewsRepository(){
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsAPI.class);



    }

    public static SoccerNewsRepository getInstance(){return LazyHolder.INSTANCE;}

    private static class LazyHolder{
        private static final SoccerNewsRepository INSTANCE = new SoccerNewsRepository();
    }

}
