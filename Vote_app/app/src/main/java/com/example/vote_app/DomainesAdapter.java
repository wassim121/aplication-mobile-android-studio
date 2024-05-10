package com.example.vote_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Update your DomainesAdapter class
public class DomainesAdapter extends RecyclerView.Adapter<DomainesAdapter.ViewHolder> {
    private ArrayList<Domaine> domaines;
    private OnDomaineClickListener listener;

    public DomainesAdapter(ArrayList<Domaine> domaines) {
        this.domaines = domaines;
    }

    @Override
    public DomainesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_domaine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DomainesAdapter.ViewHolder holder, int position) {
        Domaine domaine = domaines.get(position);

        holder.textViewNomDomaine.setText("Nom Domaine: " + domaine.getNomDomaine());
        holder.textViewDescription.setText("Description: " + domaine.getDescription());

        // Set a click listener for the entire item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    int clickedPosition = holder.getAdapterPosition();
                    if (clickedPosition != RecyclerView.NO_POSITION) {
                        listener.onDomaineClick(clickedPosition);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return domaines.size();
    }

    public void setOnDomaineClickListener(OnDomaineClickListener listener) {
        this.listener = listener;
    }

    public interface OnDomaineClickListener {
        void onDomaineClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNomDomaine;
        public TextView textViewDescription;

        public ViewHolder(View view) {
            super(view);
            textViewNomDomaine = view.findViewById(R.id.textViewNomDomaine);
            textViewDescription = view.findViewById(R.id.textViewDescription);
        }
    }
}
