package com.example.mwojcik.rickandmorty_wiki.ui.list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {

    private static final int STARTING_PAGE_INDEX = 1;

    private static int visibleThreshold = 5;
    private int currentPage = 1;
    private int previousTotalItemCount = 0;
    private boolean mLoading = true;
    private RecyclerView.LayoutManager layoutManager;

    public EndlessRecyclerViewOnScrollListener(LinearLayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = layoutManager.getItemCount();


        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                .findLastVisibleItemPosition();


        if (totalItemCount < previousTotalItemCount) { // List was cleared
            currentPage = STARTING_PAGE_INDEX;
            previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                mLoading = true;
            }
        }

        /**
         * If still loading - check for dataset change. If yes than update current page and total items count
         */
        if (mLoading && (totalItemCount > previousTotalItemCount + 1)) {
            mLoading = false;
            previousTotalItemCount = totalItemCount;
        }

        /**
         * If not loading - check if should get more data
         */
        if (!mLoading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage);
            mLoading = true;
        }
    }

    public abstract void onLoadMore(int page);
}
