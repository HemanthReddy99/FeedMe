package com.anupam.androidcameraxtflite;

public class Recipe
{
    private String Title;
    private String url;
    private String mingredient;

    public Recipe()
    {
        Title = null;
        this.url = null;
        this.mingredient = null;
    }

    public Recipe(String title, String url,String ingredient) {
        Title = title;
        this.url = url;
        this.mingredient = ingredient;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIngredient() {
        return mingredient;
    }

    public void setIngredient(String ingredient) {
        mingredient = ingredient;
    }
}
