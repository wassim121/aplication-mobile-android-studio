package com.example.vote_app;

import java.util.ArrayList;

public class Domaine {

    private String NomDomaine;
    private String description;

    // Constructor
    public Domaine(String nomDomaine, String description) {
        NomDomaine = nomDomaine;
        this.description = description;
     }

    // Getter for NomDomaine
    public String getNomDomaine() {
        return NomDomaine;
    }

    // Setter for NomDomaine
    public void setNomDomaine(String nomDomaine) {
        NomDomaine = nomDomaine;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for liste_condidats

}
