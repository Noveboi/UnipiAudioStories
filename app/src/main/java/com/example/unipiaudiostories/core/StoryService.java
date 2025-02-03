package com.example.unipiaudiostories.core;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.unipiaudiostories.domain.Story;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    }

    public void getAllStories(Consumer<List<Story>> callback) {
        storiesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Story> stories = new ArrayList<>();
                for (DataSnapshot storySnapshot : snapshot.getChildren()) {
                    Story story = storySnapshot.getValue(Story.class);
                    stories.add(story);
                }

                callback.accept(stories);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.accept(null);
            }
        });

    }

    public void getStoryById(int id, Consumer<Story> callback) {
        storiesReference.child(String.valueOf(id)).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                if (snapshot.exists()) {
                    callback.accept(snapshot.getValue(Story.class));
                }
            } else {
                Log.e("Audio Stories FB", "Fetch error", task.getException());
            }

            callback.accept(null);
        });
    }
}
