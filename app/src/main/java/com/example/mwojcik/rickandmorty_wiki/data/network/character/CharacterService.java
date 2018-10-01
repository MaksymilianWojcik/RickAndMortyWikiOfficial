package com.example.mwojcik.rickandmorty_wiki.data.network.character;

import com.example.mwojcik.rickandmorty_wiki.data.network.RetrofitInstance;

public class CharacterService {

    public static CharacterAPI makeCharacterService(){
        return RetrofitInstance.createService(CharacterAPI.class);
    }

}
