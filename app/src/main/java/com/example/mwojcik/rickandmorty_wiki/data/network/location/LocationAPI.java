package com.example.mwojcik.rickandmorty_wiki.data.network.location;

import com.example.mwojcik.rickandmorty_wiki.data.model.Location;
import com.example.mwojcik.rickandmorty_wiki.data.network.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationAPI {

    @GET("api/location")
    Call<ApiResponse<Location>> getLocations();
}
