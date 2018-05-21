package com.example.android.bakingapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.bakingapp.Ingredient;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SharedPrefSaver {
    public static final String RECIPE_NAME = "RecipeName";
    public static final String RECIPE_INGREDIENTS = "Ingredients";
    public static final String RECIPE = "Recipe";
    public static void saveRecipeIngredient(String name, ArrayList<Ingredient> ingredients, Context c) {
        SharedPreferences prefs = c.getSharedPreferences(RECIPE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(RECIPE_NAME, name);
        Gson gson = new Gson();
        editor.putString(RECIPE_INGREDIENTS, gson.toJson(ingredients));
        editor.apply();
    }
}
