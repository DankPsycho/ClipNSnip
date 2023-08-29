package com.example.myapplication;

public class Reservation {
    private int id;
    private String fullName;
    private String email;
    private String mobile;
    private String date; // New field
    private String time; // New field
    private String services; // New field
    private Double total; // New field

    public Reservation(int id, String fullName, String email, String mobile, String date, String time, String services, Double total) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.date = date;
        this.time = time;
        this.services = services;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getServices() {
        return services;
    }

    public Double getTotal() {
        return total;
    }
}