package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private ArrayList<Recipe> recipes;
    private Context c;
    public RecipeAdapter(ArrayList<Recipe> recipes, Context c) {
        this.recipes = recipes;
        this.c = c;
    }
    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        final Recipe r = recipes.get(position);
        holder.setRecipeName(r.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, RecipeDetail.class);
                i.putExtra("Recipe", r);
                c.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return recipes.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipeName)
        TextView recipeName;
        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
        void setRecipeName(String name) {
            recipeName.setText(name);
        }
    }
}
