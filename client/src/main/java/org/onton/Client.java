package org.onton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.onton.entity.User;

import java.io.*;
import java.net.Socket;

/**
 * JavaFX Client
 */
public class Client extends Application {

    public static Scene scene;
    public static ObjectOutputStream coos;
    public static ObjectInputStream cois;
    public static Socket clientSocket;

    public static User user;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("authorization"));
        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.show();
    }

   public  static void setRoot(String fxml) throws IOException {
        scene = new Scene((loadFXML(fxml)));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        try {
            System.out.println("server connecting....");
            clientSocket = new Socket("127.0.0.1", 2525);//установление соединения между локальной машиной и указанным портом узла сети
            System.out.println("connection established....");
            BufferedReader stdin =
                    new BufferedReader(new InputStreamReader(System.in));//созданиебуферизированного символьного потока ввода
             coos = new ObjectOutputStream(clientSocket.getOutputStream());//создание потока вывода
             cois = new ObjectInputStream(clientSocket.getInputStream());//создание потока ввода

            launch();

        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е

        } finally{
            try {
                coos.close();//закрытие потока вывода
            } catch (Exception e){
                e.printStackTrace();
            }

            try {
                cois.close();//закрытие потока ввода
            } catch(Exception e){
                e.printStackTrace();
            }

            try {
                clientSocket.close();//закрытие сокета
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}