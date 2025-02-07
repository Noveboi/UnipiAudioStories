package com.example.unipiaudiostories.ui.list.story;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.R;
import com.example.unipiaudiostories.databinding.StoryItemBinding;
import com.example.unipiaudiostories.domain.Story;
import com.example.unipiaudiostories.domain.StoryStatistics;

import java.util.function.Consumer;

public class StoryRecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView title;
    TextView author;
    TextView releaseDate;
    ImageView isFavorite;

    public StoryRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        StoryItemBinding binding = StoryItemBinding.bind(itemView);
        image = binding.ivStoryItem;
        title = binding.tvStoryTitle;
        author = binding.tvStoryAuthor;
        releaseDate = binding.tvStoryRelease;
        isFavorite = binding.ivFavorite;
    }

    public void setStoryItem(StoryStatistics storyStats, Consumer<Story> listener) {
        image.setImageResource(storyStats.getStory().getImage());
        title.setText(storyStats.getStory().getTitle());
        author.setText(storyStats.getStory().getAuthor());
        releaseDate.setText(String.valueOf(storyStats.getStory().getYear()));

        isFavorite.setImageResource(storyStats.isFavorite()
                ? R.drawable.filled_star
                : R.drawable.empty_star);

        itemView.setOnClickListener(v -> listener.accept(storyStats.getStory()));
    }
}
