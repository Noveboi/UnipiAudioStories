package com.example.unipiaudiostories.core;

import com.example.unipiaudiostories.R;
import com.example.unipiaudiostories.domain.Story;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods that cover the 'story' use-cases.
 */
public class StoryService {

    private final DatabaseReference storiesReference;
    private final FirebaseDatabase database;

    public StoryService() {
        database = FirebaseDatabase.getInstance();
        storiesReference = database.getReference("stories");

        // Add stories if they don't exist
        List<Story> stories = new ArrayList<>();
        stories.add(new Story(1, "Example", "Test", "Mary", 2025, R.drawable.cat));
    }

    public List<Story> getAllStories() {
        // TODO!
        return new ArrayList<>();
    }
}
