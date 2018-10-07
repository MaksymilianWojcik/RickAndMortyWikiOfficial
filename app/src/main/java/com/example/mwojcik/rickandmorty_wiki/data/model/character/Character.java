package com.example.mwojcik.rickandmorty_wiki.data.model.character;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Character implements Parcelable {
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

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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

    public Character(Parcel parcel){
        id = parcel.readInt();
        name = parcel.readString();
        status = parcel.readString();
        species = parcel.readString();
        type = parcel.readString();
        gender = parcel.readString();
        origin = parcel.readParcelable(CharacterOrigin.class.getClassLoader());
        characterLocation = parcel.readParcelable(CharacterLocation.class.getClassLoader());
        imageUrl = parcel.readString();
        episodeUrls = parcel.createStringArrayList();
        characterUrl = parcel.readString();
        createdDate = parcel.readString();
    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {

        @Override
        public Character createFromParcel(Parcel parcel) {
            return new Character(parcel);
        }

        @Override
        public Character[] newArray(int i) {
            return new Character[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(status);
        parcel.writeString(species);
        parcel.writeString(type);
        parcel.writeString(gender);
        parcel.writeParcelable(origin, i);
        parcel.writeParcelable(characterLocation, i);
        parcel.writeString(imageUrl);
        parcel.writeStringList(episodeUrls);
        parcel.writeString(characterUrl);
        parcel.writeString(createdDate);
    }
}
