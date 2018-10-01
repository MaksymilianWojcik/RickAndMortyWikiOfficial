package com.example.mwojcik.rickandmorty_wiki.ui.list;

import com.example.mwojcik.rickandmorty_wiki.data.model.character.Character;
import com.example.mwojcik.rickandmorty_wiki.ui.base.RemoteView;

import java.util.List;

public interface CharacterListContract {

    interface View {
        void onInitialListRequested();
        void onListEndReached(String pageNumber, String searchQuery);
        void onCharacterSearch(String searchQuery);
    }

    interface ListView extends RemoteView {
        void showCharacters(List<Character> characterList);
        void showSearchedCharacters(List<Character> characterList);
    }
}
