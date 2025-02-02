package com.example.unipiaudiostories.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.unipiaudiostories.domain.Story;
import com.example.unipiaudiostories.domain.StoryStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Retrieves user statistics from local storage.
 */
public class StatisticsService {

    private final Context context;
    private final StoryService storyService;
    private static final String prefName = "UniPi-AudioStories_STATS";

    public StatisticsService(Context context, StoryService storyService) {
        this.context = context;
        this.storyService = storyService;
    }

    /**
     * Retrieves user statistics for each story. The statistics are locally stored using
     * SharedPreferences.
     */
    public List<StoryStatistics> getStatisticsForStories() {
        List<Story> stories = storyService.getAllStories();
        List<StoryStatistics> stats = new ArrayList<>(stories.size());

        for (Story story : stories) {
            stats.add(getStoryStatistics(story));
        }

        return stats;
    }

    /**
     * Get user statistics for a single story.
     */
    public StoryStatistics getStoryStatistics(Story story) {
        SharedPreferences sp = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        int listens = sp.getInt(listensKey(story.getId()), 0);
        boolean isFavorite = sp.getBoolean(favoriteKey(story.getId()), false);

        return new StoryStatistics(story, isFavorite, listens);
    }

    /**
     * Get all of the user's favorite stories.
     */
    public List<Story> getFavoriteStories() {
        return getStatisticsForStories().stream()
                .filter(StoryStatistics::isFavorite)
                .map(StoryStatistics::getStory)
                .collect(Collectors.toList());
    }

    /**
     * Favorite or un-favorite a story. For example, if a story is mark as 'Favorite' by the user,
     * toggleFavorite() will un-favorite the story and vice versa.
     */
    public void toggleFavorite(int storyId) {
        SharedPreferences sp = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        String key = favoriteKey(storyId);

        boolean isFavorite = sp.getBoolean(key, false);
        editor.putBoolean(key, !isFavorite);
        editor.apply();
    }

    public void addListen(int storyId) {
        SharedPreferences sp = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        String key = listensKey(storyId);

        int listens = sp.getInt(key, 0);
        editor.putInt(key, listens + 1);
        editor.apply();
    }

    private static String listensKey(int storyId) {
        return storyId + "_listens";
    }

    private static String favoriteKey(int storyId) {
        return storyId + "_isFavorite";
    }
}
