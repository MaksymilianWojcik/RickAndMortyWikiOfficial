package com.example.mwojcik.rickandmorty_wiki.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mwojcik.rickandmorty_wiki.R;
import com.example.mwojcik.rickandmorty_wiki.data.model.character.Character;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharactersListAdapter extends RecyclerView.Adapter<CharactersListAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Character> mCharactersList;
    private CharacterListInteractionListener mListInteractionListener;

    public CharactersListAdapter(Context mContext, List<Character> mCharactersList){
        this.mCharactersList = mCharactersList;
        this.mContext = mContext;
        mListInteractionListener = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Character character = mCharactersList.get(position);
        Picasso.with(mContext).load(character.getImageUrl())
                .fit()
                .centerCrop()
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.characterIV);
        holder.characterNameTV.setText(character.getName());
        holder.characterSpeciesTV.setText(character.getSpecies());
        //TODO: Should color be changed here? - check for performance
        if(character.getStatus().equalsIgnoreCase("Dead")) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.characterDeadBackground));
        }
        if(character.getStatus().equalsIgnoreCase("Alive")){
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.characterAliveBackground));
        }
        if(character.getStatus().equalsIgnoreCase("Unknown")){
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.characterUnknownBackground));
        }
    }

    @Override
    public int getItemCount() {
        return mCharactersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView characterIV;
        private TextView characterNameTV;
        private TextView characterSpeciesTV;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            characterIV = itemView.findViewById(R.id.row_image_view);
            characterNameTV = itemView.findViewById(R.id.row_name_tv);
            characterSpeciesTV = itemView.findViewById(R.id.row_type_tv);
            cardView = itemView.findViewById(R.id.row_card_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListInteractionListener != null){
                        mListInteractionListener.onCharacterClick(mCharactersList.get(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });

        }
    }

    public void addItems(List<Character> itemsList){
        mCharactersList.addAll(itemsList);
        notifyItemRangeInserted(getItemCount(), mCharactersList.size()-1);
    }

    public void removeAll(){
        int size = mCharactersList.size();
        if (size > 0){
            mCharactersList.clear();
        }
        notifyItemRangeRemoved(0, size);
    }

    public interface CharacterListInteractionListener{
        void onCharacterClick(Character character, int position);
    }

    public void setmListInteractionListener(CharacterListInteractionListener mListInteractionListener) {
        this.mListInteractionListener = mListInteractionListener;
    }
}
