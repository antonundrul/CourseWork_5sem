package org.onton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import org.onton.entity.City;
import org.onton.entity.Ticket;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CitiesDiagramController implements Initializable {
    @FXML
    private PieChart citiesChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getCitiesDiagramm();

    }


    public void getCitiesDiagramm(){
        ObservableList<PieChart.Data> citiesData = FXCollections.observableArrayList();
        List<Ticket> ticketList = getTicketList();
        List<City> cityList = getCityList();

        for(City i : cityList) {
            double cityCounter = 0;
            for (Ticket j : ticketList) {
                if (i.getName().equals(j.getFlight().getAirportOfDestination().getCity().getName())) {
                    cityCounter++;
                }
            }
            double value = (cityCounter / ticketList.size()) * 100;
            System.out.println(i.getName()+" / "+value);
            PieChart.Data sector = new PieChart.Data(i.getName(), value);
            citiesData.add(sector);
        }
        System.out.println(citiesData);
        citiesChart.setData(citiesData);
        citiesChart.setTitle("Статистика посещения городов");
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

    public List<City> getCityList() {
        List<City> cityList = null;
        try {
            Client.coos.writeObject("print_cities");
            cityList = (List<City>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityList;
    }

    @FXML
    public void switchToAdminMenu(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Меню администратора");
        Client.setRoot("adminMenu");
        window.setScene(Client.scene);
        window.show();
    }
}
