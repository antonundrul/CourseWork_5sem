package org.onton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import org.onton.entity.Airport;
import org.onton.entity.City;
import org.onton.entity.Ticket;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AirportDiagramController implements Initializable {
    @FXML private PieChart airportsChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

       getAirportsDiagramm();

    }


    public List<Airport> getAirportList() {
        List<Airport> cityList = null;
        try {
            Client.coos.writeObject("print_airports");
            cityList = (List<Airport>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityList;
    }

    public List<Ticket> getTicketList() {
        List<Ticket> list = null;
        try {
            Client.coos.writeObject("print_tickets");
            list = (List<Ticket>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @FXML
    public void switchToAdminMenu(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Меню администратора");
        Client.setRoot("adminMenu");
        window.setScene(Client.scene);
        window.show();
    }

    public  void getAirportsDiagramm(){
        ObservableList<PieChart.Data> airportsData = FXCollections.observableArrayList();
        List<Ticket> ticketList = getTicketList();
        List<Airport> airportList = getAirportList();

        for(Airport i : airportList) {
            double airportCounter = 0;
            for (Ticket j : ticketList) {
                if (i.getName().equals(j.getFlight().getAirportOfDeparture().getName())) {
                    airportCounter++;
                }
            }
            double value = (airportCounter / ticketList.size()) * 100;
            System.out.println(i.getName()+" / "+value);
            PieChart.Data sector = new PieChart.Data(i.getName(), value);
            airportsData.add(sector);
        }
        System.out.println(airportsData);
        airportsChart.setData(airportsData);
        airportsChart.setTitle("Популярность аэропортов среди клиентов");
    }
}
