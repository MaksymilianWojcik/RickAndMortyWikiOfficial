package com.example.mwojcik.rickandmorty_wiki.data.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse<T> {

    @SerializedName("info")
    private Info info;
    @SerializedName("results")
    private List<T> resultsList;

    public List<T> getResultsList() {
        return resultsList;
    }

    public Info getInfo() {
        return info;
    }
}
