package com.example.mwojcik.rickandmorty_wiki.ui.list;

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

import java.util.ArrayList;
import java.util.List;

public class CharactersListActivity extends AppCompatActivity implements CharacterListContract.ListView, CharactersListAdapter.CharacterListInteractionListener, SearchView.OnQueryTextListener {

    private static final String TAG = "CharactersListActivity";

    private CharacterListPresenter listPresenter;
    private RecyclerView recyclerView;
    private CharactersListAdapter adapter;
    private List<Character> characterList;
    //private AlertDialog dialog;

    private String searchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_list);
        recyclerView = findViewById(R.id.characters_list_recycler_view);

        //TODO: ActionBar -> Toolbar
        getSupportActionBar().setTitle("Characters");

        //TODO: Better progress dialog
//        dialog = new AlertDialog.Builder(CharactersListActivity.this)
//                .setCancelable(false)
//                .setView(R.layout.progress_layout)
//                .create();

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
        Log.d(TAG, "showCharacters() - with: " + characterList);
        adapter.addItems(characterList);
    }

    @Override
    public void showSearchedCharacters(List<Character> characterList) {
        Log.d(TAG, "showSearchedCharacters() - with: " + characterList);
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
        Log.e(TAG, "showError(): " + errorMessage);
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
        Log.d(TAG, "character clicked: " + character + ", at position: " + position );
//        Intent intent = new Intent(CharactersListActivity.this, CharacterDetailsActivity.class);
//        intent.putExtra("characterId", character.getId());
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

    private EndlessRecyclerViewOnScrollListener setupScrollListener(RecyclerView.LayoutManager layoutManager){
        return new EndlessRecyclerViewOnScrollListener((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page) {
                Log.d(TAG, "Getting more data for page: " + page);
                listPresenter.onListEndReached(String.valueOf(page), searchQuery);
            }
        };
    }
}