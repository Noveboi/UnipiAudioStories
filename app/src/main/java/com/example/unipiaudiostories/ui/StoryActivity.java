package com.example.unipiaudiostories.ui;

import android.widget.ImageButton;

import com.example.unipiaudiostories.R;
import com.example.unipiaudiostories.core.StatisticsService;
import com.example.unipiaudiostories.core.StoryService;
import com.example.unipiaudiostories.core.TtsService;
import com.example.unipiaudiostories.databinding.ActivityStoryBinding;
import com.example.unipiaudiostories.ui.constants.ActivityIntents;

public class StoryActivity extends AppActivityBase<ActivityStoryBinding> {
    private TtsService tts;
    private ImageButton favoriteButton;

    @Override
    protected void onAfterCreate() {
        tts = new TtsService(this);
        favoriteButton = getBinding().btnFavorite;

        int storyId = getIntent().getIntExtra(ActivityIntents.STORY_ID, Integer.MIN_VALUE);
        if (storyId == Integer.MIN_VALUE) {
            throw new IllegalStateException("StoryActivity expected STORY_ID extra in intent.");
        }

        StoryService storyService = new StoryService();
        StatisticsService statsService = new StatisticsService(this, storyService);


        storyService.getStoryById(storyId, story -> {
            if (story == null) {
                return;
            }

            setFavImage(statsService.getStoryStatistics(story).isFavorite());

            getBinding().ivStoryItem.setImageResource(story.getImage());
            getBinding().tvTitle.setText(story.getTitle());
            getBinding().tvStory.setText(story.getContent());
            getBinding().tvStoryAuthor.setText(story.getAuthor());
            getBinding().tvStoryRelease.setText(String.valueOf(story.getYear()));

            favoriteButton.setOnClickListener(v -> {
                boolean isFavorite = statsService.toggleFavorite(storyId);
                getBinding().btnFavorite.setImageResource(isFavorite
                        ? R.drawable.filled_star
                        : R.drawable.empty_star);
            });

            tts.readStory(story);
        });
    }

    private void setFavImage(boolean isFavorite) {
        favoriteButton.setImageResource(isFavorite
                ? R.drawable.filled_star
                : R.drawable.empty_star);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }
}