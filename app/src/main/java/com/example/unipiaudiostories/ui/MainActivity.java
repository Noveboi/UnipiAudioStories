package com.example.unipiaudiostories.ui;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.R;
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
        stories.add(new Story(1, "Rainbow Rain", "Test", "George Niko", 2025, R.drawable.cat));
        stories.add(new Story(2, "Piece of Cake!", "Test", "Mary Dimi", 2023, R.drawable.birthday_dog));
        stories.add(new Story(3, "Beautiful Bugs", "Test", "Konstantinos Sklav", 2024, R.drawable.bugs));
        stories.add(new Story(4, "A Great Adventure", "Test", "George Niko", 2024, R.drawable.sunset_dog));
        stories.add(new Story(5, "Pretty Walls", "Test", "Mary Dimi", 2025, R.drawable.drawings_on_walls));
        stories.add(new Story(6, "Chalk Fantasy", "Test", "Konstantinos Sklav", 2024, R.drawable.chalk_drawing));

        StoryRecyclerViewAdapter adapter = new StoryRecyclerViewAdapter(stories);
        storiesList.setAdapter(adapter);
        storiesList.setLayoutManager(new GridLayoutManager(this, 2));
    }
}