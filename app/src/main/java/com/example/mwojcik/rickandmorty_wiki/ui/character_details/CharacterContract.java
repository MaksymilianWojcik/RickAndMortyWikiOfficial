package com.example.mwojcik.rickandmorty_wiki.ui.character_details;

import com.example.mwojcik.rickandmorty_wiki.data.model.Episode;
import com.example.mwojcik.rickandmorty_wiki.data.model.Location;
import com.example.mwojcik.rickandmorty_wiki.data.model.character.Character;

import java.util.List;

public interface CharacterContract {
    //TODO: Cleanup
    interface ViewActions{
        void onCharacterRequested(int characterId);
        void onCharacterEpisodesListRequested();
        void onCharacterLocationRequested();
    }

    interface CharacterView {
        void showCharacter(Character character);
        void showEpisodesList(List<Episode> episodeList);
        void showLocationDetails(Location location);
        void showProgress();
        void hideProgres();
        void showUnauthorizedError();
        void showError(String errorMessage);
    }
}
