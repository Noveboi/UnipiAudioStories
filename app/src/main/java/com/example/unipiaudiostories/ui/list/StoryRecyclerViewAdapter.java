package com.example.unipiaudiostories.ui.list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.R;
import com.example.unipiaudiostories.domain.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryRecyclerViewAdapter extends RecyclerView.Adapter<StoryRecyclerViewHolder> {

    private List<Story> stories;

    public StoryRecyclerViewAdapter() {
        this.stories = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setStories(List<Story> stories) {
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
        holder.setStoryItem(stories.get(position));
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }
}
