package com.example.mwojcik.rickandmorty_wiki.data.network.character;

import com.example.mwojcik.rickandmorty_wiki.data.model.character.Character;
import com.example.mwojcik.rickandmorty_wiki.data.network.ApiResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterAPI {

    @GET("api/character/")
    Call<ApiResponse<Character>> getCharacters();

    @GET("api/character/")
    Call<ApiResponse<Character>> getCharactersByPageNumber(@Query("page") String pageNumber);

    @GET("api/character/")
    Call<ApiResponse<Character>> getCharactersFiltered(@Query("page") String pageNumber, @Query("name") String name);
}
