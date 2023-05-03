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

    public User(){}

    public User(String firstname, String lastname, String nationality, String city, String tel, String mail, String password){
      this.firstname = firstname;
      this.lastname = lastname;
      this.nationality = nationality;
      this.city = city;
      this.tel = tel;
      this.mail = mail;
      this.password = password;
    }
}
