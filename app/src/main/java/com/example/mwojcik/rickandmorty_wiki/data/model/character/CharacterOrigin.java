package com.example.mwojcik.rickandmorty_wiki.data.model.character;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CharacterOrigin implements Parcelable {

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

    public CharacterOrigin(Parcel parcel){
        name = parcel.readString();
        originUrl = parcel.readString();
    }

    public static final Parcelable.Creator<CharacterOrigin> CREATOR = new Parcelable.Creator<CharacterOrigin>() {

        @Override
        public CharacterOrigin createFromParcel(Parcel parcel) {
            return new CharacterOrigin(parcel);
        }

        @Override
        public CharacterOrigin[] newArray(int i) {
            return new CharacterOrigin[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(originUrl);
    }
}
