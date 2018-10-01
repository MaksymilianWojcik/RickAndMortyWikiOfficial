package com.example.mwojcik.rickandmorty_wiki.data.model.character;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Character {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("status")
    private String status;
    @SerializedName("species")
    private String species;
    @SerializedName("type")
    private String type;
    @SerializedName("gender")
    private String gender;
    @SerializedName("origin")
    private CharacterOrigin origin;
    @SerializedName("location")
    private CharacterLocation characterLocation;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("episode")
    private List<String> episodeUrls;
    @SerializedName("url")
    private String characterUrl;
    @SerializedName("created")
    private String createdDate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public CharacterOrigin getOrigin() {
        return origin;
    }

    public CharacterLocation getLocation() {
        return characterLocation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getEpisodeUrls() {
        return episodeUrls;
    }

    public String getCharacterUrl() {
        return characterUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", species='" + species + '\'' +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                ", origin=" + origin +
                ", location=" + characterLocation +
                ", imageUrl='" + imageUrl + '\'' +
                ", episodeUrls=" + episodeUrls +
                ", characterUrl='" + characterUrl + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
