package com.example.unipiaudiostories.ui;

import com.example.unipiaudiostories.core.StoryService;
import com.example.unipiaudiostories.core.TtsService;
import com.example.unipiaudiostories.databinding.ActivityStoryBinding;
import com.example.unipiaudiostories.ui.constants.ActivityIntents;

public class StoryActivity extends AppActivityBase<ActivityStoryBinding> {
    private TtsService tts;

    @Override
    protected void onAfterCreate() {
        tts = new TtsService(this);

        int storyId = getIntent().getIntExtra(ActivityIntents.STORY_ID, Integer.MIN_VALUE);
        if (storyId == Integer.MIN_VALUE) {
            throw new IllegalStateException("StoryActivity expected STORY_ID extra in intent.");
        }

        StoryService storyService = new StoryService();
        storyService.getStoryById(storyId, story -> {
            if (story == null) {
                return;
            }

            getBinding().ivStoryItem.setImageResource(story.getImage());
            getBinding().tvTitle.setText(story.getTitle());
            getBinding().tvStoryAuthor.setText(story.getAuthor());
            getBinding().tvStoryRelease.setText(String.valueOf(story.getYear()));

            tts.readStory(story);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }
}