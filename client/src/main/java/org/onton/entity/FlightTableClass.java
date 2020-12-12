package org.onton.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightTableClass {
    private int id;
    private String airline;
    private String cityOfDeparture;
    private String airportOfDeparture;
    private String cityOfDestination;
    private String airportOfDestination;
    private LocalDate dateOfDeparture;
    private LocalTime timeOfDeparture;
    private int cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirportOfDeparture() {
        return airportOfDeparture;
    }

    public void setAirportOfDeparture(String airportOfDeparture) {
        this.airportOfDeparture = airportOfDeparture;
    }

    public String getAirportOfDestination() {
        return airportOfDestination;
    }

    public void setAirportOfDestination(String airportOfDestination) {
        this.airportOfDestination = airportOfDestination;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public String getCityOfDeparture() {
        return cityOfDeparture;
    }

    public void setCityOfDeparture(String cityOfDeparture) {
        this.cityOfDeparture = cityOfDeparture;
    }

    public String getCityOfDestination() {
        return cityOfDestination;
    }

    public void setCityOfDestination(String cityOfDestination) {
        this.cityOfDestination = cityOfDestination;
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

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
