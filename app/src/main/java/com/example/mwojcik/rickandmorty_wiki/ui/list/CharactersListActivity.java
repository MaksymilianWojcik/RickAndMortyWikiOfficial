package com.example.mwojcik.rickandmorty_wiki.ui.list;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mwojcik.rickandmorty_wiki.R;
import com.example.mwojcik.rickandmorty_wiki.data.DataManager;
import com.example.mwojcik.rickandmorty_wiki.data.model.character.Character;
import com.example.mwojcik.rickandmorty_wiki.ui.character_details.CharacterDetailsActivity;
import com.example.mwojcik.rickandmorty_wiki.utils.QuotesUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class CharactersListActivity extends AppCompatActivity implements CharacterListContract.ListView, CharactersListAdapter.CharacterListInteractionListener, SearchView.OnQueryTextListener {

    private static final String TAG = "CharactersListActivity";

    private CharacterListPresenter listPresenter;
    private RecyclerView recyclerView;
    private CharactersListAdapter adapter;
    private List<Character> characterList;
    private String searchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_list);
        recyclerView = findViewById(R.id.characters_list_recycler_view);

        //ca-app-pub-3940256099942544/6300978111 - test
        //ca-app-pub-1028371747184544/4713922518 - moje

        //TODO: Activity extends Application - put it there
        MobileAds.initialize(this, "ca-app-pub-1028371747184544~9384204386");
        AdView adView;
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //TODO: ActionBar -> Toolbar
        getSupportActionBar().setTitle(R.string.characters_list_title);

        //TODO: Better progress dialog

        //TODO: put in separate method - like initViews()
        characterList = new ArrayList<>();
        adapter = new CharactersListAdapter(this, characterList);
        adapter.setmListInteractionListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(setupScrollListener(recyclerView.getLayoutManager()));

        listPresenter = new CharacterListPresenter(DataManager.getInstance());
        listPresenter.attachView(this);
        listPresenter.onInitialListRequested();

        QuotesUtils.getQuote();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_characters_list, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        //TODO: strings.xml values + dimensions
        searchView.setQueryHint("search");
        //TODO: Search view is not closing properly after searching with query - have to be closed twice
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showCharacters(List<Character> characterList) {
        adapter.addItems(characterList);
    }

    @Override
    public void showSearchedCharacters(List<Character> characterList) {
        adapter.addItems(characterList);
    }

    @Override
    public void showProgress() {
        //TODO: Better progress dialog
        //dialog.show();
    }

    @Override
    public void hideProgres() {
        //TODO: Better progress dialog
        //dialog.hide();
    }

    @Override
    public void showUnauthorizedError() {
        showMessageLayout(true);
    }

    @Override
    public void showEmpty() {
        showMessageLayout(true);
    }

    @Override
    public void showError(String errorMessage) {
        //TODO: check "pages" for current response and call only for new page number if <= pages (currently causes 404)
        //TODO: check for errors logic
        if (!errorMessage.equalsIgnoreCase("Default 404 Not Found")){
            showMessageLayout(true);
        }
    }

    @Override
    public void showMessageLayout(boolean show) {
        //TODO: prepare custom error layout (TextView etc.)
//        if (show) {
//            Toast.makeText(this, "showMessageLayout()", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void onDestroy() {
        recyclerView.setAdapter(null);
        listPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onCharacterClick(Character character, int position) {
        //TODO: Open new Activity via presenter
        //TODO: Add method to contract and handle it (open new activity) - better pass object or call backend in activity?
//        Intent intent = new Intent(CharactersListActivity.this, CharacterDetailsActivity.class);
//        intent.putExtra("characterId", character.getId());
        //TODO: Static key String for that intent character object
        Intent intent = new Intent(CharactersListActivity.this, CharacterDetailsActivity.class);
        intent.putExtra("CharacterClicked", character);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchQuery = newText;
        adapter.removeAll();
        if(!TextUtils.isEmpty(searchQuery)){
            listPresenter.onCharacterSearch(searchQuery);
            return true;
        } else {
            listPresenter.onInitialListRequested();
        }
        return false;
    }

    /***
     * Setts up a scroll listener for given recycler view by it's layout manager.
     */
    private EndlessRecyclerViewOnScrollListener setupScrollListener(RecyclerView.LayoutManager layoutManager){
        return new EndlessRecyclerViewOnScrollListener((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page) {
                listPresenter.onListEndReached(String.valueOf(page), searchQuery);
            }
        };
    }
}