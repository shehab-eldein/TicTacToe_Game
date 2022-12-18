/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe_server.Models.Client;
import tictactoe_server.Repositories.UserRepository;

/**
 *
 * @author hamed
 */
public class ServerConnector implements Runnable {
    
    private static ServerConnector instance;
    private ServerSocket serverSock;
    private boolean isServerConected = false;
    private Consumer<String> error;
    private int portNumber;
    private Consumer<String> onlineClientsCount;
    
    private ServerConnector(int portNumber, Consumer error,Consumer clientCount) throws IOException {
        this.error = error;
        this.portNumber = portNumber;
        this.onlineClientsCount=clientCount;
    }
    
    public static ServerConnector getServerConnectorInstance(int portNumber, Consumer error,Consumer clientCount) throws IOException {
        if (instance == null) {
            instance = new ServerConnector(portNumber, error,clientCount);
        }
        instance.portNumber = portNumber;
        instance.error = error;
        instance.onlineClientsCount=clientCount;
        return instance;
    }
    
    @Override
    public void run() {
        while (isServerConected) {
            try {
                new Client(serverSock.accept(),onlineClientsCount);
               // onlineClientsCount.accept(UserRepository.getUsersCount()+"");
            } catch (IOException ex) {
                error.accept(ex.getMessage());
                try {
                    disCounnect();
                    serverSock = null;
                } catch (IOException ex1) {
                    error.accept(ex.getMessage());
                    
                }
            } 
        }
    }
    
    public void connect() throws IOException {
        this.serverSock = new ServerSocket(portNumber);
        isServerConected = true;
        new Thread(this).start();
    }
    
    public void disCounnect() throws IOException {
        Communicator.terminateSockets();
        isServerConected = false;
        serverSock.close();
    }
    
    public boolean getIsServerConected() {
        return isServerConected;
    }
    
}
