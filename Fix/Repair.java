package com.serifyasargmail.fixnfix;

import java.io.Serializable;

public class Repair implements Serializable {
    private int id;
    private String name;
    private String email;
    private String company;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String postcode;
    private String message;
    private String device;
    private String repair;
    private String cost;
    private String type;

    public Repair(int id, String name, String email, String company, String address, String city, String country, String phone, String postcode, String message, String device, String repair, String cost, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.company = company;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.postcode = postcode;
        this.message = message;
        this.device = device;
        this.repair = repair;
        this.cost = cost;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getMessage() {
        return message;
    }

    public String getDevice() {
        return device;
    }

    public String getRepair() {
        return repair;
    }

    public String getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setType(String type) {
        this.type = type;
    }
}
