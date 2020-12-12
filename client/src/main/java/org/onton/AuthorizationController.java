package org.onton;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.onton.entity.User;

public class AuthorizationController {
   @FXML public Button enterButton;
   @FXML public Button registrationButton;
   @FXML public TextField mailTextField;
   @FXML public PasswordField passwordField;

    @FXML
    public void registrationButtonPressed(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Регистрация");
        Client.setRoot("registration");
        window.setScene(Client.scene);
        window.show();
    }


    public ObservableList<User> getUserObservableList() {
        List<User> list = null;
        try {
            Client.coos.writeObject("print_users");
            list = (List<User>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return FXCollections.observableList(list);
    }



    @FXML
    public void enterButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException {
        Client.coos.writeObject("authorization");
        String mail = mailTextField.getText();
        String password = passwordField.getText();
        Client.coos.writeObject(mail);
        Client.coos.writeObject(password);
        String msg= (String) Client.cois.readObject();
        if(msg.equals("Успешная авторизация")) {
            Client.user = (User) Client.cois.readObject();
            if (Client.user.getAdmin()) {
                switchToAdminMenu();
            } else {
                switchToUserMenu();
            }
        }else if(msg.equals("Данный пользователь заблокирован")){
            userBlockedView();
        }
        else {
            loginFailedView();
        }
    }

    @FXML public void switchToAdminMenu() throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Меню администратора");
        Client.setRoot("adminMenu");
        window.setScene(Client.scene);
        window.show();
    }

    @FXML public void switchToUserMenu() throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Меню пользователя");
        Client.setRoot("userMenu");
        window.setScene(Client.scene);
        window.show();
    }

    @FXML public void loginFailedView() throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Ошибка входа");
        Client.setRoot("loginFailedView");
        window.setScene(Client.scene);
        window.show();
    }

    @FXML public void userBlockedView() throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Пользователь заблокирован");
        Client.setRoot("userBlockView");
        window.setScene(Client.scene);
        window.show();
    }
}
