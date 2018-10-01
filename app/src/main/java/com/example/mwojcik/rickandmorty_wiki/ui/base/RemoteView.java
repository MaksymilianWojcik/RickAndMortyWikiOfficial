package com.example.mwojcik.rickandmorty_wiki.ui.base;

public interface RemoteView {
    void showProgress();
    void hideProgres();
    void showUnauthorizedError();
    void showEmpty();
    void showError(String errorMessage);
    void showMessageLayout(boolean show);
}
