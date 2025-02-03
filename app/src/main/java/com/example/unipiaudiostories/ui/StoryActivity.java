package com.example.unipiaudiostories.ui;

import com.example.unipiaudiostories.core.TtsService;
import com.example.unipiaudiostories.databinding.ActivityStoryBinding;

public class StoryActivity extends AppActivityBase<ActivityStoryBinding> {
    private TtsService tts;

    @Override
    protected void onAfterCreate() {
        tts = new TtsService(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }
}