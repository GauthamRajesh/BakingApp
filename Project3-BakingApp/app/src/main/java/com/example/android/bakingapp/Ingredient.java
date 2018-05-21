package com.example.android.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
    private double quantity;
    private String measure;
    private String ingredientName;
    public Ingredient(double quantity, String measure, String ingredientName) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredientName = ingredientName;
    }
    private Ingredient(Parcel in) {
        quantity = in.readDouble();
        measure = in.readString();
        ingredientName = in.readString();
    }
    static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }
        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
    public String getIngredientName() {
        return ingredientName;
    }
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
    public String getMeasure() {
        return measure;
    }
    public void setMeasure(String measure) {
        this.measure = measure;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(quantity);
        dest.writeString(measure);
        dest.writeString(ingredientName);
    }
}
