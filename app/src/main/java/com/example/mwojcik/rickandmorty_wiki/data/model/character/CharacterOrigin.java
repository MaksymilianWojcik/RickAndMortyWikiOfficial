package com.example.mwojcik.rickandmorty_wiki.data.model.character;

import com.google.gson.annotations.SerializedName;

public class CharacterOrigin {

    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String originUrl;

    public String getName() {
        return name;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    @Override
    public String toString() {
        return "CharacterOrigin{" +
                "name='" + name + '\'' +
                ", originUrl='" + originUrl + '\'' +
                '}';
    }
}
