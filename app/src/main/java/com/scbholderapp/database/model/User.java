package com.scbholderapp.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "website")
    private String website;

    @ColumnInfo(name = "companyName")
    private String companyName;

    @ColumnInfo(name = "companyCatchPhrase")
    private String companyCatchPhrase;

    @ColumnInfo(name = "companyBs")
    private String companyBs;

    @ColumnInfo(name = "addressStreet")
    private String addressStreet;

    @ColumnInfo(name = "addressSuite")
    private String addressSuite;

    @ColumnInfo(name = "addressCity")
    private String addressCity;

    @ColumnInfo(name = "addressZipcode")
    private String addressZipcode;

    @ColumnInfo(name = "addressGeoLat")
    private String addressGeoLat;

    @ColumnInfo(name = "addressGeoLng")
    private String addressGeoLng;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyCatchPhrase() {
        return companyCatchPhrase;
    }

    public String getCompanyBs() {
        return companyBs;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getAddressSuite() {
        return addressSuite;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public String getAddressZipcode() {
        return addressZipcode;
    }

    public String getAddressGeoLat() {
        return addressGeoLat;
    }

    public String getAddressGeoLng() {
        return addressGeoLng;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyCatchPhrase(String companyCatchPhrase) {
        this.companyCatchPhrase = companyCatchPhrase;
    }

    public void setCompanyBs(String companyBs) {
        this.companyBs = companyBs;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public void setAddressSuite(String addressSuite) {
        this.addressSuite = addressSuite;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public void setAddressZipcode(String addressZipcode) {
        this.addressZipcode = addressZipcode;
    }

    public void setAddressGeoLat(String addressGeoLat) {
        this.addressGeoLat = addressGeoLat;
    }

    public void setAddressGeoLng(String addressGeoLng) {
        this.addressGeoLng = addressGeoLng;
    }
}
