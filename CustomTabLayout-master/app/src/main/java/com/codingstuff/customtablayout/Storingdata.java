package com.codingstuff.customtablayout;

public class Storingdata {
    String name,Email,Password,Number;

    public Storingdata(String name, String email, String number, String password) {
        this.name = name;
        Email = email;
        Password = password;
        Number = number;
    }

    public Storingdata() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
