package com.example.mwojcik.rickandmorty_wiki.ui.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mwojcik.rickandmorty_wiki.ui.list.CharactersListActivity;

public class SplashActivity extends AppCompatActivity {

    //TODO: Better splash activity - text with my name
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashActivity.this, CharactersListActivity.class));
        finish();
    }
}
