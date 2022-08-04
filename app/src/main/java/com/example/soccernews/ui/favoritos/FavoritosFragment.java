package com.example.soccernews.ui.favoritos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.soccernews.databinding.FragmentFavoritoBinding;
import com.example.soccernews.ui.adpters.NewsAdapter;

public class FavoritosFragment extends Fragment {

    private FragmentFavoritoBinding binding;
    private FavoritosViewModel favoritosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritosViewModel = new ViewModelProvider(this).get(FavoritosViewModel.class);

        binding = FragmentFavoritoBinding.inflate(inflater, container, false);

        loadFavoriteNews();

        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        favoritosViewModel.loadFavoriteNews().observe(getViewLifecycleOwner(), localNews -> {
            binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recycler.setAdapter(new NewsAdapter(localNews, updateNews -> {
                favoritosViewModel.saveNews(updateNews);
                loadFavoriteNews();
            }));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}