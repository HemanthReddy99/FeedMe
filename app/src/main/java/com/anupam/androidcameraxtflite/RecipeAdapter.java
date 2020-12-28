package com.anupam.androidcameraxtflite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter<Recipe> {
    private Context mcontet;
    private ArrayList<Recipe> mrecipes;
    public RecipeAdapter(@NonNull Context context, ArrayList<Recipe> recipes) {
        super(context, 0,recipes);
        mcontet = context;
        mrecipes = recipes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipelistitemlayout, parent, false);

        TextView views = convertView.findViewById(R.id.title);
        TextView iview = convertView.findViewById(R.id.ingredients);
        views.setText(mrecipes.get(position).getTitle());
        iview.setText(mrecipes.get(position).getIngredient());

        return convertView;
    }
    public void setItemList(ArrayList<Recipe> itemList) {
        this.mrecipes = itemList;
    }


}
