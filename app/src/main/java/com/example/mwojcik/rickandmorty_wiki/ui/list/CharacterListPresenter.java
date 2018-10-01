package com.example.mwojcik.rickandmorty_wiki.ui.list;

import android.text.TextUtils;
import android.util.Log;

import com.example.mwojcik.rickandmorty_wiki.data.DataManager;
import com.example.mwojcik.rickandmorty_wiki.data.model.character.Character;
import com.example.mwojcik.rickandmorty_wiki.data.network.ApiResponse;
import com.example.mwojcik.rickandmorty_wiki.data.network.RemoteCallback;
import com.example.mwojcik.rickandmorty_wiki.ui.base.BasePresenter;

import java.util.List;

public class CharacterListPresenter extends BasePresenter<CharacterListContract.ListView> implements CharacterListContract.View {

    private static final String TAG = "CharacterListPresenter";
    //TODO: Cleanup - sometimes String, sometimes int?
    private static final String INIT_PAGE_NUMBER = "1";
    private final DataManager mDataManager;

    public CharacterListPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void onInitialListRequested() {
        getCharacters(INIT_PAGE_NUMBER, null);
    }

    @Override
    public void onListEndReached(String pageNumber, String searchQuery) {
        getCharacters(pageNumber, searchQuery);
    }

    @Override
    public void onCharacterSearch(String searchQuery) {
        getCharacters(INIT_PAGE_NUMBER, searchQuery);
    }

    private void getCharacters(String pageNumber, final String searchQuery){
        if (!isViewAttached()){
            Log.w(TAG, "View is not attached");
            return;
        }

        mView.showProgress();
        mDataManager.getCharactersListFiltered(pageNumber, searchQuery, new RemoteCallback<ApiResponse<Character>>() {
            @Override
            public void onSuccess(ApiResponse<Character> response) {
                if (!isViewAttached()){
                    Log.w(TAG, "View is not attached");
                    return;
                }
                mView.hideProgres();
                List<Character> responseResults = response.getResultsList();
                if(responseResults.isEmpty()){
                    mView.showEmpty();
                    return;
                }
                Log.d(TAG, "response pages: " + response.getInfo().getPages()
                        + ", results count: " + responseResults.size());

                //TODO: Do I really need those two separate methods? They do the same - think for future cases
                if (TextUtils.isEmpty(searchQuery)){
                    mView.showCharacters(responseResults);
                } else {
                    mView.showSearchedCharacters(responseResults);
                }
            }

            @Override
            public void onUnauthorized() {
                if(!checkIfViewAttached()){
                    return;
                }
                mView.hideProgres();
                mView.showUnauthorizedError();
            }

            @Override
            public void onFailed(Throwable throwable) {
                if(!checkIfViewAttached()){
                    return;
                }
                mView.hideProgres();
                mView.showError(throwable.getMessage());
            }
        });
    }

    private boolean checkIfViewAttached(){
        if (!isViewAttached()){
            Log.w(TAG, "View is not attached");
            return false;
        } else {
            return true;
        }
    }
}