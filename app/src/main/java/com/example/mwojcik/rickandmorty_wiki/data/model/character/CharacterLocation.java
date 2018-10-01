package com.example.mwojcik.rickandmorty_wiki.data.model.character;

import com.google.gson.annotations.SerializedName;

public class CharacterLocation {

    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String locationUrl;

    public String getName() {
        return name;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    @Override
    public String toString() {
        return "CharacterLocation{" +
                "name='" + name + '\'' +
                ", locationUrl='" + locationUrl + '\'' +
                '}';
    }
}
