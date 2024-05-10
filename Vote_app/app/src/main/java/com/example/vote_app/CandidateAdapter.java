package com.example.vote_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {
    private ArrayList<Condidat> candidates;

    public CandidateAdapter(ArrayList<Condidat> candidates) {
        this.candidates = candidates;
    }


    public interface OnVoteClickListener {
        void onVoteClick(int position);
    }

    private OnVoteClickListener onVoteClickListener;

    public void setOnVoteClickListener(OnVoteClickListener listener) {
        this.onVoteClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candidate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Condidat candidate = candidates.get(position);

        holder.textViewFirstName.setText("Prénom: " + candidate.getFirstName());
        holder.textViewLastName.setText("Nom: " + candidate.getLastName());
        holder.textViewAddress.setText("Adresse: " + candidate.getAddress());
        holder.textViewDescription.setText("Description: " + candidate.getdescription());
        holder.textViewnbvote.setText("NB Vote: " + candidate.getNbV());
        holder.textViewdomaine_nom.setText("nom domaine: " + candidate.getNom_domine());

        holder.buttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onVoteClickListener != null) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        onVoteClickListener.onVoteClick(adapterPosition);

                    }
                }
            }
        });

    }










    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewFirstName;
        public TextView textViewLastName;
        public TextView textViewAddress;
        public TextView textViewDescription;
        public TextView textViewnbvote;
        public Button buttonVote;
        public TextView textViewdomaine_nom;

        public ViewHolder(View view) {
            super(view);
            textViewFirstName = view.findViewById(R.id.textViewFirstName);
            textViewLastName = view.findViewById(R.id.textViewLastName);
            textViewAddress = view.findViewById(R.id.textViewAddress);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            textViewnbvote = view.findViewById(R.id.textViewNBvote);

            buttonVote = view.findViewById(R.id.buttonVote);
            textViewdomaine_nom = view.findViewById(R.id.textViewdomaine_nom);
        }
    }
}
