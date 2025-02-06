package com.example.unipiaudiostories.ui.list.stats;

import android.content.Context;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StatsRecyclerViewHelper {
    public static void applyStyling(Context context, RecyclerView statsList) {
        statsList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        statsList.setItemAnimator(new DefaultItemAnimator());
    }
}
