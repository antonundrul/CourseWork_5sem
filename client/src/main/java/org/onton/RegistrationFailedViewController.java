package org.onton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationFailedViewController {

    @FXML
    public void switchToRegistration(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Регистрация");
        Client.setRoot("registration");
        window.setScene(Client.scene);
        window.show();
    }

}
