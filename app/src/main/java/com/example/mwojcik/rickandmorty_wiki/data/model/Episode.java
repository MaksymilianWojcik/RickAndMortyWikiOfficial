package com.example.mwojcik.rickandmorty_wiki.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Episode {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("air_date")
    private String episodeDate;
    @SerializedName("episode")
    private String code;
    @SerializedName("characters")
    private List<String> characterUrls;
    @SerializedName("url")
    private String episodeUrl;
    @SerializedName("created")
    private String createdDate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEpisodeDate() {
        return episodeDate;
    }

    public String getCode() {
        return code;
    }

    public List<String> getCharacterUrls() {
        return characterUrls;
    }

    public String getEpisodeUrl() {
        return episodeUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", episodeDate='" + episodeDate + '\'' +
                ", code='" + code + '\'' +
                ", characterUrls=" + characterUrls +
                ", episodeUrl='" + episodeUrl + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
