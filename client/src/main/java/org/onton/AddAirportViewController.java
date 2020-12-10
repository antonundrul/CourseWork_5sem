package org.onton;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.onton.entity.Airport;
import org.onton.entity.City;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddAirportViewController implements Initializable {
    @FXML private TextField airportNameTextField;
    @FXML private ChoiceBox<City> cityChoiceBox;

    @FXML
    public void switchToAddCityView(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Добавление города");
        Client.setRoot("addCityView");
        window.setScene(Client.scene);
        window.show();
    }

    @FXML
    public void saveButtonPressed(ActionEvent event) throws IOException {

        Client.coos.writeObject("add_airport");
        Airport airport = new Airport();
        airport.setName(airportNameTextField.getText());
        airport.setCity(cityChoiceBox.getValue());
        Client.coos.writeObject(airport);
        switchToAdminMenu();
    }

    @FXML
    public void switchToAdminMenu() throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Меню администратора");
        Client.setRoot("adminMenu");
        window.setScene(Client.scene);
        window.show();
    }

    public ObservableList<City> getCityObservableList() {
        List<City> list = null;
        try {
            Client.coos.writeObject("print_cities");
            list = (List<City>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return FXCollections.observableList(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityChoiceBox.setItems(getCityObservableList());
    }
}
