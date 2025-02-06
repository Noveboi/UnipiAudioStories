package com.example.unipiaudiostories.ui;

import com.example.unipiaudiostories.core.SettingsService;
import com.example.unipiaudiostories.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppActivityBase<ActivitySettingsBinding> {
    @Override
    protected void onAfterCreate() {
        getBinding().btnEnglish.setOnClickListener(v -> setLocale("en"));
        getBinding().btnGreek.setOnClickListener(v -> setLocale("el"));
        getBinding().btnSpanish.setOnClickListener(v -> setLocale("es"));
    }

    private void setLocale(String languageCode) {
        SettingsService.setLocale(this, languageCode);
        recreate();
    }
}