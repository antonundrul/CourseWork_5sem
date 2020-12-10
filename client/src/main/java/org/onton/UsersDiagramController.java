package org.onton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import org.onton.entity.City;
import org.onton.entity.Ticket;
import org.onton.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsersDiagramController implements Initializable {
    @FXML
    private PieChart usersChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getUsersDiagramm();

    }


    public void getUsersDiagramm(){
        ObservableList<PieChart.Data> usersData = FXCollections.observableArrayList();
        List<Ticket> ticketList = getTicketList();
        List<User> userList = getUserList();

        for(User i : userList) {
            double userCounter = 0;
            for (Ticket j : ticketList) {
                if (i.getId()==j.getUser().getId()) {
                    userCounter++;
                }
            }
            double value = (userCounter / ticketList.size()) * 100;
            System.out.println(i.getLastName()+" / "+value);
            PieChart.Data sector = new PieChart.Data(i.getLastName(), value);
            usersData.add(sector);
        }
        System.out.println(usersData);
        usersChart.setData(usersData);
        usersChart.setTitle("Статистика клиентов");
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

    public List<User> getUserList() {
        List<User> userList = null;
        try {
            Client.coos.writeObject("print_users");
            userList = (List<User>) Client.cois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    @FXML
    public void switchToAdminMenu(ActionEvent event) throws IOException {
        Stage window = (Stage) Client.scene.getWindow();
        window.setTitle("Меню администратора");
        Client.setRoot("adminMenu");
        window.setScene(Client.scene);
        window.show();
    }
}
