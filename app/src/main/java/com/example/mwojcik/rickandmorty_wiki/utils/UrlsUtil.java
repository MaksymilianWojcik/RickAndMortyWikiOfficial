package com.example.mwojcik.rickandmorty_wiki.utils;

public class UrlsUtil {
    public static String getIdFromUrl(String url){
        StringBuilder stringBuilder = new StringBuilder(url);
        int beginningOfNumberIndex = stringBuilder.lastIndexOf("/") +1;
        String numberStr = stringBuilder.substring(beginningOfNumberIndex);
        int number = Integer.parseInt(numberStr);
        return numberStr;
    }
}

//TODO: Dimensions into dimensions - general todo