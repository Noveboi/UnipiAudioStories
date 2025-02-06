package com.example.unipiaudiostories.ui.list.stats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.R;
import com.example.unipiaudiostories.databinding.StatisticsItemBinding;
import com.example.unipiaudiostories.domain.StoryStatistics;

import java.util.ArrayList;
import java.util.List;

public class StatsRecyclerViewAdapter extends RecyclerView.Adapter<StatsRecyclerViewAdapter.StatsRecyclerViewHolder> {

    private final List<StoryStatistics> stats = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void addStats(List<StoryStatistics> stats) {
        this.stats.addAll(stats);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolderView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.statistics_item, parent, false);

        return new StatsRecyclerViewHolder(viewHolderView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsRecyclerViewHolder holder, int position) {
        holder.setItem(stats.get(position));
    }

    @Override
    public int getItemCount() {
        return stats.size();
    }

    public static class StatsRecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView storyImage;
        TextView storyAuthor;
        TextView storyReads;
        ImageView isFavoriteImage;

        public StatsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            StatisticsItemBinding binding = StatisticsItemBinding.bind(itemView);
            storyImage = binding.ivStoryItem;
            storyAuthor = binding.tvStoryAuthor;
            storyReads = binding.tvReadsNumber;
            isFavoriteImage = binding.ivIsFavorite;
        }

        public void setItem(StoryStatistics stats) {
            storyImage.setImageResource(stats.getStory().getImage());
            storyAuthor.setText(stats.getStory().getAuthor());
            storyReads.setText(String.valueOf(stats.getNumberOfListens()));

            int favImage = stats.isFavorite()
                    ? R.drawable.filled_star
                    : R.drawable.empty_star;

            isFavoriteImage.setImageResource(favImage);
        }
    }
}
