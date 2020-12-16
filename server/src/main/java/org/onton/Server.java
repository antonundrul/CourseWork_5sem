package org.onton;

import com.mysql.cj.Session;
import org.onton.db.SessionFactorySingleton;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class Server
{
    public static void main( String[] args )
    {
        SessionFactorySingleton.getInstance();
        ServerSocket serverSocket = null;
        try {
            System.out.println("server starting....");
            serverSocket = new ServerSocket(2525);//создание сокета сервера для заданного порта
            while(true){
                Thread t = new Thread(new ClientThread(serverSocket.accept()));
                t.start();

            }
             } catch (Exception e) {
               e.printStackTrace();
             } finally {
            print_log("server terminating....");
                try {
                    serverSocket.close();//закрытие сокета сервера
                } catch(Exception e) {
                    e.printStackTrace();//вызывается метод исключения е
                }
            }
    }

    public static void print_log(String msg) {
        System.out.printf("%s",msg);
    }
}
