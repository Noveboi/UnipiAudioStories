package com.example.unipiaudiostories.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.core.StatisticsService;
import com.example.unipiaudiostories.core.StoryService;
import com.example.unipiaudiostories.databinding.ActivityStatisticsBinding;
import com.example.unipiaudiostories.ui.list.stats.StatsRecyclerViewAdapter;
import com.example.unipiaudiostories.ui.list.stats.StatsRecyclerViewHelper;

public class StatisticsActivity extends AppActivityBase<ActivityStatisticsBinding> {

    @Override
    protected void onAfterCreate() {
        RecyclerView rv = getBinding().rvStories;
        StatsRecyclerViewAdapter adapter = new StatsRecyclerViewAdapter();

        rv.setAdapter(adapter);
        StatsRecyclerViewHelper.applyStyling(this, rv);

        StoryService storyService = new StoryService();
        StatisticsService statisticsService = new StatisticsService(this, storyService);

        statisticsService.getStatisticsForStories(adapter::addStats);
    }
}