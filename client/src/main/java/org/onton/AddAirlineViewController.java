package org.onton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.onton.entity.Airline;
import org.onton.entity.City;

import java.io.IOException;

public class AddAirlineViewController {
    @FXML
    private TextField airlineNameTextField;

    @FXML
    public void saveButtonPressed(ActionEvent event) throws IOException {

        Client.coos.writeObject("add_airline");
        Airline airline= new Airline();
        airline.setName(airlineNameTextField.getText());
        Client.coos.writeObject(airline);
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

}
