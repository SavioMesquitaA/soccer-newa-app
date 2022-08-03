package com.example.soccernews.ui.favoritos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.soccernews.MainActivity;
import com.example.soccernews.databinding.FragmentFavoritoBinding;
import com.example.soccernews.databinding.FragmentFavoritoBinding;
import com.example.soccernews.ui.news.News;
import com.example.soccernews.ui.news.NewsAdapter;

import java.util.List;

public class FavoritosFragment extends Fragment {

    private FragmentFavoritoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritosViewModel dashboardViewModel =
                new ViewModelProvider(this).get(FavoritosViewModel.class);

        binding = FragmentFavoritoBinding.inflate(inflater, container, false);

        MainActivity activity = (MainActivity) getActivity();
        List<News> favoriteNews = activity.getDb().newsDAO().loadFavoriteNews();
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycler.setAdapter(new NewsAdapter(favoriteNews, updateNews-> {
            activity.getDb().newsDAO().save(updateNews);
            //findFavroriteNews();
        }));


        //findFavroriteNews();

        return binding.getRoot();
    }

    private void findFavroriteNews(){
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            List<News> favoriteNews = activity.getDb().newsDAO().loadFavoriteNews();
            binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recycler.setAdapter(new NewsAdapter(favoriteNews, updateNews-> {
                activity.getDb().newsDAO().save(updateNews);
                findFavroriteNews();
            }));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}