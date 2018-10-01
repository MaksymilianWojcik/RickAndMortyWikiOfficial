package com.example.mwojcik.rickandmorty_wiki.utils;

public class UrlsUtil {

    //TODO: Not checked if working - prepared for getting episode/location info via episode call
    public static String getIdFromUrl(String url){
        StringBuilder stringBuilder = new StringBuilder(url);
        int beginningOfNumberIndex = stringBuilder.lastIndexOf("/");
        String numberStr = stringBuilder.substring(beginningOfNumberIndex);
        int number = Integer.parseInt(numberStr);
        return numberStr;
    }

}

/*
https://rickandmortyapi.com/api/location/3
 */