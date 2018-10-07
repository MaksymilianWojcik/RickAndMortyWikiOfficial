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
        //super.onScrolled(recyclerView, dx, dy);
        int lastVisibleItemPosition = 0;

        /***
         * total item count is a total item count that is loaded into recyclerview
         */
        int totalItemCount = layoutManager.getItemCount();

        /***
         * That gives us the adapter position of the last visible view.
         * This position does not include adapter changes that were dispatched after the last layout pass.
         * Ex. if we see 5 completely visible items and 1 item halfly visible (cutted at bottom), than the
         * value here is 5. If we scroll to 7 visible items, than its seven. If we go back to 5 - 5 back again.
         */
        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                .findLastVisibleItemPosition();


        /***
         * The list was cleared
         */
        if (totalItemCount < previousTotalItemCount) {
            currentPage = STARTING_PAGE_INDEX;
            previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                mLoading = true;
            }
        }

        /**
         * If was loading - check if data count has changed.
         * If yes than update current page and total items count
         */
        if (mLoading && (totalItemCount > previousTotalItemCount + 1)) {
            mLoading = false;
            previousTotalItemCount = totalItemCount;
        }

        /**
         * It if is not loading, than check if should get new data for current page and update it.
         */
        if (!mLoading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage);
            mLoading = true;
        }
    }

    /***
     * Called when there are only 5 more visible items.
     * @param page number of the page (for which data should be loaded).
     */
    public abstract void onLoadMore(int page);
}
