package com.example.vote_app;

import java.util.ArrayList;

public class Condidat {

    private String firstName;
    private String lastName;
    private String address;

    private String description;
    private int NbV;

    private String nom_domine;
    public Condidat() {
        // Constructeur par défaut
    }


    public Condidat(String firstName, String lastName, String address, String description,String nom_domine ,int n) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.description = description;
        this.NbV=n;
        this.nom_domine=nom_domine;

    }
    public String getNom_domine() {
        return nom_domine;
    }

    public void setNom_domine(String nom_domine) {
        this.nom_domine = nom_domine;
    }
    public void incrementNbV() {
        this.NbV++;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getdescription() {
        return description;
    }

    public void setNbV(int NbV) {
        this.NbV = NbV;
    }

    public int getNbV() {
        return NbV;
    }
}
