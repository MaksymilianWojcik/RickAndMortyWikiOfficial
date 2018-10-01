package com.example.mwojcik.rickandmorty_wiki.data;

import com.example.mwojcik.rickandmorty_wiki.data.model.Episode;
import com.example.mwojcik.rickandmorty_wiki.data.model.Location;
import com.example.mwojcik.rickandmorty_wiki.data.model.character.Character;
import com.example.mwojcik.rickandmorty_wiki.data.network.ApiResponse;
import com.example.mwojcik.rickandmorty_wiki.data.network.RemoteCallback;
import com.example.mwojcik.rickandmorty_wiki.data.network.character.CharacterAPI;
import com.example.mwojcik.rickandmorty_wiki.data.network.character.CharacterService;
import com.example.mwojcik.rickandmorty_wiki.data.network.episode.EpisodeAPI;
import com.example.mwojcik.rickandmorty_wiki.data.network.episode.EpisodeService;
import com.example.mwojcik.rickandmorty_wiki.data.network.location.LocationAPI;
import com.example.mwojcik.rickandmorty_wiki.data.network.location.LocationService;

//TODO: Cleanup - Different Data Managers for different services
public class DataManager {

    private static DataManager mInstance;
    private final CharacterAPI mCharacterService;
    private final LocationAPI mLocationService;
    private final EpisodeAPI mEpisodeService;

    public static DataManager getInstance(){
        if (mInstance == null){
            mInstance = new DataManager();
        }
        return mInstance;
    }

    private DataManager(){
        mCharacterService = CharacterService.makeCharacterService();
        mLocationService = LocationService.makeLocationService();
        mEpisodeService = EpisodeService.makeEpisodeService();
    }

    //TODO: Cleanup
    public void getCharactersList(RemoteCallback<ApiResponse<Character>> listener){
        mCharacterService.getCharacters().enqueue(listener);
    }
    //TODO: Cleanup
    public void getCharactersListByPage(String pageNumber, RemoteCallback<ApiResponse<Character>> listener){
        mCharacterService.getCharactersByPageNumber(pageNumber).enqueue(listener);
    }
    //TODO: Cleanup - renaming
    public void getCharactersListFiltered(String pageNumber, String searchQuery, RemoteCallback<ApiResponse<Character>> listener){
        mCharacterService.getCharactersFiltered(pageNumber, searchQuery).enqueue(listener);
    }

    public void getLocationsList(RemoteCallback<ApiResponse<Location>> listener){
        mLocationService.getLocations().enqueue(listener);
    }

    public void getEpisodesList(RemoteCallback<ApiResponse<Episode>> listener){
        mEpisodeService.getEpisodes().enqueue(listener);
    }
}
