package com.example.mwojcik.rickandmorty_wiki.utils;

//TODO: Cleaning with Utils classes - one class
public class Utils {

    public static String getIdsSplittedByComa(int[] array){
        String episodes = "";
        StringBuilder builder = new StringBuilder(episodes);
        for(int i = 0 ; i < array.length; i++){
            builder.append(String.valueOf(array[i]));
            if (i<(array.length-1)){
                builder.append(",");
            }
        }
        String result = builder.toString();
        return result;
    }
}
