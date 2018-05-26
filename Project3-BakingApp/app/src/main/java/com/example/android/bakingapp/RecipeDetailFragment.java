package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.utils.SharedPrefSaver;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailFragment extends Fragment implements FragmentClickInterface {
    @BindView(R.id.rvIngredients)
    RecyclerView rvIngredients;
    @BindView(R.id.rvSteps)
    RecyclerView rvSteps;
    ArrayList<Ingredient> ingredients;
    ArrayList<Step> steps;
    public RecipeDetailFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, v);
        Bundle b = getArguments();
        Recipe r = b.getParcelable("Recipe");
        ingredients = r.getIngredients();
        steps = r.getSteps();
        SharedPrefSaver.saveRecipeIngredient(r.getName(), ingredients, getContext());
        BakingWidgetService.startWidget(getContext());
        RecyclerView.LayoutManager layoutManagerSteps = new LinearLayoutManager(v.getContext());
        rvSteps.setLayoutManager(layoutManagerSteps);
        RecyclerView.LayoutManager layoutManagerIngredients = new LinearLayoutManager(v.getContext());
        rvIngredients.setLayoutManager(layoutManagerIngredients);
        IngredientsAdapter i = new IngredientsAdapter(ingredients, v.getContext());
        StepsAdapter s = new StepsAdapter(steps, v.getContext(), this);
        rvIngredients.setAdapter(i);
        rvSteps.setAdapter(s);
        return v;
    }
    @Override
    public void onClick(String description, String videoURL, int position, Context c, ArrayList<Step> steps) {
        launchDetailActivity(description, videoURL, position, c, steps);
    }
    private void launchDetailActivity(String description, String videoURL, int position, Context c, ArrayList<Step> steps) {
        Intent i = new Intent(c, DetailActivity.class);
        Bundle b = new Bundle();
        b.putParcelableArrayList("steps", steps);
        b.putString("description", description);
        b.putString("videoURL", videoURL);
        b.putInt("position", position);
        i.putExtras(b);
        c.startActivity(i);
    }
}
