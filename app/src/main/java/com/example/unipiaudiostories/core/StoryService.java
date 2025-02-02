package com.example.unipiaudiostories.core;

import com.example.unipiaudiostories.domain.Story;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods that cover the 'story' use-cases.
 */
public class StoryService {

    FirebaseDatabase database;
    DatabaseReference storiesReference;

    public StoryService() {
        database = FirebaseDatabase.getInstance();
        storiesReference = database.getReference("stories");
    }

    public List<Story> getAllStories() {
        // TODO!
        return new ArrayList<>();
    }
}
