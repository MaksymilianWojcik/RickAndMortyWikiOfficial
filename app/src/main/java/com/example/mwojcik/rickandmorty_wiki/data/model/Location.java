package com.example.mwojcik.rickandmorty_wiki.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("dimension")
    private String dimension;
    @SerializedName("residents")
    private List<String> residentsUrls;
    @SerializedName("url")
    private String locationUrl;
    @SerializedName("created")
    private String createdDate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDimension() {
        return dimension;
    }

    public List<String> getResidentsUrls() {
        return residentsUrls;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", dimension='" + dimension + '\'' +
                ", residentsUrls=" + residentsUrls +
                ", locationUrl='" + locationUrl + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
