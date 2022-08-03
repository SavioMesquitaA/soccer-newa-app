package com.example.soccernews.ui.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.soccernews.ui.news.News;

@Database(entities = {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDAO newsDAO();
}
