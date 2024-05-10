package com.example.vote_app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCondidatActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextAddress;
    private EditText editTextDescription;
    private EditText editTextnom_domaine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcondidats);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextDescription = findViewById(R.id.description);
        editTextnom_domaine = findViewById(R.id.nom_damaine);

        Button buttonRegister = findViewById(R.id.buttonRegister);

        //...
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérez les valeurs des champs
                String firstName = editTextFirstName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String address = editTextAddress.getText().toString();
                String description = editTextDescription.getText().toString();
                String nom_domaine = editTextnom_domaine.getText().toString();

                // Créez une instance de Condidat
                Condidat condidat = new Condidat(firstName, lastName, address, description, nom_domaine,0);

                DatabaseHelper dbHelper = new DatabaseHelper(AddCondidatActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("username", condidat.getFirstName());
                values.put("lastName", condidat.getLastName());
                values.put("address", condidat.getAddress());
                values.put("description", condidat.getdescription());
                values.put("domaine_nom", condidat.getNom_domine());

                long newRowId = db.insert("Candidates", null, values);

                db.close();

                if (newRowId != -1) {
                    Toast.makeText(AddCondidatActivity.this, "Ajout réussi", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCondidatActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddCondidatActivity.this, "Ajout a échoué", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //...
    }
}

