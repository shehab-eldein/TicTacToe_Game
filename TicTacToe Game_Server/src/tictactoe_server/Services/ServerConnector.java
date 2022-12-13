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

/**
 *
 * @author hamed
 */
public class ServerConnector implements Runnable {

    private Client clientHandler;
    private final ServerSocket serverSock;
    private final Thread thread = new Thread(this);
    private boolean isServerConected = false;
    private Consumer<String> error;

    public ServerConnector(ServerSocket serverSock, Consumer error) throws IOException {
        this.serverSock = serverSock;
        this.error = error;
    }

    @Override
    public void run() {
        while (isServerConected) {
            try {
                clientHandler = new Client(serverSock.accept());
            } catch (IOException ex) {
                error.accept(ex.getMessage());
                try {
                    clientHandler.closeSocket();
                    disCounnect();
                } catch (IOException ex1) {
                    error.accept(ex.getMessage());

                }
            }
        }
    }

    public void connect() throws IOException {
        isServerConected = true;
        thread.start();
    }

    public void disCounnect() throws IOException {
        serverSock.close();
        isServerConected = false;
        System.out.println(thread.isAlive());
        System.err.println(serverSock.isClosed());
    }

}
