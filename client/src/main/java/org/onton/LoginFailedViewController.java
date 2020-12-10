package org.onton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFailedViewController {


    @FXML
    public void switchToAuthorization(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Авторизация");
        Client.setRoot("authorization");
        window.setScene(Client.scene);
        window.show();
    }
}
