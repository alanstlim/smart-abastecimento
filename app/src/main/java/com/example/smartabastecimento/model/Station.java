package com.example.smartabastecimento.model;

import java.io.Serializable;

public class Station implements Serializable {

    private String nameStation;
    private String priceEthanol;
    private String priceGasoline;
    private String address;
    private String cep;
    private String imageFranchise;
    private int number;
    private double latitude, longitude;


    public Station () {

    }

    public Station(String nameStation, String priceEthanol, String priceGasoline, String address, String cep, int number, String imageFranchise) {
        this.nameStation = nameStation;
        this.priceEthanol = priceEthanol;
        this.priceGasoline = priceGasoline;
        this.address = address;
        this.cep = cep;
        this.number = number;
        this.imageFranchise = imageFranchise;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public String getPriceEthanol() {
        return priceEthanol;
    }

    public void setPriceEthanol(String priceEthanol) {
        this.priceEthanol = priceEthanol;
    }

    public String getPriceGasoline() {
        return priceGasoline;
    }

    public void setPriceGasoline(String priceGasoline) {
        this.priceGasoline = priceGasoline;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getImageFranchise() {
        return imageFranchise;
    }

    public void setImageFranchise(String imageFranchise) {
        this.imageFranchise = imageFranchise;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
