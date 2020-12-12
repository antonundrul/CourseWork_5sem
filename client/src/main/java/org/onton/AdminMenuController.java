package org.onton;

import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.onton.entity.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AdminMenuController implements Initializable {


    @FXML private Pane homePane;
    @FXML private Pane profilePane;
    @FXML private Pane airportsPane;
    @FXML private Pane usersPane;
    @FXML private Pane flightsPane;
    @FXML private Pane ticketsPane;
    @FXML private Pane addFlightPane;
    @FXML private Pane statisticsPane;
    @FXML private Pane userInformationPane;
    @FXML private Pane reportsPane;


    @FXML private Button homePageButton;
    @FXML private Button profilePageButton;
    @FXML private Button airportsPageButton;
    @FXML private Button usersPageButton;
    @FXML private Button flightsPageButton;
    @FXML private Button ticketsPageButton;
    @FXML private Button statisticsPageButton;
    @FXML private Button reportButton;


    @FXML private Button removeUserButton;
    @FXML private Button printUserInformationButton;


    @FXML private Button removeFlightButton;
    @FXML private Button editFlightButton;
    @FXML private Button addFlightButton;

    @FXML private Button removeAirportButton;


    @FXML private TableView<User> usersTableView;
    @FXML private TableColumn<User, String> mailColumn;
    @FXML private TableColumn<User, String> passwordColumn;
    @FXML private TableColumn<User, String> lastNameColumn;
    @FXML private TableColumn<User, String> firstNameColumn;
    @FXML private TableColumn<User, String> middleNameColumn;
    @FXML private TableColumn<User, String> passportSeriesColumn;
    @FXML private TableColumn<User, String> passportNumberColumn;

    @FXML private TableView<FlightTableClass> flightTableView;
    @FXML private TableColumn<FlightTableClass, Integer> idFlightColumn;
    @FXML private TableColumn<FlightTableClass, String> airlineFlightColumn;
    @FXML private TableColumn<FlightTableClass, String> cityOfDepartureColumn;
    @FXML private TableColumn<FlightTableClass, String> airportOfDepartureColumn;
    @FXML private TableColumn<FlightTableClass, String> cityOfDestinationColumn;
    @FXML private TableColumn<FlightTableClass, String> airportOfDestinationColumn;
    @FXML private TableColumn<FlightTableClass, LocalDate> dateColumn;
    @FXML private TableColumn<FlightTableClass, LocalTime> timeColumn;
    @FXML private TableColumn<FlightTableClass, Integer> costColumn;

    @FXML private TableView<AirportTableClass> airportTableView;
    @FXML private TableColumn<AirportTableClass, Integer> idAirportColumn;
    @FXML private TableColumn<AirportTableClass, String> airportNameColumn;
    @FXML private TableColumn<AirportTableClass, String> airportCityColumn;

    @FXML private TableView<TicketTableClass> ticketsTableView;
    @FXML private TableColumn<TicketTableClass, Integer> idTicketColumn;
    @FXML private TableColumn<TicketTableClass, Integer> idFlightColumn1;
    @FXML private TableColumn<TicketTableClass, String> airlineTicketColumn;
    @FXML private TableColumn<TicketTableClass, String> cityOfDepartureColumn1;
    @FXML private TableColumn<TicketTableClass, String> cityOfDestinationColumn1;
    @FXML private TableColumn<TicketTableClass, LocalDate> flightDateColumn;
    @FXML private TableColumn<TicketTableClass, LocalTime> flightTimeColumn;
    @FXML private TableColumn<TicketTableClass, String> userLastnameColumn;
    @FXML private TableColumn<TicketTableClass, LocalDate> dateSaleColumn;

    @FXML private ChoiceBox<Airport> airportOfDepartureChoiceBox;
    @FXML private ChoiceBox<Airport> airportOfDestinationChoiceBox;
    @FXML private ChoiceBox<Airline> airlineChoiceBox;
    @FXML private DatePicker dateOfDepartureDatePicker;
    @FXML private TextField timeOfDepartureTextField;
    @FXML private TextField costTextField;

    @FXML private TextField incomeTextField;
    @FXML private TextField amountOfTicketsTextField;
    @FXML private TextField adminFIOTextField;



    @FXML private TextField mailProfileTextField;
    @FXML private TextField passwordProfileTextField;
    @FXML private TextField lastnameProfileTextField;
    @FXML private TextField firstnameProfileTextField;
    @FXML private TextField middlenameProfileTextField;
    @FXML private TextField passportSeriesProfileTextField;
    @FXML private TextField passportNumberProfileTextField;
    @FXML private Button editProfileButton;
    @FXML private Button saveProfileButton;

    @FXML private TextField mailUserTextField;
    @FXML private TextField passwordUserTextField;
    @FXML private TextField lastnameUserTextField;
    @FXML private TextField firstnameUserTextField;
    @FXML private TextField middlenameUserTextField;
    @FXML private TextField passportSeriesUserTextField;
    @FXML private TextField passportNumberUserTextField;
    @FXML private CheckBox isAdminCheckBox;
    @FXML private CheckBox isBlockedCheckBox;
    @FXML private Button editUserButton;
    @FXML private Button saveUserButton;


    @FXML
    public void switchToAddAirportView(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Добавление аэропорта");
        Client.setRoot("addAirportView");
        window.setScene(Client.scene);
        window.show();
    }

    @FXML
    public void switchToAddAirlineView(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Добавление авиакомпании");
        Client.setRoot("addAirlineView");
        window.setScene(Client.scene);
        window.show();
    }

    @FXML
    public void switchToAuthorization(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Авторизация");
        Client.setRoot("authorization");
        window.setScene(Client.scene);
        window.show();
    }

    @FXML
    void handleCliks(ActionEvent event) {
        if (event.getSource() == usersPageButton) {
            userTableInit();
            usersPane.toFront();
        } else if (event.getSource() == homePageButton) {
            homePagePaneInit();
            homePane.toFront();
        } else if (event.getSource() == flightsPageButton) {
            flightTableInit();
            flightsPane.toFront();
        } else if (event.getSource() == profilePageButton) {
            profilePaneInit();
            profilePane.toFront();
        } else if (event.getSource() == airportsPageButton) {
            airportTableInit();
            airportsPane.toFront();
        } else if (event.getSource() == ticketsPageButton) {
            ticketTableInit();
            ticketsPane.toFront();
        } else if (event.getSource() == statisticsPageButton) {
            statisticsPane.toFront();
        } else if (event.getSource() == addFlightButton) {
            addFlightPane.toFront();
        } else if (event.getSource() == printUserInformationButton) {
            editUserPaneInit(usersTableView.getSelectionModel().getSelectedItem());
            userInformationPane.toFront();
        }else if (event.getSource() == reportButton) {
            reportsPane.toFront();
        }
    }

    public void editUserPaneInit(User user) {
        mailUserTextField.setText(user.getMail());
        passwordUserTextField.setText(user.getPassword());
        lastnameUserTextField.setText(user.getLastName());
        firstnameUserTextField.setText(user.getFirstName());
        middlenameUserTextField.setText(user.getMiddleName());
        passportSeriesUserTextField.setText(user.getPassportSeries());
        passportNumberUserTextField.setText(user.getPassportNumber());
        isAdminCheckBox.setSelected(user.getAdmin());
        isBlockedCheckBox.setSelected(user.getBlocked());

        mailUserTextField.setEditable(false);
        passwordUserTextField.setEditable(false);
        lastnameUserTextField.setEditable(false);
        firstnameUserTextField.setEditable(false);
        middlenameUserTextField.setEditable(false);
        passportSeriesUserTextField.setEditable(false);
        passportNumberUserTextField.setEditable(false);
        isAdminCheckBox.setDisable(true);
        isBlockedCheckBox.setDisable(true);

        saveUserButton.setVisible(false);
    }

    public void editUserButtonPressed(ActionEvent event) throws Exception {
        mailUserTextField.setEditable(true);
        passwordUserTextField.setEditable(true);
        lastnameUserTextField.setEditable(true);
        firstnameUserTextField.setEditable(true);
        middlenameUserTextField.setEditable(true);
        passportSeriesUserTextField.setEditable(true);
        passportNumberUserTextField.setEditable(true);
        isAdminCheckBox.setDisable(false);

        if(!isAdminCheckBox.isSelected()){
            isBlockedCheckBox.setDisable(false);
        }

        saveUserButton.setVisible(true);

    }

    public void saveUserButtonPressed(ActionEvent event) throws Exception {
        User user = usersTableView.getSelectionModel().getSelectedItem();
        user.setMail(mailUserTextField.getText());
        user.setPassword(passwordUserTextField.getText());
        user.setLastName(lastnameUserTextField.getText());
        user.setFirstName(firstnameUserTextField.getText());
        user.setMiddleName(middlenameUserTextField.getText());
        user.setPassportSeries(passportSeriesUserTextField.getText());
        user.setPassportNumber(passportNumberUserTextField.getText());
        user.setAdmin(isAdminCheckBox.isSelected());

        if(isAdminCheckBox.isSelected()){
            isBlockedCheckBox.setSelected(false);
            isBlockedCheckBox.setDisable(true);
            user.setBlocked(isBlockedCheckBox.isSelected());
        }
        else{
            user.setBlocked(isBlockedCheckBox.isSelected());
        }
//        user.setBlocked(isBlockedCheckBox.isSelected());

        Client.coos.writeObject("edit_profile");
        Client.coos.writeObject(user);

        editUserPaneInit(user);
        userTableInit();

    }

    public void userTableInit() {
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        passportSeriesColumn.setCellValueFactory(new PropertyValueFactory<>("passportSeries"));
        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));
        usersTableView.setItems(getUserObservableList());

        removeUserButton.setVisible(false);
        printUserInformationButton.setVisible(false);
    }

    public void homePagePaneInit() {
        adminFIOTextField.setText(Client.user.getFirstName()+" "+Client.user.getMiddleName());
        List<Ticket> ticketList = getTicketList();
        amountOfTicketsTextField.setText(String.valueOf(ticketList.size()));
        int income=0;
        for(Ticket i: ticketList){
            income+=i.getFlight().getCost();
        }
        incomeTextField.setText(String.valueOf(income));
    }

    public void flightTableInit() {
        idFlightColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        airlineFlightColumn.setCellValueFactory(new PropertyValueFactory<>("airline"));
        cityOfDepartureColumn.setCellValueFactory(new PropertyValueFactory<>("cityOfDeparture"));
        airportOfDepartureColumn.setCellValueFactory(new PropertyValueFactory<>("airportOfDeparture"));
        cityOfDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("cityOfDestination"));
        airportOfDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("airportOfDestination"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfDeparture"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfDeparture"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        flightTableView.setItems(getFlightTableClassObservableList());

        removeFlightButton.setVisible(false);
        editFlightButton.setVisible(false);
    }

    public void airportTableInit() {
        idAirportColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        airportNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        airportCityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        airportTableView.setItems(getAirportTableClassObservableList());
        removeAirportButton.setVisible(false);
    }

    public void ticketTableInit() {
        idTicketColumn.setCellValueFactory(new PropertyValueFactory<>("idTicket"));
        idFlightColumn1.setCellValueFactory(new PropertyValueFactory<>("idFlight"));
        airlineTicketColumn.setCellValueFactory(new PropertyValueFactory<>("airline"));
        cityOfDepartureColumn1.setCellValueFactory(new PropertyValueFactory<>("cityOfDeparture"));
        cityOfDestinationColumn1.setCellValueFactory(new PropertyValueFactory<>("cityOfDestination"));
        flightDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfFlight"));
        flightTimeColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfFlight"));
        userLastnameColumn.setCellValueFactory(new PropertyValueFactory<>("userLastname"));
        dateSaleColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfSale"));
        ticketsTableView.setItems(getTicketTableClassObservableList());
    }

    public void profilePaneInit() {
        mailProfileTextField.setText(Client.user.getMail());
        passwordProfileTextField.setText(Client.user.getPassword());
        lastnameProfileTextField.setText(Client.user.getLastName());
        firstnameProfileTextField.setText(Client.user.getFirstName());
        middlenameProfileTextField.setText(Client.user.getMiddleName());
        passportSeriesProfileTextField.setText(Client.user.getPassportSeries());
        passportNumberProfileTextField.setText(Client.user.getPassportNumber());

        mailProfileTextField.setEditable(false);
        passwordProfileTextField.setEditable(false);
        lastnameProfileTextField.setEditable(false);
        firstnameProfileTextField.setEditable(false);
        middlenameProfileTextField.setEditable(false);
        passportSeriesProfileTextField.setEditable(false);
        passportNumberProfileTextField.setEditable(false);

        saveProfileButton.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePagePaneInit();
        homePane.toFront();
        userTableInit();
        flightTableInit();
        airportTableInit();
        ticketTableInit();
        profilePaneInit();

        airportOfDepartureChoiceBox.setItems(getAirportObservableList());
        airportOfDestinationChoiceBox.setItems(getAirportObservableList());
        airlineChoiceBox.setItems(getAirlineObservableList());

    }


    public void userTableRowSelected(MouseEvent event) {
        User user = usersTableView.getSelectionModel().getSelectedItem();
        if (user != null) {
            removeUserButton.setVisible(true);
            printUserInformationButton.setVisible(true);
        }
    }

    public void flightTableRowSelected(MouseEvent event) {
        FlightTableClass flight = flightTableView.getSelectionModel().getSelectedItem();
        if (flight != null) {
            removeFlightButton.setVisible(true);
            editFlightButton.setVisible(true);
        }
    }

    public void airportTableRowSelected(MouseEvent event) {
        AirportTableClass airport = airportTableView.getSelectionModel().getSelectedItem();
        if (airport != null) {
            removeAirportButton.setVisible(true);

        }
    }


    public void removeUserButtonPressed(ActionEvent event) throws Exception {
        Client.coos.writeObject("delete_user");
        User user = usersTableView.getSelectionModel().getSelectedItem();
        Client.coos.writeObject(user);
        userTableInit();
        ticketTableInit();
    }


    public void addFlightButtonPressed(ActionEvent event) throws Exception {

        Client.coos.writeObject("add_flight");
        Flight flight = new Flight();

        flight.setAirline(airlineChoiceBox.getValue());
        flight.setAirportOfDeparture(airportOfDepartureChoiceBox.getValue());
        flight.setAirportOfDestination(airportOfDestinationChoiceBox.getValue());
        flight.setDateOfDeparture(dateOfDepartureDatePicker.getValue());
        flight.setTimeOfDeparture(LocalTime.parse(timeOfDepartureTextField.getText()));
        flight.setCost(Integer.parseInt(costTextField.getText()));
        Client.coos.writeObject(flight);

        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Меню администратора");
        Client.setRoot("adminMenu");
        window.setScene(Client.scene);
        window.show();

    }

    public void removeFlightButtonPressed(ActionEvent event) throws Exception {
        List<Flight> flightList = getFlightList();
        List<Ticket> ticketList = getTicketList();
        FlightTableClass flightTableClass = flightTableView.getSelectionModel().getSelectedItem();

        List<TicketTableClass> ticketTableClassList = getTicketTableClassList(getTicketList());
        for (TicketTableClass i : ticketTableClassList) {
            if (i.getIdFlight() == flightTableClass.getId()) {
                ticketsTableView.getItems().remove(i);

            }
        }

        flightTableView.getItems().remove(flightTableClass);


        for (Ticket i : ticketList) {
            if (i.getFlight().getId() == flightTableClass.getId()) {
                Client.coos.writeObject("delete_ticket");
                Client.coos.writeObject(i);
            }
        }

        for (Flight i : flightList) {
            if (i.getId() == flightTableClass.getId()) {
                Client.coos.writeObject("delete_flight");
                Client.coos.writeObject(i);
            }
        }

        ticketTableInit();
        flightTableInit();
    }


    public void removeAirportButtonPressed(ActionEvent event) throws Exception {
        List<Airport> airportList = getAirportList();
        List<Flight> flightList = getFlightList();
        List<Ticket> ticketList = getTicketList();

        AirportTableClass airportTableClass = airportTableView.getSelectionModel().getSelectedItem();

        List<TicketTableClass> ticketTableClassList = getTicketTableClassList(getTicketList());
        List<FlightTableClass> flightTableClassList = getFlightTableClassList(getFlightList());

        airportTableView.getItems().remove(airportTableClass);


        for (Ticket i : ticketList) {
            if (i.getFlight().getAirportOfDeparture().getId() == airportTableClass.getId()
                    || i.getFlight().getAirportOfDestination().getId() == airportTableClass.getId()) {
                for (TicketTableClass j : ticketTableClassList) {
                    if (i.getId() == j.getIdTicket()) {
                        ticketsTableView.getItems().remove(j);
                    }
                }
                Client.coos.writeObject("delete_ticket");
                Client.coos.writeObject(i);
            }
        }

        for (Flight i : flightList) {
            if (i.getAirportOfDestination().getId() == airportTableClass.getId()
                    || i.getAirportOfDeparture().getId() == airportTableClass.getId()) {
                for (FlightTableClass j : flightTableClassList) {
                    if (i.getId() == j.getId()) {
                        flightTableView.getItems().remove(j);
                    }
                }
                Client.coos.writeObject("delete_flight");
                Client.coos.writeObject(i);
            }
        }
        for (Airport i : airportList) {
            if (i.getId() == airportTableClass.getId()) {

                Client.coos.writeObject("delete_airport");
                Client.coos.writeObject(i);
            }
        }
        airportTableView.getItems().remove(airportTableClass);
    }


    public void editProfileButtonPressed(ActionEvent event) throws Exception {
        mailProfileTextField.setEditable(true);
        passwordProfileTextField.setEditable(true);
        lastnameProfileTextField.setEditable(true);
        firstnameProfileTextField.setEditable(true);
        middlenameProfileTextField.setEditable(true);
        passportSeriesProfileTextField.setEditable(true);
        passportNumberProfileTextField.setEditable(true);

        saveProfileButton.setVisible(true);

    }

    public void saveProfileButtonPressed(ActionEvent event) throws Exception {

        Client.user.setMail(mailProfileTextField.getText());
        Client.user.setPassword(passwordProfileTextField.getText());
        Client.user.setLastName(lastnameProfileTextField.getText());
        Client.user.setFirstName(firstnameProfileTextField.getText());
        Client.user.setMiddleName(middlenameProfileTextField.getText());
        Client.user.setPassportSeries(passportSeriesProfileTextField.getText());
        Client.user.setPassportNumber(passportNumberProfileTextField.getText());

        Client.coos.writeObject("edit_profile");
        Client.coos.writeObject(Client.user);

        profilePaneInit();

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

    public List<Flight> getFlightList() {
        List<Flight> list = null;
        try {
            Client.coos.writeObject("print_flights");
            list = (List<Flight>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<FlightTableClass> getFlightTableClassList(List<Flight> flightList) {
        List<FlightTableClass> list = new ArrayList<>();
        try {
            for (int i = 0; i < flightList.size(); i++) {
                FlightTableClass flightTableClass = new FlightTableClass();
                list.add(flightTableClass);

                list.get(i).setId(flightList.get(i).getId());
                list.get(i).setAirline(flightList.get(i).getAirline().getName());
                list.get(i).setCityOfDeparture(flightList.get(i).getAirportOfDeparture().getCity().getName());
                list.get(i).setAirportOfDeparture(flightList.get(i).getAirportOfDeparture().getName());
                list.get(i).setCityOfDestination(flightList.get(i).getAirportOfDestination().getCity().getName());
                list.get(i).setAirportOfDestination(flightList.get(i).getAirportOfDestination().getName());
                list.get(i).setDateOfDeparture(flightList.get(i).getDateOfDeparture());
                list.get(i).setTimeOfDeparture(flightList.get(i).getTimeOfDeparture());
                list.get(i).setCost(flightList.get(i).getCost());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<FlightTableClass> getFlightTableClassObservableList() {
        return FXCollections.observableList(getFlightTableClassList(getFlightList()));
    }

    public List<Airport> getAirportList() {
        List<Airport> list = null;
        try {
            Client.coos.writeObject("print_airports");
            list = (List<Airport>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ObservableList<Airport> getAirportObservableList() {
        List<Airport> airportList = getAirportList();
        return FXCollections.observableList(airportList);
    }

    public ObservableList<Airline> getAirlineObservableList() {
        List<Airline> airlineList = getAirlineList();
        return FXCollections.observableList(airlineList);
    }

    public List<Airline> getAirlineList() {
        List<Airline> list = null;
        try {
            Client.coos.writeObject("print_airlines");
            list = (List<Airline>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ObservableList<AirportTableClass> getAirportTableClassObservableList() {

        List<Airport> airportList = getAirportList();
        List<AirportTableClass> list = new ArrayList<>();
        try {
            for (int i = 0; i < airportList.size(); i++) {
                AirportTableClass airportTableClass = new AirportTableClass();
                list.add(airportTableClass);

                list.get(i).setId(airportList.get(i).getId());
                list.get(i).setName(airportList.get(i).getName());
                list.get(i).setCity(airportList.get(i).getCity().getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableList(list);
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

    public List<TicketTableClass> getTicketTableClassList(List<Ticket> ticketList) {
        List<TicketTableClass> list = new ArrayList<>();
        try {
            for (int i = 0; i < ticketList.size(); i++) {
                TicketTableClass ticketTableClass = new TicketTableClass();
                list.add(ticketTableClass);

                list.get(i).setIdTicket(ticketList.get(i).getId());
                list.get(i).setIdFlight(ticketList.get(i).getFlight().getId());
                list.get(i).setAirline(ticketList.get(i).getFlight().getAirline().getName());
                list.get(i).setCityOfDeparture(ticketList.get(i).getFlight().getAirportOfDeparture().getCity().getName());
                list.get(i).setCityOfDestination(ticketList.get(i).getFlight().getAirportOfDestination().getCity().getName());
                list.get(i).setDateOfFlight(ticketList.get(i).getFlight().getDateOfDeparture());
                list.get(i).setTimeOfFlight(ticketList.get(i).getFlight().getTimeOfDeparture());
                list.get(i).setUserLastname(ticketList.get(i).getUser().getLastName());
                list.get(i).setDateOfSale(ticketList.get(i).getSaleDate());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<TicketTableClass> getTicketTableClassObservableList() {
        return FXCollections.observableList(getTicketTableClassList(getTicketList()));
    }


    @FXML
    public void switchToCitiesDiagram(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Статистика посещения городов");
        Client.setRoot("citiesDiagram");
        window.setScene(Client.scene);
        window.show();
    }
    @FXML
    public void switchToAirportDiagram(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Статистика аэропортов");
        Client.setRoot("airportDiagram");
        window.setScene(Client.scene);
        window.show();
    }
    @FXML
    public void switchToUsersDiagram(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Статистика клиентов");
        Client.setRoot("usersDiagram");
        window.setScene(Client.scene);
        window.show();
    }


    public void getUserReportDOCX(ActionEvent event) throws IOException {
        Client.coos.writeObject("print_users_in_docx");
    }
    public void getTicketReportDOCX(ActionEvent event) throws IOException {
        Client.coos.writeObject("print_tickets_in_docx");
    }
    public void getFlightReportDOCX(ActionEvent event) throws IOException {
        Client.coos.writeObject("print_flights_in_docx");
    }
    public void getAirportReportDOCX(ActionEvent event) throws IOException {
        Client.coos.writeObject("print_airports_in_docx");
    }
    public void getCityReportDOCX(ActionEvent event) throws IOException {
        Client.coos.writeObject("print_cities_in_docx");
    }


}