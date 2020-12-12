package org.onton.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class Flight implements Serializable {

    private int id;
    private Airport airportOfDeparture;
    private Airport airportOfDestination;
    private LocalDate dateOfDeparture;
    private LocalTime timeOfDeparture;
    private int cost;
    private Airline airline;

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airport getAirportOfDeparture() {
        return airportOfDeparture;
    }

    public void setAirportOfDeparture(Airport airportOfDeparture) {
        this.airportOfDeparture = airportOfDeparture;
    }

    public Airport getAirportOfDestination() {
        return airportOfDestination;
    }

    public void setAirportOfDestination(Airport airportOfDestination) {
        this.airportOfDestination = airportOfDestination;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(LocalTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}

