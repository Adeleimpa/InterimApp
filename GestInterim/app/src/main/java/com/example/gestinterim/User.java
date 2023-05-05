package com.example.gestinterim;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    // common attributes to all users
    public String mail;
    public String password;
    public String type;
    public String city;
    public String tel;

    // candidate
    public String firstname;
    public String lastname;
    public String nationality;

    // agency
    public String agencyName;

    // employer
    public String enterpriseName;

    // employer and agency
    public String service;
    public String national_nr;


    public User(){}

    public User(String city, String tel, String mail, String password, String type){
      this.city = city;
      this.tel = tel;
      this.mail = mail;
      this.password = password;
      this.type = type;
    }

    public void setExtraCandidateData(String firstname, String lastname, String nationality){
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationality = nationality;
    }

    public void setExtraAgencyData(String agencyName, String service, String national_nr){
        this.agencyName = agencyName;
        this.service = service;
        this.national_nr = national_nr;
    }

    public void setExtraEmployerData(String enterpriseName, String service, String national_nr){
        this.enterpriseName = enterpriseName;
        this.service = service;
        this.national_nr = national_nr;
    }

    public String getType() {
        return type;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getService() {
        return service;
    }

    public String getNational_nr() {
        return national_nr;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setNational_nr(String national_nr) {
        this.national_nr = national_nr;
    }
}
