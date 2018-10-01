package com.example.mwojcik.rickandmorty_wiki.data.network.episode;

import com.example.mwojcik.rickandmorty_wiki.data.model.Episode;
import com.example.mwojcik.rickandmorty_wiki.data.network.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EpisodeAPI {

    @GET("api/episode/")
    Call<ApiResponse<Episode>> getEpisodes();
}
