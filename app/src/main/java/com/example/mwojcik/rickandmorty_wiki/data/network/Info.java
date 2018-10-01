package com.example.mwojcik.rickandmorty_wiki.data.network;

import com.google.gson.annotations.SerializedName;

//TODO: Structure and class name
public class Info {
    @SerializedName("count")
    private int count;
    @SerializedName("pages")
    private int pages;
    @SerializedName("next")
    String nextPageUrl;
    @SerializedName("prev")
    String prevPageUrl;

    public int getCount() {
        return count;
    }

    public int getPages() {
        return pages;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }
}
