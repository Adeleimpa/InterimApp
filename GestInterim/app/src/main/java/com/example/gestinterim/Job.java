package com.example.gestinterim;

public class Job {

    public String title;
    public String description;
    public String city;
    public String company;
    public String url;

    public Job(){}

    public Job(String title, String description, String city, String company, String url){
        this.title = title;
        this.description = description;
        this.city = city;
        this.company = company;
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getCompany() {
        return company;
    }

    public String getUrl() {
        return url;
    }
}
