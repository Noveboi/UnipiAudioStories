package com.example.unipiaudiostories.ui.list.story;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.R;
import com.example.unipiaudiostories.core.StatisticsService;
import com.example.unipiaudiostories.core.StoryService;
import com.example.unipiaudiostories.domain.Story;
import com.example.unipiaudiostories.domain.StoryStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StoryRecyclerViewAdapter extends RecyclerView.Adapter<StoryRecyclerViewHolder> {

    private List<StoryStatistics> stories;
    private final Consumer<Story> onClick;

    public StoryRecyclerViewAdapter(Consumer<Story> onClickListener) {
        this.stories = new ArrayList<>();
        onClick = onClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setStories(List<StoryStatistics> stories) {
        this.stories = stories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolderView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.story_item, parent, false);

        return new StoryRecyclerViewHolder(viewHolderView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryRecyclerViewHolder holder, int position) {
        holder.setStoryItem(stories.get(position), onClick);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }
}
