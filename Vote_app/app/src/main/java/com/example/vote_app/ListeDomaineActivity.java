package com.example.vote_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListeDomaineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domaineliste);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<Domaine> domaines = dbHelper.getAllDomaines();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewdomaines);
        DomainesAdapter domainesAdapter = new DomainesAdapter(domaines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(domainesAdapter);

        domainesAdapter.setOnDomaineClickListener(new DomainesAdapter.OnDomaineClickListener() {
            @Override
            public void onDomaineClick(int position) {
                // Handle the click event
                Domaine clickedDomaine = domaines.get(position);

                 String toastMessage = "Clicked on " + clickedDomaine.getNomDomaine();
                Toast.makeText(ListeDomaineActivity.this, toastMessage, Toast.LENGTH_SHORT).show();

               Intent intent = new Intent(ListeDomaineActivity.this, ListeCandidatsBydomaineActivity.class);
                intent.putExtra("DOMAIN_Nom", clickedDomaine.getNomDomaine());
                startActivity(intent);
            }

        });
    }
}
