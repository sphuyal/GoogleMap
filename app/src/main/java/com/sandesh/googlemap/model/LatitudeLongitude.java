package com.sandesh.googlemap.model;

public class LatitudeLongitude {

    private double lat;
    private double lon;
    private String hamro;

    public LatitudeLongitude(double lat,double lon,String hamro){
        this.lat= lat;
        this.lon= lon;
        this.hamro = hamro;
    }
    public String getHamro(){
        return hamro;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setHamro(String hamro) {
        this.hamro = hamro;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
