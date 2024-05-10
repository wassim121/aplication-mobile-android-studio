package com.example.vote_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListeCandidatsBydomaineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        try {
            Intent intent = getIntent();
            String DOMAIN_Nom = null;

            if (intent != null && intent.hasExtra("DOMAIN_Nom")) {
                DOMAIN_Nom = intent.getStringExtra("DOMAIN_Nom");

                if (DOMAIN_Nom != null) {
                    ArrayList<Condidat> candidats = dbHelper.getCandidatesByDomain(DOMAIN_Nom);

                    RecyclerView recyclerView = findViewById(R.id.recyclerViewCandidates);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));

                    CandidateAdapter adapter = new CandidateAdapter(candidats);
                    adapter.setOnVoteClickListener(new CandidateAdapter.OnVoteClickListener() {
                        @Override
                        public void onVoteClick(int position) {
                            // Handle the vote button click, e.g., increment the number of votes
                            Condidat candidate = candidats.get(position);

                            dbHelper.updateCandidateVotes(candidate.getFirstName(), candidate.getLastName(), candidate.getNbV());

                            // Notify the adapter that data has changed
                            adapter.notifyItemChanged(position);

                            // Show a Toast with a thank you message
                            Toast.makeText(ListeCandidatsBydomaineActivity.this, "Merci pour votre participation!", Toast.LENGTH_SHORT).show();

                            // Redirect to MainActivity
                            Intent intent = new Intent(ListeCandidatsBydomaineActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });

                    recyclerView.setAdapter(adapter);
                } else {
                    showToast("Domain name is null or not provided.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showToast("Error retrieving candidates: " + e.getMessage());
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
