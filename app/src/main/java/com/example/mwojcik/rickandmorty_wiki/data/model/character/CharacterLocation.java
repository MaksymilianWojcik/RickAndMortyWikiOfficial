package com.example.mwojcik.rickandmorty_wiki.data.model.character;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CharacterLocation implements Parcelable {

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

    public CharacterLocation(Parcel parcel){
        name = parcel.readString();
        locationUrl = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(locationUrl);
    }

    public static final Parcelable.Creator<CharacterLocation> CREATOR = new Parcelable.Creator<CharacterLocation>() {

        @Override
        public CharacterLocation createFromParcel(Parcel parcel) {
            return new CharacterLocation(parcel);
        }

        @Override
        public CharacterLocation[] newArray(int i) {
            return new CharacterLocation[i];
        }
    };
}
