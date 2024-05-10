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

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextAddress;
    private EditText editTextDOB;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextDOB = findViewById(R.id.editTextDOB);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérez les valeurs des champs
                String firstName = editTextFirstName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String address = editTextAddress.getText().toString();
                String dob = editTextDOB.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                // Créez une instance de RegisterData
                RegisterData registerData = new RegisterData(firstName, lastName, address, dob, password, confirmPassword);

                DatabaseHelper dbHelper = new DatabaseHelper(RegisterActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("username", registerData.getFirstName());
                values.put("password", registerData.getPassword());
                long newRowId = db.insert("Users", null, values);

                // Fermez la base de données
                db.close();

                if (newRowId != -1) {
                    // L'inscription a réussi, affichez un Toast
                    Toast.makeText(RegisterActivity.this, "Inscription réussie", Toast.LENGTH_SHORT).show();

                    // Redirigez l'utilisateur vers l'activité de connexion
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    // L'inscription a échoué, affichez un message d'erreur
                    Toast.makeText(RegisterActivity.this, "L'inscription a échoué", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
