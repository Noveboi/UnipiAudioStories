package com.example.unipiaudiostories.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unipiaudiostories.R;
import com.example.unipiaudiostories.databinding.ActivityMainBinding;
import com.example.unipiaudiostories.domain.Story;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseDatabase database;
    DatabaseReference storiesReference;

    // This is a test. When we get the actual stories, we will add them in the database in this way!
    Story story1 = new Story(UUID.randomUUID(), "test", "test", "test", 2000, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = FirebaseDatabase.getInstance();
        storiesReference = database.getReference("stories");

        this.writeStoryToDb(story1);
    }

    public void writeStoryToDb(Story story){
        storiesReference.child("story1").setValue(story);
    }
}