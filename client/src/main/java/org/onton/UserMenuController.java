package org.onton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.onton.entity.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {

    @FXML
    private Pane homePane;
    @FXML
    private Pane profilePane;
    @FXML
    private Pane airportsPane;
    @FXML
    private Pane flightsPane;
    @FXML
    private Pane ticketsPane;


    @FXML
    private Button homePageButton;
    @FXML
    private Button profilePageButton;
    @FXML
    private Button airportsPageButton;
    @FXML
    private Button flightsPageButton;
    @FXML
    private Button ticketsPageButton;

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
    @FXML private Button buyTicketButton;


    @FXML
    private TableView<AirportTableClass> airportTableView;
    @FXML
    private TableColumn<AirportTableClass, Integer> idAirportColumn;
    @FXML
    private TableColumn<AirportTableClass, String> airportNameColumn;
    @FXML
    private TableColumn<AirportTableClass, String> airportCityColumn;

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


    @FXML
    private TextField mailProfileTextField;
    @FXML
    private TextField passwordProfileTextField;
    @FXML
    private TextField lastnameProfileTextField;
    @FXML
    private TextField firstnameProfileTextField;
    @FXML
    private TextField middlenameProfileTextField;
    @FXML
    private TextField passportSeriesProfileTextField;
    @FXML
    private TextField passportNumberProfileTextField;
    @FXML
    private TextField balanceProfileTextField;
    @FXML
    private Button saveProfileButton;
    @FXML
    private Button editProfileButton;
    @FXML
    private Button topUpBalanceButton;

    @FXML private Button printTicketInDOCXButton;
    @FXML private Button removeTicketButton;
    @FXML private Button searchFlightButton;
    @FXML private TextField searchFlightTextField;


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
        if (event.getSource() == homePageButton) {
            homePane.toFront();
        } else if (event.getSource() == flightsPageButton) {
            flightTableInit(getFlightList());
            flightsPane.toFront();
        } else if (event.getSource() == profilePageButton) {
            profilePaneInit();
            profilePane.toFront();
        } else if (event.getSource() == airportsPageButton) {
            airportTableInit(getAirportList());
            airportsPane.toFront();
        } else if (event.getSource() == ticketsPageButton) {
            myTicketsTableInit();
            ticketsPane.toFront();
        }
    }

    public void flightTableInit(List<Flight> flightList) {
        idFlightColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        airlineFlightColumn.setCellValueFactory(new PropertyValueFactory<>("airline"));
        cityOfDepartureColumn.setCellValueFactory(new PropertyValueFactory<>("cityOfDeparture"));
        airportOfDepartureColumn.setCellValueFactory(new PropertyValueFactory<>("airportOfDeparture"));
        cityOfDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("cityOfDestination"));
        airportOfDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("airportOfDestination"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfDeparture"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfDeparture"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        flightTableView.setItems(getFlightTableClassObservableList(flightList));
        buyTicketButton.setVisible(false);

    }

    public void profilePaneInit() {
        mailProfileTextField.setText(Client.user.getMail());
        passwordProfileTextField.setText(Client.user.getPassword());
        lastnameProfileTextField.setText(Client.user.getLastName());
        firstnameProfileTextField.setText(Client.user.getFirstName());
        middlenameProfileTextField.setText(Client.user.getMiddleName());
        passportSeriesProfileTextField.setText(Client.user.getPassportSeries());
        passportNumberProfileTextField.setText(Client.user.getPassportNumber());
        balanceProfileTextField.setText(String.valueOf(Client.user.getBalance()));

        mailProfileTextField.setEditable(false);
        passwordProfileTextField.setEditable(false);
        lastnameProfileTextField.setEditable(false);
        firstnameProfileTextField.setEditable(false);
        middlenameProfileTextField.setEditable(false);
        passportSeriesProfileTextField.setEditable(false);
        passportNumberProfileTextField.setEditable(false);
        balanceProfileTextField.setEditable(false);

        saveProfileButton.setVisible(false);
    }

    public void editProfileButtonPressed(ActionEvent event) throws IOException {
        mailProfileTextField.setEditable(true);
        passwordProfileTextField.setEditable(true);
        lastnameProfileTextField.setEditable(true);
        firstnameProfileTextField.setEditable(true);
        middlenameProfileTextField.setEditable(true);
        passportSeriesProfileTextField.setEditable(true);
        passportNumberProfileTextField.setEditable(true);

        saveProfileButton.setVisible(true);
    }

    public void toUpBalanceProfileButtonPressed(ActionEvent event) throws IOException {
        balanceProfileTextField.setEditable(true);
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
        Client.user.setBalance(Integer.parseInt(balanceProfileTextField.getText()));

        Client.coos.writeObject("edit_profile");
        Client.coos.writeObject(Client.user);

        profilePaneInit();

    }

    public void myTicketsTableInit() {

        idTicketColumn.setCellValueFactory(new PropertyValueFactory<>("idTicket"));
        idFlightColumn1.setCellValueFactory(new PropertyValueFactory<>("idFlight"));
       airlineTicketColumn.setCellValueFactory(new PropertyValueFactory<>("airline"));
        cityOfDepartureColumn1.setCellValueFactory(new PropertyValueFactory<>("cityOfDeparture"));
        cityOfDestinationColumn1.setCellValueFactory(new PropertyValueFactory<>("cityOfDestination"));
        flightDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfFlight"));
        flightTimeColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfFlight"));
        userLastnameColumn.setCellValueFactory(new PropertyValueFactory<>("userLastname"));
        dateSaleColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfSale"));
        ticketsTableView.setItems(getMyTicketTableClassObservableList());

        printTicketInDOCXButton.setVisible(false);
        removeTicketButton.setVisible(false);
    }

    public void airportTableInit(List<Airport> airportList) {

        idAirportColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        airportNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        airportCityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        airportTableView.setItems(getAirportTableClassObservableList(airportList));

        printTicketInDOCXButton.setVisible(false);
        removeTicketButton.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePane.toFront();
        flightTableInit(getFlightList());
        profilePaneInit();
        myTicketsTableInit();

    }


    public void flightTableRowSelected(MouseEvent event) {
        FlightTableClass flight = flightTableView.getSelectionModel().getSelectedItem();
        if (flight != null) {
            buyTicketButton.setVisible(true);
        }
    }

    public void ticketsTableRowSelected(MouseEvent event) {
        TicketTableClass ticketTableClass = ticketsTableView.getSelectionModel().getSelectedItem();
        if (ticketTableClass != null) {
            printTicketInDOCXButton.setVisible(true);
            removeTicketButton.setVisible(true);
        }
    }

    public void removeTicketButtonPresseed(ActionEvent event)throws IOException{
        TicketTableClass ticketTableClass = ticketsTableView.getSelectionModel().getSelectedItem();
        List<Ticket> ticketList = getTicketList();
        for(Ticket i:ticketList){
            if(ticketTableClass.getIdTicket()==i.getId()){
                Client.coos.writeObject("delete_ticket");
                Client.coos.writeObject(i);
                myTicketsTableInit();
            }
        }
    }

    public void printTicketInDOCXButtonPressed(ActionEvent event) throws IOException {
        TicketTableClass ticketTableClass = ticketsTableView.getSelectionModel().getSelectedItem();
        List<Ticket> ticketList = getTicketList();
        for (Ticket i : ticketList) {
            if (i.getId() == ticketTableClass.getIdTicket()) {
                Client.coos.writeObject("print_my_ticket_in_docx");
                Client.coos.writeObject(i);
            }
        }
    }

    public void buyTicketButtonPressed(ActionEvent event) throws IOException {
        FlightTableClass flightTableClass = flightTableView.getSelectionModel().getSelectedItem();
        List<Flight> flightList = getFlightList();
        Client.coos.writeObject("buy_ticket");
        Client.coos.writeObject(Client.user);
        for(Flight i: flightList){
            if(flightTableClass.getId()==i.getId()){
                Client.coos.writeObject(i);
            }
        }

        try {
            String msg = (String) Client.cois.readObject();

            if(msg.equals("Операция проведена успешно")){
                switchToPurchaseReceipt(event);
            }else{
                switchToPurchaseFailedView(event);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        profilePaneInit();
        myTicketsTableInit();
        ticketsPane.toFront();
    }

    public void searchFlightButtonPressed(ActionEvent event) throws IOException {
        if(!searchFlightTextField.getText().equals("")){
            Client.coos.writeObject("search_flight");
            Client.coos.writeObject(searchFlightTextField.getText());
            try {
                String msg = (String) Client.cois.readObject();

                if(msg.equals("Ничего не найдено")){

                }
                if(msg.equals("Результаты поиска")){
                    List<Flight> searchFlightList = (List<Flight>) Client.cois.readObject();
                    flightTableInit(searchFlightList);

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML public void switchToPurchaseReceipt(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(Client.class.getResource("purchaseReceipt.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage popupStage = new Stage();

        popupStage.initOwner(((Node)event.getSource()).getScene().getWindow());
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    @FXML public void switchToPurchaseFailedView(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(Client.class.getResource("purchaseFailedView.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage popupStage = new Stage();

        popupStage.initOwner(((Node)event.getSource()).getScene().getWindow());
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.setScene(scene);
        popupStage.showAndWait();
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

    public ObservableList<FlightTableClass> getFlightTableClassObservableList(List<Flight> flightList) {


        List<FlightTableClass> list = new ArrayList<>(flightList.size());
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
        return FXCollections.observableList(list);
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

   /* public ObservableList<Airport> getAirportObservableList(){
        List<Airport> airportList = getAirportList();
        return FXCollections.observableList(airportList);
    }*/

    public ObservableList<AirportTableClass> getAirportTableClassObservableList(List<Airport> airportList) {


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

    public List<TicketTableClass> getMyTicketTableClassList(List<Ticket> ticketList) {
        List<TicketTableClass> list = new ArrayList<>();
        try {
            int counter = 0;
            for (Ticket j : ticketList) {
                if (Client.user.getId() == j.getUser().getId()) {
                    TicketTableClass ticketTableClass = new TicketTableClass();
                    list.add(ticketTableClass);
                    list.get(counter).setIdTicket(j.getId());
                    list.get(counter).setIdFlight(j.getFlight().getId());
                    list.get(counter).setAirline(j.getFlight().getAirline().getName());
                    list.get(counter).setCityOfDeparture(j.getFlight().getAirportOfDeparture().getCity().getName());
                    list.get(counter).setCityOfDestination(j.getFlight().getAirportOfDestination().getCity().getName());
                    list.get(counter).setDateOfFlight(j.getFlight().getDateOfDeparture());
                    list.get(counter).setTimeOfFlight(j.getFlight().getTimeOfDeparture());
                    list.get(counter).setUserLastname(j.getUser().getLastName());
                    list.get(counter).setDateOfSale(j.getSaleDate());
                    counter++;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<TicketTableClass> getMyTicketTableClassObservableList() {
        return FXCollections.observableList(getMyTicketTableClassList(getTicketList()));
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



}
