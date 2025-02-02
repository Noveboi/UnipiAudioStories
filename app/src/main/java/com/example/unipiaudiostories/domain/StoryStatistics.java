package com.example.unipiaudiostories.domain;

/**
 * Decorates the Story class with user-related statistics.
 */
public class StoryStatistics {
    Story story;
    boolean isFavorite;
    int numberOfListens;

    public StoryStatistics(Story story, boolean isFavorite, int numberOfListens) {
        this.story = story;
        this.isFavorite = isFavorite;
        this.numberOfListens = numberOfListens;
    }

    public Story getStory() {
        return story;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getNumberOfListens() {
        return numberOfListens;
    }
}
