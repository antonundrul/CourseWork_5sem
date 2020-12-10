package org.onton.entity;

import java.io.Serializable;


public class Airport implements Serializable {


    private int id;
    private String name;
    private City city;

    public Airport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return city + ", " + name;
    }
}