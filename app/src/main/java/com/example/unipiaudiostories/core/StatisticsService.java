package com.example.unipiaudiostories.core;

import com.example.unipiaudiostories.domain.StoryStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Retrieves user statistics from local storage
 */
public class StatisticsService {



    /**
     * Retrieves user statistics for each story.
     */
    public List<StoryStatistics> getStatisticsForStories() {
        // TODO!
        return new ArrayList<>();
    }

    public List<StoryStatistics> getFavoriteStories() {
        // TODO!
        return new ArrayList<>();
    }

    public void toggleFavorite(UUID storyId) {
        // TODO!
    }

    public void addListen(UUID storyId) {
        // TODO!
    }
}
