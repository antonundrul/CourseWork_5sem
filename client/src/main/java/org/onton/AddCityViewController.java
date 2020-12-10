package org.onton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.onton.entity.City;
import org.onton.entity.User;

import java.io.IOException;

public class AddCityViewController {
    @FXML private TextField cityNameTextField;

    @FXML
    public void saveButtonPressed(ActionEvent event) throws IOException {

        Client.coos.writeObject("add_city");
        City city = new City();
        city.setName(cityNameTextField.getText());
        Client.coos.writeObject(city);
        switchToAddAirportView();
    }

    @FXML
    public void switchToAddAirportView() throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Добавление аэропорта");
        Client.setRoot("addAirportView");
        window.setScene(Client.scene);
        window.show();
    }


}
