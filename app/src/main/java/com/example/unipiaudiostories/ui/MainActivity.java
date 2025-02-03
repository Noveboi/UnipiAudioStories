package com.example.unipiaudiostories.ui;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.R;
import com.example.unipiaudiostories.core.StoryService;
import com.example.unipiaudiostories.databinding.ActivityMainBinding;
import com.example.unipiaudiostories.domain.Story;
import com.example.unipiaudiostories.ui.list.StoryRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppActivityBase<ActivityMainBinding> {
    @Override
    protected void onAfterCreate() {
        RecyclerView storiesList = getBinding().rvStories;
        List<Story> stories = new ArrayList<>();
        stories.add(new Story(1, "Bugs", "ok", "Mary", 2024, R.drawable.bugs));
        stories.add(new Story(2, "Cat", "Ok", "Mary", 2025, R.drawable.cat));
        stories.add(new Story(3, "Dog Cake", "Ok", "Mary", 2025, R.drawable.birthday_dog));

        StoryRecyclerViewAdapter adapter = new StoryRecyclerViewAdapter(stories);
        storiesList.setAdapter(adapter);
        storiesList.setLayoutManager(new GridLayoutManager(this, 2));

        StoryService storyService = new StoryService();

        storyService.getAllStories(stories -> {
            if (stories == null)
                return;

            adapter.setStories(stories);
        });
    }
}