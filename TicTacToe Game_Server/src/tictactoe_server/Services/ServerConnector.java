/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe_server.Models.Client;

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

    private ServerConnector(int portNumber, Consumer error) throws IOException {
        this.error = error;
        this.portNumber = portNumber;
    }

    public static ServerConnector getServerConnectorInstance(int portNumber, Consumer error) throws IOException {
        if (instance == null) {
            instance = new ServerConnector(portNumber, error);
        }
        instance.portNumber = portNumber;
        instance.error = error;
        return instance;
    }

    @Override
    public void run() {
        while (isServerConected) {
            try {
                new Client(serverSock.accept());
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
