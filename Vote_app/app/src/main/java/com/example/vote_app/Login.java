package com.example.vote_app;

public class Login {
    private String address;
    private String password;

    public Login() {
        // Constructeur par défaut
    }

    public Login(String address, String password) {
        this.address = address;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
