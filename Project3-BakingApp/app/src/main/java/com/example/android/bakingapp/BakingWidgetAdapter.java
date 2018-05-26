package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.utils.SharedPrefSaver;

import java.util.ArrayList;

public class BakingWidgetAdapter implements RemoteViewsService.RemoteViewsFactory {
    private Context c;
    private ArrayList<Ingredient> ingredients;
    Intent i;
    public BakingWidgetAdapter(Context c, Intent i) {
        this.c = c;
        this.i = i;
    }
    @Override
    public void onCreate() {
        getData();
    }

    @Override
    public void onDataSetChanged() {
        getData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(c.getPackageName(),
                android.R.layout.simple_list_item_1);
        rv.setTextViewText(android.R.id.text1, ingredients.get(position).getIngredientName());
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    private void getData() {
        ingredients = SharedPrefSaver.getSavedIngredients(c);
    }
}
