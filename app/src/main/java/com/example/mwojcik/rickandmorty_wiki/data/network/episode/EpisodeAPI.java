package com.example.mwojcik.rickandmorty_wiki.data.network.episode;

import com.example.mwojcik.rickandmorty_wiki.data.model.Episode;
import com.example.mwojcik.rickandmorty_wiki.data.network.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpisodeAPI {

    @GET("api/episode/")
    Call<ApiResponse<Episode>> getEpisodes();

    @GET("api/episode/{id}")
    Call<Episode> getEpisodeById(@Path("id") int id);

    @GET("api/episode/{ids}")
    Call<List<Episode>> getEpisodesByIds(@Path("ids") String ids);
}
