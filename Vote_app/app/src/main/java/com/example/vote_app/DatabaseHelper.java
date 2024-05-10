package com.example.vote_app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase6.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the "Domaines" table
        String createDomainesTable = "CREATE TABLE Domaines (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NomDomaine TEXT," +
                "description TEXT" +
                 ")";

        db.execSQL(createDomainesTable);
        // Créez la table Users
        String createUsersTable = "CREATE TABLE Users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "lastName TEXT," +
                "address TEXT," +
                "dob TEXT," +
                "password TEXT," +
                "confirmPassword TEXT" +
                ")";
        db.execSQL(createUsersTable);

        // Créez la table Candidates
        String createCandidatesTable = "CREATE TABLE Candidates (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "lastName TEXT," +
                "address TEXT," +
                "description TEXT," +
                "nbVote INTEGER ,"+
                "domaine_nom TEXT" +
                ")";
        db.execSQL(createCandidatesTable);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Mettez à jour vos tables en cas de besoin
    }

    // Méthode pour récupérer les candidats par nom de domaine
    public ArrayList<Condidat> getCandidatesByDomain(String nomDomaine) {
        ArrayList<Condidat> candidatsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "username",
                "lastName",
                "address",
                "description",
                "domaine_nom",
                "nbVote"
        };

        // Condition WHERE pour filtrer par nom de domaine
        String selection = "domaine_nom = ?";
        String[] selectionArgs = {nomDomaine};

        Cursor cursor = db.query("Candidates", projection, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range")   int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range")   String firstName = cursor.getString(cursor.getColumnIndex("username"));
                @SuppressLint("Range")   String lastName = cursor.getString(cursor.getColumnIndex("lastName"));
                @SuppressLint("Range")   String address = cursor.getString(cursor.getColumnIndex("address"));
                @SuppressLint("Range")   String description = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range")   String domaine_nom = cursor.getString(cursor.getColumnIndex("domaine_nom"));
                @SuppressLint("Range")   int n = cursor.getInt(cursor.getColumnIndex("nbVote"));

                Condidat condidat = new Condidat(firstName, lastName, address, description, domaine_nom,n);
                candidatsList.add(condidat);
            }

            cursor.close();
        }

        db.close();

        return candidatsList;
    }
    public ArrayList<Condidat> getAllCandidates() {
        ArrayList<Condidat> candidatsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "username",
                "lastName",
                "address",
                "description",
                "domaine_nom" ,
                "nbVote"
        };

        Cursor cursor = db.query("Candidates", projection, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex("username"));
                @SuppressLint("Range")  String lastName = cursor.getString(cursor.getColumnIndex("lastName"));
                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range")  String domaine_nom = cursor.getString(cursor.getColumnIndex("domaine_nom"));
                @SuppressLint("Range")  int nbVote = cursor.getInt(cursor.getColumnIndex("nbVote"));

                Condidat condidat = new Condidat(firstName, lastName, address, description, domaine_nom,nbVote);
                candidatsList.add(condidat);
            }

            cursor.close();
        }

        db.close();

        return candidatsList;
    }

    public void updateCandidateVotes(String firstName, String lastName, int n) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Fetch the existing candidate details
        String[] projection = {
                "id",
                "address",
                "description",
                "domaine_nom"
        };
        String selection = "username = ? AND lastName = ?";
        String[] selectionArgs = {firstName, lastName};

        Cursor cursor = db.query("Candidates", projection, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Get existing candidate details
            @SuppressLint("Range") int candidateId = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range")  String address = cursor.getString(cursor.getColumnIndex("address"));
            @SuppressLint("Range")  String description = cursor.getString(cursor.getColumnIndex("description"));
            @SuppressLint("Range")  String domaine_nom = cursor.getString(cursor.getColumnIndex("domaine_nom"));

            cursor.close();

            // Increment the vote count
            int newVoteCount = n + 1;

            // Delete the existing candidate
            String deleteWhereClause = "id = ?";
            String[] deleteWhereArgs = {String.valueOf(candidateId)};
            db.delete("Candidates", deleteWhereClause, deleteWhereArgs);

            // Insert the candidate with the updated vote count
            ContentValues values = new ContentValues();
            values.put("username", firstName);
            values.put("lastName", lastName);
            values.put("address", address);
            values.put("description", description);
            values.put("nbVote", newVoteCount);
            values.put("domaine_nom", domaine_nom);

            db.insert("Candidates", null, values);
        }

        db.close();
    }







    public long insertCandidate(Condidat condidat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", condidat.getFirstName());
        values.put("lastName", condidat.getLastName());
        values.put("address", condidat.getAddress());
        values.put("nbVote", condidat.getNbV());

        values.put("description", condidat.getdescription());

        // Insérez le candidat dans la table "Candidates"
        long newRowId = db.insert("Candidates", null, values);

        // Fermez la base de données
        db.close();

        return newRowId;
    }


    public long createDomaine(Domaine domaine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NomDomaine", domaine.getNomDomaine());



        values.put("description", domaine.getDescription());

        long newRowId = db.insert("Domaines", null, values);

        return newRowId;
    }

    // Méthode pour sérialiser une liste d'entiers en un tableau d'octets
    private byte[] serialize(ArrayList<Integer> list) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(list);
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }



    public ArrayList<Domaine> getAllDomaines() {
        ArrayList<Domaine> domainesList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "NomDomaine",
                "description",
         };

        Cursor cursor = db.query("Domaines", projection, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String nomDomaine = cursor.getString(cursor.getColumnIndex("NomDomaine"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));



                Domaine domaine = new Domaine(nomDomaine, description);
                domainesList.add(domaine);
            }

            cursor.close();
        }

        db.close();

        return domainesList;
    }

    // Méthode pour désérialiser un tableau d'octets en une liste d'entiers
    private Object deserialize(byte[] byteData) throws IOException, ClassNotFoundException {
        if (byteData == null) {
            return null; // ou lancez une exception appropriée selon votre logique
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteData);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object result = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return result;
    }


}