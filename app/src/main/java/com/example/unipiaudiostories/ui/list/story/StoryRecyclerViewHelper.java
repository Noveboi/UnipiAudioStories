package com.example.unipiaudiostories.ui.list.story;

import android.content.Context;
import android.content.res.Resources;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unipiaudiostories.R;

public class StoryRecyclerViewHelper {
    public static void applyStyling(Context context, RecyclerView storiesList) {
        Resources resources = context.getResources();

        float columnWidth = resources.getDimension(R.dimen.story_item_width);
        int itemSpacing = 15;
        float itemSize = columnWidth + itemSpacing + 10;
        int spanCount = (int) Math.max(2.0f, resources.getDisplayMetrics().widthPixels / itemSize);

        storiesList.setLayoutManager(new GridLayoutManager(context, spanCount));
        storiesList.setItemAnimator(new DefaultItemAnimator());
        storiesList.addItemDecoration(new StoryItemDecoration(itemSpacing));
    }
}
