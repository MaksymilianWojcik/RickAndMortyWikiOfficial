package com.example.mwojcik.rickandmorty_wiki.data.network.episode;

import com.example.mwojcik.rickandmorty_wiki.data.network.RetrofitInstance;

public class EpisodeService {

    public static EpisodeAPI makeEpisodeService(){
        return RetrofitInstance.createService(EpisodeAPI.class);
    }
}
