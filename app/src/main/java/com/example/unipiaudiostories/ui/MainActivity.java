package com.example.unipiaudiostories.ui;

import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.core.StoryService;
import com.example.unipiaudiostories.databinding.ActivityMainBinding;
import com.example.unipiaudiostories.domain.Story;
import com.example.unipiaudiostories.ui.list.StoryRecyclerViewAdapter;

public class MainActivity extends AppActivityBase<ActivityMainBinding> {
    @Override
    protected void onAfterCreate() {
        RecyclerView storiesList = getBinding().rvStories;

        StoryRecyclerViewAdapter adapter = new StoryRecyclerViewAdapter(this::navigateToStory);
        storiesList.setAdapter(adapter);
        storiesList.setLayoutManager(new GridLayoutManager(this, 2));

        StoryService storyService = new StoryService();

        storyService.getAllStories(stories -> {
            if (stories == null)
                return;

            adapter.setStories(stories);
        });
    }

    private void navigateToStory(Story story) {
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra("story_id", story.getId());

        startActivity(intent);
    }
}