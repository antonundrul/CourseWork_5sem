package org.onton.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketTableClass {

    private int idTicket;
    private int idFlight;
    private String airline;
    private String cityOfDeparture;
    private String cityOfDestination;
    private LocalDate dateOfFlight;
    private LocalTime timeOfFlight;
    private String userLastname;
    private LocalDate dateOfSale;

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
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

    public LocalDate getDateOfFlight() {
        return dateOfFlight;
    }

    public void setDateOfFlight(LocalDate dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public LocalTime getTimeOfFlight() {
        return timeOfFlight;
    }

    public void setTimeOfFlight(LocalTime timeOfFlight) {
        this.timeOfFlight = timeOfFlight;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
