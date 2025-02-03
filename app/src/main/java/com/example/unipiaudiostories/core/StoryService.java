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
        stories.add(new Story(1, "The Cat and The Rainbow Rain", "Test", "George Niko", 2025, R.drawable.cat));
        stories.add(new Story(2, "Piece of Cake!", "Test", "Mary Dimi", 2023, R.drawable.birthday_dog));
        stories.add(new Story(3, "Beautiful Bugs", "Test", "Konstantinos Sklav", 2024, R.drawable.bugs));
        stories.add(new Story(4, "A Great Adventure", "Test", "George Niko", 2024, R.drawable.sunset_dog));
        stories.add(new Story(5, "Pretty Walls", "Test", "Mary Dimi", 2025, R.drawable.drawings_on_walls));
        stories.add(new Story(6, "Chalk Fantasy", "Test", "Konstantinos Sklav", 2024, R.drawable.chalk_drawing));
    }

    public List<Story> getAllStories() {
        // TODO!
        return new ArrayList<>();
    }
}
