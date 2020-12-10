package org.onton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.onton.entity.Flight;
import org.onton.entity.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PassengerInfoViewController implements Initializable {

    @FXML
    private Button backToMenuButton;
    @FXML
    private Button fullInfoButton;
    @FXML
    private Button deleteButton;

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> middleNameColumn;
    @FXML
    private TableColumn<User, LocalDate> dateOfBirthColumn;
    @FXML
    private TableColumn<User, String> passportSeriesColumn;
    @FXML
    private TableColumn<User, String> passportNumberColumn;

    @FXML
    public void switchToPrimary(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Главное меню");
        Client.setRoot("primary");
        window.setScene(Client.scene);
        window.show();
    }


    public ObservableList<User> getPassengerObservableList() {
        List<User> list = null;
        try {
            Client.coos.writeObject("print_passengers");
            list = (List<User>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return FXCollections.observableList(list);
    }


    public ObservableList<Flight> getFlightObservableList() {
        List<Flight> list = null;
        try {
            // Client.coos.writeObject("print_passengers");
            list = (List<Flight>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return FXCollections.observableList(list);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        passportSeriesColumn.setCellValueFactory(new PropertyValueFactory<>("passportSeries"));
        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));



        tableView.setItems(getPassengerObservableList());


        fullInfoButton.setVisible(false);
        deleteButton.setVisible(false);
    }


    public void tableRowSelected(MouseEvent event) {
        User user = tableView.getSelectionModel().getSelectedItem();
        if (user != null) {
            fullInfoButton.setVisible(true);
            deleteButton.setVisible(true);

        }
    }


    public void removeButtonPressed(ActionEvent event) throws Exception{
        Client.coos.writeObject("delete_passenger");

        User user = tableView.getSelectionModel().getSelectedItem();

        Client.coos.writeObject(user);

        tableView.getItems().remove(user);
    }

    public void fullInfoButtonPressed(ActionEvent event) throws Exception{

        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Информация о пассажирах");
        Client.setRoot("printFullInfo");
        window.setScene(Client.scene);
        window.show();
    }

}