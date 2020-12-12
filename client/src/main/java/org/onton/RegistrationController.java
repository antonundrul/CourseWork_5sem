package org.onton;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.onton.entity.Flight;
import org.onton.entity.User;

public class RegistrationController {

    @FXML private TextField mailField;
    @FXML private TextField passwordField;
    @FXML private TextField lastNameField;
    @FXML private TextField firstNameField;
    @FXML private TextField middleNameField;
    @FXML private TextField passportSeriesField;
    @FXML private TextField passportNumberField;

    @FXML private Button returnBackButton;
    @FXML private Button buyButton;

    @FXML
    public void switchToAuthorization(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Авторизация");
        Client.setRoot("authorization");
        window.setScene(Client.scene);
        window.show();
    }

    @FXML
    public void registrationButtonPressed(ActionEvent event) throws IOException {

        Client.coos.writeObject("add_user");


        User user = new User();

        user.setMail(mailField.getText());
        user.setPassword(passwordField.getText());
        user.setAdmin(false);
        user.setLastName(lastNameField.getText());
        user.setFirstName(firstNameField.getText());
        user.setMiddleName(middleNameField.getText());
        user.setPassportSeries(passportSeriesField.getText());
        user.setPassportNumber(passportNumberField.getText());
        user.setBalance(0);
        user.setBlocked(false);

        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getMiddleName());
        System.out.println(user.getPassportSeries());
        System.out.println(user.getPassportNumber());

        Client.coos.writeObject(user);
        try {
            String msg = (String) Client.cois.readObject();
            if(msg.equals("Пользователь успешно зарегистрирован")){
                mailField.clear();
                passwordField.clear();
                passportNumberField.clear();
                lastNameField.clear();
                firstNameField.clear();
                middleNameField.clear();
                passportSeriesField.clear();
                passportNumberField.clear();
            }
         else{
                switchToRegistrationFailedView();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML public void switchToRegistrationFailedView() throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Ошибка регистрации");
        Client.setRoot("registrationFailedView");
        window.setScene(Client.scene);
        window.show();
    }
}