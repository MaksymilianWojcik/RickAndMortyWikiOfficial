package com.example.mwojcik.rickandmorty_wiki.data.network.location;

import com.example.mwojcik.rickandmorty_wiki.data.model.Location;
import com.example.mwojcik.rickandmorty_wiki.data.network.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocationAPI {

    @GET("api/location")
    Call<ApiResponse<Location>> getLocations();

    @GET("api/location/{id}")
    Call<Location> getLocationById(@Path("id") String id);
}
