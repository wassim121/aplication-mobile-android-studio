package com.example.vote_app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DomaineActivity extends AppCompatActivity {

    private EditText editTextNomDomaine;
    private EditText editTextDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domaine);

        editTextNomDomaine = findViewById(R.id.editTextNomDomaine);
        editTextDesc = findViewById(R.id.editTextDesc);
         Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editTextNomDomaine.getText().toString();
                String description = editTextDesc.getText().toString();



                Domaine domaine = new Domaine(nom, description);

                DatabaseHelper dbHelper = new DatabaseHelper(DomaineActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("NomDomaine", domaine.getNomDomaine());
                values.put("description", domaine.getDescription());
                long newRowId = db.insert("Domaines", null, values);

                db.close();


                if (newRowId != -1) {
                     Toast.makeText(DomaineActivity.this, "Ajout réussi", Toast.LENGTH_SHORT).show();


                } else {
                     Toast.makeText(DomaineActivity.this, "L'ajout a échoué", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
