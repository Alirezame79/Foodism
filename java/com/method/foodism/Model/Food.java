package com.method.foodism.Model;

import com.google.gson.annotations.SerializedName;

public class Food {

    @SerializedName("name")
    public String name;
    @SerializedName("cost")
    public int cost;
    @SerializedName("image_url")
    public String imageUrl;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public String getImage_url() {
        return imageUrl;
    }
    public void setImage_url(String image_url) {
        this.imageUrl = image_url;
    }
}
