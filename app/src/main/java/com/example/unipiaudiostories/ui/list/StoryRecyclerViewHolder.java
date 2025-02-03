package com.example.unipiaudiostories.ui.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.databinding.StoryItemBinding;
import com.example.unipiaudiostories.domain.Story;

import java.util.function.Consumer;

public class StoryRecyclerViewHolder extends RecyclerView.ViewHolder {

    StoryItemBinding binding;

    ImageView image;
    TextView title;
    TextView author;
    TextView releaseDate;

    public StoryRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = StoryItemBinding.bind(itemView);
        image = binding.ivStoryItem;
        title = binding.tvStoryTitle;
        author = binding.tvStoryAuthor;
        releaseDate = binding.tvStoryRelease;
    }

    public void setStoryItem(Story story, Consumer<Story> listener) {
        image.setImageResource(story.getImage());
        title.setText(story.getTitle());
        author.setText(story.getAuthor());
        releaseDate.setText(String.valueOf(story.getYear()));

        itemView.setOnClickListener(v -> listener.accept(story));
    }
}
