package com.example.mwojcik.rickandmorty_wiki.data.network.location;

import com.example.mwojcik.rickandmorty_wiki.data.network.RetrofitInstance;

public class LocationService {

    public static LocationAPI makeLocationService(){
        return RetrofitInstance.createService(LocationAPI.class);
    }
}
