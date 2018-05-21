package com.example.android.bakingapp.utils;

import android.os.AsyncTask;

import com.example.android.bakingapp.Ingredient;
import com.example.android.bakingapp.Recipe;
import com.example.android.bakingapp.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class JSONFetcher {
    public static ArrayList<Recipe> getRecipes() {
        try {
            return new RecipeTask().execute(Connection.URL).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class RecipeTask extends AsyncTask<String, Void, ArrayList<Recipe>> {
        @Override
        protected ArrayList<Recipe> doInBackground(String... strings) {
            final ArrayList<Recipe> recipes = new ArrayList<>();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();
                String inputString;
                while((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }
                JSONArray jsonArray = new JSONArray(builder.toString());;
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject recipe = jsonArray.getJSONObject(i);
                    int id = recipe.getInt("id");
                    String name = recipe.getString("name");
                    ArrayList<Ingredient> ingredients = new ArrayList<>();
                    JSONArray ingredientsArray = recipe.getJSONArray("ingredients");
                    for(int j = 0; j < ingredientsArray.length(); j++) {
                        JSONObject ingredient = ingredientsArray.getJSONObject(j);
                        ingredients.add(new Ingredient(ingredient.getDouble("quantity"), ingredient.getString("measure"), ingredient.getString("ingredient")));
                    }
                    ArrayList<Step> steps = new ArrayList<>();
                    JSONArray stepsArray = recipe.getJSONArray("steps");
                    for(int k = 0; k < stepsArray.length(); k++) {
                        JSONObject step = stepsArray.getJSONObject(k);
                        steps.add(new Step(step.getString("shortDescription"), step.getString("description"), step.getString("videoURL"),
                                step.getString("thumbnailURL")));
                    }
                    recipes.add(new Recipe(id, name, ingredients, steps, recipe.getInt("servings"), recipe.getString("image")));
                }
                urlConnection.disconnect();
                stream.close();
                bufferedReader.close();
            }
            catch(IOException | JSONException e) {
                e.printStackTrace();
            }
            return recipes;
        }
        @Override
        protected void onPostExecute(ArrayList<Recipe> recipes) {
        }
    }
}