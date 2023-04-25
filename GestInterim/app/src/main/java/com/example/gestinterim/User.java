package com.example.gestinterim;

public class User {

    private String firstname;
    private String lastname;
    private String nationality;
    private String city;
    private String tel;
    private String mail;
    private String password;

    public User(){};

    public User(String firstname, String lastname, String nationality, String city, String tel, String mail, String password){
      this.firstname = firstname;
      this.lastname = lastname;
      this.nationality = nationality;
      this.city = city;
      this.tel = tel;
      this.mail = mail;
      this.password = password;
    };
}
