package com.example.vote_app;

import java.util.ArrayList;

public class RegisterData {
    private String firstName;
    private String lastName;
    private String address;
    private String dob;
    private String password;
    private String confirmPassword;
    private ArrayList<Domaine> ListeDomaineVotee;

    public RegisterData() {
        // Constructeur par défaut
    }

    public RegisterData(String firstName, String lastName, String address, String dob, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dob = dob;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.ListeDomaineVotee=new ArrayList<Domaine>();
    }
    public ArrayList<Domaine> getListeDomaineVotee() {
        return ListeDomaineVotee;
    }

    public void addDomaineVotee(Domaine  DomaineVotee) {
        ListeDomaineVotee.add(DomaineVotee);
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
