package org.onton.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "flight")
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFlight")
    private int id;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "airport_of_departure_id")
    private Airport airportOfDeparture;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "airport_of_destination_id")
    private Airport airportOfDestination;

    @NotNull
    @Column(name = "date_of_departure")
    private LocalDate dateOfDeparture;

    @NotNull
    @Column(name = "time_of_departure")
    private LocalTime timeOfDeparture;

    @NotNull
    @Column(name = "cost")
    private int cost;

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

    public void setDateOfDeparture(LocalDate timeOfDeparture) {
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
}
