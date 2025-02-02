package com.example.unipiaudiostories.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.R;
import com.example.unipiaudiostories.databinding.ActivityMainBinding;
import com.example.unipiaudiostories.domain.Story;
import com.example.unipiaudiostories.ui.list.StoryRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

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

        RecyclerView storiesList = binding.rvStories;
        List<Story> stories = new ArrayList<>();
        stories.add(new Story(1, "Bugs", "ok", "Mary", 2024, R.drawable.bugs));
        stories.add(new Story(2, "Cat", "Ok", "Mary", 2025, R.drawable.cat));
        stories.add(new Story(3, "Dog Cake", "Ok", "Mary", 2025, R.drawable.birthday_dog));

        StoryRecyclerViewAdapter adapter = new StoryRecyclerViewAdapter(stories);
        storiesList.setAdapter(adapter);
        storiesList.setLayoutManager(new GridLayoutManager(this, 2));
    }
}