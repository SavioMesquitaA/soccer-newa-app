package com.example.soccernews.ui.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccernews.R;
import com.example.soccernews.databinding.NewsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final FavoriteListener favoritemListener;

    public NewsAdapter(List<News> news, FavoriteListener favoritemlistener){
        this.news = news;
        this.favoritemListener = favoritemlistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Context context = holder.itemView.getContext();

        News news = this.news.get(position);
        holder.binding.textView.setText(news.title);
        holder.binding.textView2.setText(news.description);
        Picasso.get().load(news.image).into(holder.binding.imageView);
        holder.binding.button.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.link));
            holder.itemView.getContext().startActivity(i);
        });

        holder.binding.imageView5.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, news.link);
            holder.itemView.getContext().startActivity(Intent.createChooser(i, "Compartilhe com ..."));
        });

        holder.binding.imageView4.setOnClickListener(view -> {
            news.favorito =!news.favorito;
            this.favoritemListener.click(news);
            notifyItemChanged(position);
        });

        int favorColor;
        if (news.favorito){
            favorColor = R.color.favorite_active;
        } else {
            favorColor = R.color.favorite_unactive;
        }
        holder.binding.imageView4.setColorFilter(context.getResources().getColor(favorColor));

    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface FavoriteListener {
        void click(News news);
    }

}
