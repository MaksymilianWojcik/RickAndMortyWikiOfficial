package com.example.mwojcik.rickandmorty_wiki.ui.base;

public abstract class BasePresenter<V> {

    protected  V mView;

    public final void attachView(V mView){
        this.mView = mView;
    }

    public final void detachView(){
        this.mView = null;
    }

    protected final boolean isViewAttached(){
        return mView != null;
    }
}
