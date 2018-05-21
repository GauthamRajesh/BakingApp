package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapp.utils.Connection;
import com.example.android.bakingapp.utils.JSONFetcher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Recipe> recipes = new ArrayList<>();
        if(Connection.checkConnection(this)) {
            recipes = JSONFetcher.getRecipes();
        }
        RecyclerView rvRecipes = findViewById(R.id.rvRecipes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvRecipes.setLayoutManager(layoutManager);
        rvRecipes.setAdapter(new RecipeAdapter(recipes, this));
    }
}