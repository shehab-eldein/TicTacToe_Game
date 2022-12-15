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

    private ServerSocket serverSock;
    private boolean isServerConected = false;
    private Consumer<String> error;
    private final int portNumber;

    public ServerConnector(int portNumber, Consumer error) throws IOException {
        this.error = error;
        this.portNumber = portNumber;
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
        isServerConected = false;
        Communicator.disconnectClosed();
        serverSock.close();
    }

}
