package com.example.vote_app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        //Condidat newCondidat = new Condidat("John", "Doe", "123 Main St", "Description du candidat");
        //  long newRowId = dbHelper.insertCandidate(newCondidat);

        ArrayList<Condidat> candidats = dbHelper.getAllCandidates();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCandidates);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CandidateAdapter adapter = new CandidateAdapter(candidats);
        recyclerView.setAdapter(adapter);







    }
}