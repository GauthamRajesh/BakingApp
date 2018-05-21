package com.example.android.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecipeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Bundle b = getIntent().getExtras();
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.recipe_detail_container, fragment).commit();
    }
}
