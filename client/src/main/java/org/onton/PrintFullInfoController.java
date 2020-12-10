package org.onton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.onton.entity.Flight;
import org.onton.entity.User;

import java.io.IOException;

public class PrintFullInfoController {

    private User user;
    private Flight flight;



    @FXML
    private Button backButton;

    @FXML private TextField lastNameField;
    @FXML private TextField firstNameField;
    @FXML private TextField middleNameField;
    @FXML private TextField dateOfBirthField;
    @FXML private TextField passportSeriesField;
    @FXML private TextField passportNumberField;
    @FXML private TextField dateOfDeparture;
    @FXML private TextField placeOfDeparture;
    @FXML private TextField placeOfDestination;

    public PrintFullInfoController() {
    }


    @FXML
    public void backButtonPressed(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Информация о пассажирах");
        Client.setRoot("passengerInfoView");
        window.setScene(Client.scene);
        window.show();
    }

  /*  public void initData(User user){
        this.user=user;
        lastNameField.setText(user.getLastName());
        firstNameField.setText(user.getFirstName());
        middleNameField.setText(user.getMiddleName());
        dateOfBirthField.setText(user.getDateOfBirth().toString());
        passportSeriesField.setText(user.getPassportSeries());
        passportNumberField.setText(user.getPassportNumber());




        lastNameField.setEditable(false);
        firstNameField.setEditable(false);
        middleNameField.setEditable(false);
        dateOfBirthField.setEditable(false);
        passportSeriesField.setEditable(false);
        passportNumberField.setEditable(false);

    }

*/

}
