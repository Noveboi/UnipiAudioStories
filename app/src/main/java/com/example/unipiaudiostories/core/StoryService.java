package com.example.unipiaudiostories.core;

import android.util.Log;

import com.example.unipiaudiostories.domain.Story;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.unipiaudiostories.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Provides methods that cover the 'story' use-cases.
 */
public class StoryService {

    private final DatabaseReference storiesReference;
    private final FirebaseDatabase database;

    public StoryService() {
        database = FirebaseDatabase.getInstance();
        storiesReference = database.getReference("stories");

        // Add the stories to the database if they don't exist.
        getAllStories(stories -> {
            if (stories == null || stories.isEmpty()) {
                List<Story> newStories = new ArrayList<>();
                newStories.add(new Story(1, "Rainbow Rain Day", "Test", "George Niko", 2025, R.drawable.cat));
                newStories.add(new Story(2, "Piece of Cake!", "Test", "Mary Dimi", 2023, R.drawable.birthday_dog));
                newStories.add(new Story(3, "Beautiful Bugs", "Test", "Konstantinos Sklav", 2024, R.drawable.bugs));
                newStories.add(new Story(4, "A Great Adventure", "Test", "George Niko", 2024, R.drawable.sunset_dog));
                newStories.add(new Story(5, "Pretty Walls", "Test", "Mary Dimi", 2025, R.drawable.drawings_on_walls));
                newStories.add(new Story(6, "Chalk Fantasy", "Test", "Konstantinos Sklav", 2024, R.drawable.chalk_drawing));

                for (Story story : newStories) {
                    storiesReference.child(String.valueOf(story.getId())).setValue(story);
                }
            }
        });
    }

    public void getAllStories(Consumer<List<Story>> callback) {
        storiesReference.get().addOnCompleteListener(task ->
        {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                if (snapshot.exists()) {
                    List<Story> stories = new ArrayList<>();
                    for (DataSnapshot storySnapshot : snapshot.getChildren()) {
                        Story story = storySnapshot.getValue(Story.class);
                        stories.add(story);
                    }

                    callback.accept(stories);
                    return;

                } else {
                    Log.w("Audio Stories FB", "Snapshot 'stories' doesn't exist.");
                }
            }

            callback.accept(null);
        });
    }

    public void getStoryById(int id, Consumer<Story> callback) {
        storiesReference.child(String.valueOf(id)).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                if (snapshot.exists()) {
                    callback.accept(snapshot.getValue(Story.class));
                    return;
                }
            } else {
                Log.e("Audio Stories FB", "Fetch error", task.getException());
            }

            callback.accept(null);
        });
    }
}
