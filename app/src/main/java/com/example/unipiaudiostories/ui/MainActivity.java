package com.example.unipiaudiostories.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.core.StatisticsService;
import com.example.unipiaudiostories.core.StoryService;
import com.example.unipiaudiostories.databinding.ActivityMainBinding;
import com.example.unipiaudiostories.domain.Story;
import com.example.unipiaudiostories.ui.constants.ActivityIntents;
import com.example.unipiaudiostories.ui.list.story.StoryRecyclerViewAdapter;
import com.example.unipiaudiostories.ui.list.story.StoryRecyclerViewHelper;

public class MainActivity extends AppActivityBase<ActivityMainBinding> {

    private StatisticsService statisticsService;

    @Override
    protected void onAfterCreate() {
        RecyclerView storiesList = getBinding().rvStories;
        StoryRecyclerViewHelper.applyStyling(this, storiesList);

        StoryRecyclerViewAdapter adapter = new StoryRecyclerViewAdapter(this::navigateToStory);
        storiesList.setAdapter(adapter);

        StoryService storyService = new StoryService();
        statisticsService = new StatisticsService(this, storyService);
        storyService.getAllStories(stories -> {
            if (stories == null)
                return;

            runOnUiThread(() -> adapter.setStories(stories));
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getBinding().textView.getLayoutParams();
            params.setMargins(0, 20, 0, 0);
            getBinding().textView.setLayoutParams(params);
        }
    }

    private void navigateToStory(Story story) {
        statisticsService.addListen(story.getId());

        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra(ActivityIntents.STORY_ID, story.getId());
        startActivity(intent);
    }
}