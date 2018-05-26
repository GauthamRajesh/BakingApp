package com.example.android.bakingapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.bakingapp.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

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
    public static ArrayList<Ingredient> getSavedIngredients(Context c) {
        SharedPreferences prefs = c.getSharedPreferences(RECIPE, Context.MODE_PRIVATE);
        String recipeArrayJson = prefs.getString(RECIPE_INGREDIENTS, "");
        Gson gson = new Gson();
        return gson.fromJson(recipeArrayJson, new TypeToken<List<Ingredient>>(){}.getType());
    }
    public static String getRecipeName(Context c) {
        SharedPreferences prefs = c.getSharedPreferences(RECIPE, Context.MODE_PRIVATE);
        return prefs.getString(RECIPE_NAME, "");
    }
}
