package com.example.soccernews.ui.favoritos;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.data.remote.SoccerNewsRepository;
import com.example.soccernews.domain.News;

import java.util.List;

public class FavoritosViewModel extends ViewModel {

    public FavoritosViewModel(){

    }

    public LiveData<List<News>> loadFavoriteNews() {
        return SoccerNewsRepository.getInstance().getLocalDb().newsDAO().loadFavoriteNews();
    }

    public void saveNews(News news){
        AsyncTask.execute(() -> SoccerNewsRepository.getInstance().getLocalDb().newsDAO().save(news));
    }

}