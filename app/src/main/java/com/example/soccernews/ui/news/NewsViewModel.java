package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        List<News> news = new ArrayList<>();
        news.add(new News("FlaFlu", "Flamengo vs Fluminence no campeonato pela taça"));
        news.add(new News("FlaFlu", "Flamengo vs Fluminence no campeonato regional"));
        news.add(new News("FlaFlu", "Flamengo vs Fluminence no campeonato brasileiro"));

        this.news.setValue(news);

        }

    public LiveData<List<News>> getNews() { return news; }
}