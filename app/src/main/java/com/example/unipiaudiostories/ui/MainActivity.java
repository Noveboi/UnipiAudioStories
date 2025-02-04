package com.example.unipiaudiostories.ui;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.core.StoryService;
import com.example.unipiaudiostories.databinding.ActivityMainBinding;
import com.example.unipiaudiostories.domain.Story;
import com.example.unipiaudiostories.ui.constants.ActivityIntents;
import com.example.unipiaudiostories.ui.list.StoryRecyclerViewAdapter;
import com.example.unipiaudiostories.ui.list.StoryRecyclerViewHelper;

public class MainActivity extends AppActivityBase<ActivityMainBinding> {
    @Override
    protected void onAfterCreate() {
        RecyclerView storiesList = getBinding().rvStories;
        StoryRecyclerViewHelper.applyStyling(this, storiesList);

        StoryRecyclerViewAdapter adapter = new StoryRecyclerViewAdapter(this::navigateToStory);
        storiesList.setAdapter(adapter);

        StoryService storyService = new StoryService();
        storyService.getAllStories(stories -> {
            if (stories == null)
                return;

            runOnUiThread(() -> adapter.setStories(stories));
        });
    }

    private void navigateToStory(Story story) {
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra(ActivityIntents.STORY_ID, story.getId());

        startActivity(intent);
    }
}