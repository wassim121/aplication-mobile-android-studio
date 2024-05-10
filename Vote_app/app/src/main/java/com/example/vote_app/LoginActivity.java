package com.example.vote_app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextAddress;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonGoToSecondActivity = findViewById(R.id.buttonLogin2);
         buttonGoToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = editTextAddress.getText().toString();
                String password = editTextPassword.getText().toString();
                Login loginData = new Login(address, password);



                DatabaseHelper dbHelper = new DatabaseHelper(LoginActivity.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();



                // Spécifiez les colonnes que vous voulez récupérer
                String[] projection = {
                        "username", // Remplacez par le nom de la colonne contenant le nom d'utilisateur
                        "password"  // Remplacez par le nom de la colonne contenant le mot de passe
                };




                // Spécifiez la clause WHERE pour la recherche
                String selection = "username = ? AND password = ?";
                String[] selectionArgs = {loginData.getAddress(), loginData.getPassword()};



                // Effectuez la requête pour rechercher l'utilisateur
                Cursor cursor = db.query(
                        "Users",    // Nom de la table
                        projection, // Colonnes à renvoyer
                        selection,  // Clause WHERE
                        selectionArgs, // Arguments de la clause WHERE
                        null,
                        null,
                        null
                );




                if (cursor.moveToFirst()) {
                     Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(LoginActivity.this, ListeDomaineActivity.class);
                    startActivity(intent);
                } else {
                     Toast.makeText(LoginActivity.this, "Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }



                 cursor.close();
                db.close();
            }
        });

    }
}
