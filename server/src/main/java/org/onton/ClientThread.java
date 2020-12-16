package org.onton;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.onton.DAO.*;
import org.onton.entity.*;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.*;

public class ClientThread implements Runnable {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    @Override
    public void run() {
        String msg;
        try {
            Server.print_log("New user connected....");
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());


            msg = (String) inputStream.readObject();

            while (!msg.equals("exit")) {
                switch (msg) {
                    case "authorization" -> authorization();

                    case "add_user" -> addUser();
                    case "add_ticket" -> addTicket();
                    case "add_flight" -> addFlight();
                    case "add_airport" -> addAirport();
                    case "add_city" -> addCity();
                    case "add_airline" -> addAirline();
                    case "add_review" -> addReview();

                    case "print_users" -> printUsers();
                    case "print_tickets" -> printTickets();
                    case "print_flights" -> printFlights();
                    case "print_airports" -> printAirports();
                    case "print_cities" -> printCities();
                    case "print_airlines" -> printAirlines();
                    case "print_reviews" -> printReviews();

                    case "delete_user" -> deleteUser();
                    case "delete_ticket" -> deleteTicket();
                    case "delete_flight" -> deleteFlight();
                    case "delete_airport" -> deleteAirport();

                    case "edit_profile" -> editProfile();

                    case "print_users_in_docx" -> printUsersInDOCX();
                    case "print_tickets_in_docx" -> printTicketsInDOCX();
                    case "print_flights_in_docx" -> printFlightsInDOCX();
                    case "print_airports_in_docx" -> printAirportsInDOCX();
                    case "print_cities_in_docx" -> printCitiesInDOCX();
                    case "print_reviews_in_docx" -> printReviewsInDOCX();

                    case "buy_ticket" -> buyTicket();
                    case "print_my_ticket_in_docx" -> printMyTicketInDOCX();
                    case "search_flight" -> searchFlight();

                }

                msg = (String) inputStream.readObject();
                System.out.println(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Server.print_log("User disconnected....");
        }

    }


    private void authorization() throws IOException, ClassNotFoundException {
        List<User> userList = getUserList();
        User user = null;
        String msg = null;
        String mail = (String) inputStream.readObject();
        String password = (String) inputStream.readObject();
        for (int i = 0; i < userList.size(); i++) {
            if (mail.equals(userList.get(i).getMail())) {
                if (password.equals(userList.get(i).getPassword())) {
                    user = userList.get(i);
                    msg = "Успешная авторизация";
                    if(userList.get(i).getBlocked()){
                        msg = "Данный пользователь заблокирован";
                    }
                } else {
                    msg = "Неверно введен пароль";
                }
            }
        }
        if(user==null){
            msg = "Пользователя с таким E-mail не существует";
            outputStream.writeObject(msg);
        }else if (msg.equals("Успешная авторизация")) {
            outputStream.writeObject(msg);
            outputStream.writeObject(user);
        }else if(msg.equals("Неверно введен пароль")){
            outputStream.writeObject(msg);
        }else if(msg.equals("Данный пользователь заблокирован")){
            outputStream.writeObject(msg);
        }

        /*else{
            msg = "Пользователя с таким E-mail не существует";
        }*/

    }

    private void addUser() throws Exception {
        User user = (User) inputStream.readObject();
        List<User> userList = getUserList();
        String msg = null;
        for (User i : userList) {
            if (user.getMail().equals(i.getMail())) {
                msg = "Данный E-mail уже зарегистрирован";
            }
        }

        if (msg==null){
            outputStream.writeObject("Пользователь успешно зарегистрирован");
            DAO<User> daoUser = new UserDAO();
            daoUser.save(user);

        } else {
            outputStream.writeObject(msg);
        }
    }

    private void addTicket() throws Exception {
        Ticket ticket = (Ticket) inputStream.readObject();
        DAO<Ticket> daoTicket = new TicketDAO();
        daoTicket.save(ticket);
    }

    private void addFlight() throws Exception {
        Flight flight = (Flight) inputStream.readObject();
        DAO<Flight> daoFlight = new FlightDAO();
        daoFlight.save(flight);
    }

    private void addAirport() throws Exception {
        Airport airport = (Airport) inputStream.readObject();
        DAO<Airport> daoAirport = new AirportDAO();
        daoAirport.save(airport);
    }

    private void addCity() throws Exception {
        City city = (City) inputStream.readObject();
        DAO<City> daoCity = new CityDAO();
        daoCity.save(city);
    }

    private void addAirline() throws Exception {
        Airline airline = (Airline) inputStream.readObject();
        DAO<Airline> daoAirline = new AirlineDAO();
        daoAirline.save(airline);
    }

    private void addReview() throws Exception {
        Review review = (Review) inputStream.readObject();
        DAO<Review> daoReview = new ReviewDAO();
        daoReview.save(review);
    }


    private void printUsers() {
        List<User> userList = getUserList();
        try {
            outputStream.writeObject(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printTickets() {
        List<Ticket> ticketList = getTicketList();
        try {
            outputStream.writeObject(ticketList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printFlights() {
        List<Flight> flightList = getFlightList();
        try {
            outputStream.writeObject(flightList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printAirports() {
        List<Airport> airportList = getAirportList();
        try {
            outputStream.writeObject(airportList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printCities() {
        List<City> cityList = getCityList();
        try {
            outputStream.writeObject(cityList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printAirlines() {
        List<Airline> airlineList = getAirlineList();
        try {
            outputStream.writeObject(airlineList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printReviews() {
        List<Review> reviewList = getReviewList();
        try {
            outputStream.writeObject(reviewList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void deleteUser() throws Exception {
        User user = (User) inputStream.readObject();
        List<Ticket> ticketList = getTicketList();
        for(Ticket i: ticketList){
            if(user.getId()==i.getUser().getId()){
                DAO<Ticket> daoTicket = new TicketDAO();
                daoTicket.delete(i.getId());
            }
        }
        DAO<User> daoUser = new UserDAO();
        daoUser.delete(user.getId());
    }

    private void deleteTicket() throws Exception {
        Ticket ticket = (Ticket) inputStream.readObject();
        DAO<Ticket> daoTicket = new TicketDAO();
        daoTicket.delete(ticket.getId());
    }

    private void deleteFlight() throws Exception {
        Flight flight = (Flight) inputStream.readObject();
        DAO<Flight> daoFlight = new FlightDAO();
        daoFlight.delete(flight.getId());
    }

    private void deleteAirport() throws Exception{
        Airport airport = (Airport) inputStream.readObject();
        DAO<Airport> daoAirport = new AirportDAO();
        daoAirport.delete(airport.getId());
    }


    private void editProfile() throws Exception{
        User user = (User) inputStream.readObject();
        DAO<User> daoUser = new UserDAO();
        daoUser.save(user);
    }

    public List<User> getUserList() {
        DAO<User> dao = new UserDAO();
        List<User> list = dao.getList();

        if (list == null) {
            list = new ArrayList<User>();
        }

        return list;
    }

    public List<Ticket> getTicketList() {
        DAO<Ticket> dao = new TicketDAO();
        List<Ticket> list = dao.getList();

        if (list == null) {
            list = new ArrayList<Ticket>();
        }
        return list;
    }

    public List<Flight> getFlightList() {
        DAO<Flight> dao = new FlightDAO();
        List<Flight> list = dao.getList();

        if (list == null) {
            list = new ArrayList<Flight>();
        }
        return list;
    }

    public List<Airport> getAirportList() {
        DAO<Airport> dao = new AirportDAO();
        List<Airport> list = dao.getList();

        if (list == null) {
            list = new ArrayList<Airport>();
        }
        return list;
    }

    public List<City> getCityList() {
        DAO<City> dao = new CityDAO();
        List<City> list = dao.getList();

        if (list == null) {
            list = new ArrayList<City>();
        }
        return list;
    }

    public List<Airline> getAirlineList() {
        DAO<Airline> dao = new AirlineDAO();
        List<Airline> list = dao.getList();

        if (list == null) {
            list = new ArrayList<Airline>();
        }
        return list;
    }

    public List<Review> getReviewList() {
        DAO<Review> dao = new ReviewDAO();
        List<Review> list = dao.getList();

        if (list == null) {
            list = new ArrayList<Review>();
        }
        return list;
    }

    public void printUsersInDOCX() throws IOException, URISyntaxException, DocumentException {

        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("users.docx"));
        //create table
        XWPFTable table = document.createTable();

        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("ID");
        tableRowOne.addNewTableCell().setText("E-mail");
        tableRowOne.addNewTableCell().setText("Password");
        tableRowOne.addNewTableCell().setText("isAdmin");
        tableRowOne.addNewTableCell().setText("Lastname");
        tableRowOne.addNewTableCell().setText("Firstname");
        tableRowOne.addNewTableCell().setText("Middlename");
        tableRowOne.addNewTableCell().setText("Passport series");
        tableRowOne.addNewTableCell().setText("Passport number");

        List<User> userList = getUserList();
        for(User i: userList){
            XWPFTableRow tableRow = table.createRow();
            int counter=0;

            tableRow.getCell(0).setText(""+i.getId()+"");
            tableRow.getCell(1).setText(""+i.getMail()+"");
            tableRow.getCell(2).setText(""+i.getPassword()+"");
            tableRow.getCell(3).setText(""+i.getAdmin()+"");
            tableRow.getCell(4).setText(""+i.getLastName()+"");
            tableRow.getCell(5).setText(""+i.getFirstName()+"");
            tableRow.getCell(6).setText(""+i.getMiddleName()+"");
            tableRow.getCell(7).setText(""+i.getPassportSeries()+"");
            tableRow.getCell(8).setText(""+i.getPassportNumber()+"");

        }

        document.write(out);
        out.close();
        System.out.println("users.docx written successully");
    }

    public void printTicketsInDOCX() throws IOException, URISyntaxException, DocumentException {

        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("tickets.docx"));
        //create table
        XWPFTable table = document.createTable();

        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("ID");
        tableRowOne.addNewTableCell().setText("№ рейса");
        tableRowOne.addNewTableCell().setText("Откуда");
        tableRowOne.addNewTableCell().setText("Куда");
        tableRowOne.addNewTableCell().setText("Дата");
        tableRowOne.addNewTableCell().setText("Время");
        tableRowOne.addNewTableCell().setText("Фамилия");
        tableRowOne.addNewTableCell().setText("Дата покупки");

        List<Ticket> ticketList = getTicketList();
        for(Ticket i: ticketList){
            XWPFTableRow tableRow = table.createRow();
            int counter=0;

            tableRow.getCell(0).setText(""+i.getId()+"");
            tableRow.getCell(1).setText(""+i.getFlight().getId()+"");
            tableRow.getCell(2).setText(""+i.getFlight().getAirportOfDeparture().getCity().getName()+"");
            tableRow.getCell(3).setText(""+i.getFlight().getAirportOfDestination().getCity().getName()+"");
            tableRow.getCell(4).setText(""+i.getFlight().getDateOfDeparture()+"");
            tableRow.getCell(5).setText(""+i.getFlight().getTimeOfDeparture()+"");
            tableRow.getCell(6).setText(""+i.getUser().getLastName()+"");
            tableRow.getCell(7).setText(""+i.getSaleDate()+"");

        }

        document.write(out);
        out.close();
        System.out.println("tickets.docx written successully");
    }

    public void printFlightsInDOCX() throws IOException, URISyntaxException, DocumentException {

        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("flights.docx"));
        //create table
        XWPFTable table = document.createTable();

        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("ID");
        tableRowOne.addNewTableCell().setText("Откуда");
        tableRowOne.addNewTableCell().setText("Аэропорт");
        tableRowOne.addNewTableCell().setText("Куда");
        tableRowOne.addNewTableCell().setText("Аэропорт");
        tableRowOne.addNewTableCell().setText("Дата");
        tableRowOne.addNewTableCell().setText("Время");

        List<Flight> flightList = getFlightList();
        for(Flight i: flightList){
            XWPFTableRow tableRow = table.createRow();

            tableRow.getCell(0).setText(""+i.getId()+"");
            tableRow.getCell(1).setText(""+i.getAirportOfDeparture().getCity().getName()+"");
            tableRow.getCell(2).setText(""+i.getAirportOfDeparture().getName()+"");
            tableRow.getCell(3).setText(""+i.getAirportOfDestination().getCity().getName()+"");
            tableRow.getCell(4).setText(""+i.getAirportOfDestination().getName()+"");
            tableRow.getCell(5).setText(""+i.getDateOfDeparture()+"");
            tableRow.getCell(6).setText(""+i.getTimeOfDeparture()+"");

        }

        document.write(out);
        out.close();
        System.out.println("flights.docx written successully");
    }

    public void printAirportsInDOCX() throws IOException, URISyntaxException, DocumentException {

        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("airports.docx"));
        //create table
        XWPFTable table = document.createTable();

        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("ID");
        tableRowOne.addNewTableCell().setText("Название");
        tableRowOne.addNewTableCell().setText("Город");

        List<Airport> airportList = getAirportList();
        for(Airport i: airportList){
            XWPFTableRow tableRow = table.createRow();

            tableRow.getCell(0).setText(""+i.getId()+"");
            tableRow.getCell(1).setText(""+i.getName()+"");
            tableRow.getCell(2).setText(""+i.getCity().getName()+"");

        }

        document.write(out);
        out.close();
        System.out.println("airports.docx written successully");
    }

    public void printCitiesInDOCX() throws IOException, URISyntaxException, DocumentException {

        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("cities.docx"));
        //create table
        XWPFTable table = document.createTable();

        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("ID");
        tableRowOne.addNewTableCell().setText("Город");

        List<City> cityList = getCityList();
        for(City i: cityList){
            XWPFTableRow tableRow = table.createRow();

            tableRow.getCell(0).setText(""+i.getId()+"");
            tableRow.getCell(1).setText(""+i.getName()+"");

        }

        document.write(out);
        out.close();
        System.out.println("cities.docx written successully");
    }

    public void printMyTicketInDOCX() throws IOException, URISyntaxException, DocumentException, ClassNotFoundException {

        XWPFDocument document = new XWPFDocument();

        Ticket myTicket = (Ticket) inputStream.readObject();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(
                new File("билет_"+myTicket.getUser().getLastName()+"_"+myTicket.getId()+".docx"));

        XWPFTable table = document.createTable();

        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("ID");
        tableRowOne.addNewTableCell().setText("№ рейса");
        tableRowOne.addNewTableCell().setText("Откуда");
        tableRowOne.addNewTableCell().setText("Куда");
        tableRowOne.addNewTableCell().setText("Дата");
        tableRowOne.addNewTableCell().setText("Время");
        tableRowOne.addNewTableCell().setText("Фамилия");
        tableRowOne.addNewTableCell().setText("Дата покупки");
        tableRowOne.addNewTableCell().setText("Цена");


        XWPFTableRow tableRow = table.createRow();

        tableRow.getCell(0).setText(""+myTicket.getId()+"");
        tableRow.getCell(1).setText(""+myTicket.getFlight().getId()+"");
        tableRow.getCell(2).setText(""+myTicket.getFlight().getAirportOfDeparture().getCity().getName()+"");
        tableRow.getCell(3).setText(""+myTicket.getFlight().getAirportOfDestination().getCity().getName()+"");
        tableRow.getCell(4).setText(""+myTicket.getFlight().getDateOfDeparture()+"");
        tableRow.getCell(5).setText(""+myTicket.getFlight().getTimeOfDeparture()+"");
        tableRow.getCell(6).setText(""+myTicket.getUser().getLastName()+"");
        tableRow.getCell(7).setText(""+myTicket.getSaleDate()+"");
        tableRow.getCell(8).setText(""+myTicket.getFlight().getCost()+"");



        document.write(out);
        out.close();
        System.out.println("билет_"+myTicket.getUser().getLastName()+"_"+myTicket.getId()+".docx written successully");
    }


    public void printReviewsInDOCX() throws IOException, URISyntaxException, DocumentException, ClassNotFoundException {

        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("reviews.docx"));
        //create table
        XWPFTable table = document.createTable();

        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("ID");
        tableRowOne.addNewTableCell().setText("E-mail");
        tableRowOne.addNewTableCell().setText("Аэропорт");
        tableRowOne.addNewTableCell().setText("Отзыв");

        List<Review> reviewList = getReviewList();
        for(Review i: reviewList){
            XWPFTableRow tableRow = table.createRow();

            tableRow.getCell(0).setText(""+i.getId()+"");
            tableRow.getCell(1).setText(""+i.getUser().getMail()+"");
            tableRow.getCell(2).setText(""+i.getAirport().getName()+"");
            tableRow.getCell(3).setText(""+i.getFeedback()+"");

        }

        document.write(out);
        out.close();
        System.out.println("reviews.docx written successully");
    }

    public void buyTicket(){
        String msg=null;
        try {
            User user = (User) inputStream.readObject();
            Flight flight = (Flight) inputStream.readObject();
            if((user.getBalance()-flight.getCost())<0){
                msg="Недостаточно средств на счету";
                outputStream.writeObject(msg);
            }else{
                user.setBalance(user.getBalance()-flight.getCost());
                Ticket ticket = new Ticket();
                ticket.setFlight(flight);
                ticket.setUser(user);
                ticket.setSaleDate(LocalDate.now());

                DAO<Ticket> daoTicket = new TicketDAO();
                daoTicket.save(ticket);

                DAO<User> daoUser = new UserDAO();
                daoUser.save(user);

                msg = "Операция проведена успешно";
                outputStream.writeObject(msg);
                outputStream.writeObject(user);
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchFlight(){
        try {
            String searchString = (String) inputStream.readObject();
            List<Flight> flightList = getFlightList();
            List<Flight> searchFlightList = new ArrayList<>();

            for(Flight i: flightList){
                if(i.getAirportOfDeparture().getName().equals(searchString)){
                    searchFlightList.add(i);
                }
                if(i.getAirportOfDestination().getName().equals(searchString)){
                    searchFlightList.add(i);
                }
                if(i.getAirportOfDeparture().getCity().getName().equals(searchString)){
                    searchFlightList.add(i);
                }
                if(i.getAirportOfDestination().getCity().getName().equals(searchString)){
                    searchFlightList.add(i);
                }
            }

            if(flightList.size()==0){
              String  msg = "Ничего не найдено";
                outputStream.writeObject(msg);
            }
            else{
                String msg="Результаты поиска";
                outputStream.writeObject(msg);
                outputStream.writeObject(searchFlightList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}