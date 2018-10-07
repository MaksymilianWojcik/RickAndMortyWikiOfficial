package com.example.mwojcik.rickandmorty_wiki.ui.character_details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mwojcik.rickandmorty_wiki.R;
import com.example.mwojcik.rickandmorty_wiki.data.DataManager;
import com.example.mwojcik.rickandmorty_wiki.data.model.Episode;
import com.example.mwojcik.rickandmorty_wiki.data.model.Location;
import com.example.mwojcik.rickandmorty_wiki.data.model.character.Character;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//TODO: Clean up + Docs - also in presenter
public class CharacterDetailsActivity extends AppCompatActivity implements CharacterContract.CharacterView{

    private CharacterPresenter mCharacterPresenter;

    private TextView nameTV;
    private TextView speciesTV;
    private TextView statusTV;
    private TextView genderTV;
    private TextView originTV;
    private TextView locationTV;
    private TextView createTV;
    private ImageView avatarIV;
    private ListView episodesLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(R.string.characters_details_title);

        Character character = (Character) getIntent().getParcelableExtra("CharacterClicked");

        nameTV = findViewById(R.id.character_details_name_tv);
        speciesTV = findViewById(R.id.character_details_species_tv);
        statusTV = findViewById(R.id.character_details_status_tv);
        genderTV = findViewById(R.id.character_details_gender_tv);
        originTV = findViewById(R.id.character_details_origin_tv);
        locationTV = findViewById(R.id.character_details_location_tv);
        createTV = findViewById(R.id.character_details_id_tv);
        episodesLV = findViewById(R.id.character_details_episodes_lv);
        avatarIV = findViewById(R.id.character_details_avatar_iv);

        mCharacterPresenter = new CharacterPresenter(DataManager.getInstance(), character);
        mCharacterPresenter.attachView(this);
        mCharacterPresenter.onCharacterRequested(character.getId());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showCharacter(Character character) {
        nameTV.setText(character.getName());
        speciesTV.setText(character.getSpecies());
        statusTV.setText(character.getStatus());
        genderTV.setText(character.getGender());
        originTV.setText(character.getOrigin().getName());
        locationTV.setText(character.getLocation().getName());
        createTV.setText("Id: " + character.getId() + " - created: " + character.getCreatedDate());
        Picasso.with(this).load(character.getImageUrl())
                .fit()
                .centerCrop()
                .into(avatarIV);
        mCharacterPresenter.onCharacterEpisodesListRequested();
    }

    @Override
    public void showEpisodesList(List<Episode> episodeList) {
        //TODO: CUSTOM ARRAY ADAPTER - and white text color
        //TODO: Activity with details about episode after click
        List<String> list = new ArrayList<>();
        for (Episode e : episodeList){
            list.add(e.getName() + ", " + e.getCode());
        }
        if (list.isEmpty()){
            list.add("unknown");
        }
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        episodesLV.setAdapter(itemsAdapter);
    }

    @Override
    public void showLocationDetails(Location location) {
        //TODO: More details about location: new Activity or dialog
    }

    @Override
    public void showProgress() {
        //TODO: Progress bars or not
    }

    @Override
    public void hideProgres() {

    }

    @Override
    public void showUnauthorizedError() {
        Toast.makeText(this, "Unauthorized error", Toast.LENGTH_SHORT);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT);
        //TODO: Handle errors
    }

}
