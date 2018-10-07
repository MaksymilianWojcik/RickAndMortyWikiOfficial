package com.example.mwojcik.rickandmorty_wiki.ui.character_details;

import com.example.mwojcik.rickandmorty_wiki.data.DataManager;
import com.example.mwojcik.rickandmorty_wiki.data.model.Episode;
import com.example.mwojcik.rickandmorty_wiki.data.model.Location;
import com.example.mwojcik.rickandmorty_wiki.data.model.character.Character;
import com.example.mwojcik.rickandmorty_wiki.data.network.RemoteCallback;
import com.example.mwojcik.rickandmorty_wiki.ui.base.BasePresenter;
import com.example.mwojcik.rickandmorty_wiki.utils.UrlsUtil;
import com.example.mwojcik.rickandmorty_wiki.utils.Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CharacterPresenter extends BasePresenter<CharacterContract.CharacterView> implements CharacterContract.ViewActions {
    //TODO: Logs were removed for productions - mind that
    //TODO: Cleanup - check with contract and view
    private DataManager dataManager;
    private Character character;

//    public CharacterPresenter(DataManager dataManager){
//        this.dataManager = dataManager;
//    }

    public CharacterPresenter(DataManager dataManager, Character character){
        this.dataManager = dataManager;
        this.character = character;
    }

    @Override
    public void onCharacterRequested(int characterId) {
        getCharacter(characterId);
        //TODO: Leave injection via constructor? or manual method inject?
//        if (mView instanceof CharacterDetailsActivity) {
//            Character character1 = ((CharacterDetailsActivity) mView).getIntent().getParcelableExtra("CharacterClicked");
//            attachCharacter(character);
//            Log.d("CharacterPresenter", character1.toString());
//        }
    }

    @Override
    public void onCharacterEpisodesListRequested() {
        if (!isCharacterAttached()){
            return;
        }
        if (character.getEpisodeUrls().size() > 0) {
            int[] array = new int[character.getEpisodeUrls().size()];
            int temp = 0;
            for (String s : character.getEpisodeUrls()) {
                String id = UrlsUtil.getIdFromUrl(s);
                array[temp] = Integer.parseInt(id);
                temp++;
            }
            String result = Utils.getIdsSplittedByComa(array);
            if (temp > 1) {
                getEpisodesAll(result);
            } else {
                getEpisode(result);
            }
        }
    }

    @Override
    public void onCharacterLocationRequested() {
        if (!isCharacterAttached()){
            return;
        }
        int id = Integer.parseInt(UrlsUtil.getIdFromUrl(character.getLocation().getLocationUrl()));
        getLocation(id);
    }

    private void getLocation(int id) {
        if (!isViewAttached()) {
            return;
        }
        mView.showProgress();
        String idStr = String.valueOf(id);
        dataManager.getLocationById(idStr, new RemoteCallback<Location>() {
            @Override
            public void onSuccess(Location response) {
                mView.hideProgres();
                mView.showLocationDetails(response);
            }

            @Override
            public void onUnauthorized() {
                mView.hideProgres();
                mView.showUnauthorizedError();
            }

            @Override
            public void onFailed(Throwable throwable) {
                mView.hideProgres();
                mView.showError(throwable.getMessage());
            }
        });
    }


    private void getCharacter(int id){
        if (!isViewAttached()) {
            return;
        }
        if (!isCharacterAttached()){
            return;
        }
        SimpleDateFormat dateFormatterIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat dateFormatterOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        String formattedDate = "";
        try {
            date = dateFormatterIn.parse(character.getCreatedDate());
            formattedDate = dateFormatterOut.format(date);
            character.setCreatedDate(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mView.showCharacter(character);
    //TODO: do zastanowienia, czy chce tu przekazywaco obiekt czy pobierac. Po to zrobilem parcelable
    }


    private void getEpisodesAll(String episodesIds) {
        if (!isViewAttached()) {
            return;
        }
        mView.showProgress();

        dataManager.getEpisodesByIds(episodesIds, new RemoteCallback<List<Episode>>() {
            @Override
            public void onSuccess(List<Episode> response) {
                mView.hideProgres();
                mView.showEpisodesList(response);
            }

            @Override
            public void onUnauthorized() {
                mView.hideProgres();
                mView.showUnauthorizedError();
            }

            @Override
            public void onFailed(Throwable throwable) {
                mView.hideProgres();
                mView.showError(throwable.getMessage());
            }
        });
    }

    private void getEpisode(String id){
        if (!isViewAttached()) {
            return;
        }
        mView.showProgress();

        dataManager.getEpisodeById(Integer.parseInt(id), new RemoteCallback<Episode>() {
            @Override
            public void onSuccess(Episode response) {
                List<Episode> episodes = new ArrayList<>();
                episodes.add(response);
                mView.showEpisodesList(episodes);
            }

            @Override
            public void onUnauthorized() {
                mView.hideProgres();
                mView.showUnauthorizedError();
            }

            @Override
            public void onFailed(Throwable throwable) {
                mView.hideProgres();
                mView.showError(throwable.getMessage());
            }
        });
    }

    private boolean isCharacterAttached(){
        return character != null;
    }

}
