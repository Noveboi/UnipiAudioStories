package com.example.unipiaudiostories.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.unipiaudiostories.domain.Story;
import com.example.unipiaudiostories.domain.StoryStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
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
    public void getStatisticsForStories(Consumer<List<StoryStatistics>> callback) {
        storyService.getAllStories(stories -> {
            if (stories == null) {
                callback.accept(null);
                return;
            }

            List<StoryStatistics> statistics = new ArrayList<>();
            for (Story story : stories) {
                statistics.add(getStoryStatistics(story));
            }

            callback.accept(statistics);
        });
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
    public void getFavoriteStories(Consumer<List<Story>> callback) {
        getStatisticsForStories(stats -> {
            if (stats == null) {
                callback.accept(null);
                return;
            }

            List<Story> favoriteStories = stats.stream()
                    .filter(StoryStatistics::isFavorite)
                    .map(StoryStatistics::getStory)
                    .collect(Collectors.toList());

            callback.accept(favoriteStories);
        });
    }

    /**
     * Favorite or un-favorite a story. For example, if a story is mark as 'Favorite' by the user,
     * toggleFavorite() will un-favorite the story and vice versa.
     * @return The boolean value stored.
     */
    public boolean toggleFavorite(int storyId) {
        SharedPreferences sp = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        String key = favoriteKey(storyId);

        boolean isFavorite = sp.getBoolean(key, false);
        editor.putBoolean(key, !isFavorite);
        editor.apply();

        return !isFavorite;
    }

    /**
     * Increment the 'listen count' for the specified story.
     */
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
