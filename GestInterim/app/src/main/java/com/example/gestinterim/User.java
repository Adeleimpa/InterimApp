package com.example.gestinterim;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String firstname;
    public String lastname;
    public String nationality;
    public String city;
    public String tel;
    public String mail;
    public String password;
    public String type;

    public User(){}

    public User(String firstname, String lastname, String nationality, String city, String tel, String mail, String password, String type){
      this.firstname = firstname;
      this.lastname = lastname;
      this.nationality = nationality;
      this.city = city;
      this.tel = tel;
      this.mail = mail;
      this.password = password;
      this.type = type;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCity() {
        return city;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
