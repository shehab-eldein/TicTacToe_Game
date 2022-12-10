/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamed
 */
public class ServerConnector implements Runnable {

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
                new ClientsHandler(serverSock.accept());
            } catch (IOException ex) {
                error.accept(ex.getMessage());
                try {
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
        isServerConected = false;
        serverSock.close();
    }

}

//client handler version beta not supported it's just for trying
class ClientsHandler extends Thread {

    private final Vector<ClientsHandler> clientList;
    private byte[] DataByteList;
    DataInputStream dataInputStream;
    PrintStream dataOutPutStream;

    public ClientsHandler(Socket socket) throws IOException {
        this.clientList = new Vector();
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutPutStream = new PrintStream(socket.getOutputStream());
        clientList.add(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                //open stream not handled it will be soon
                dataInputStream.read(DataByteList);
                System.out.println(new String(DataByteList));
                sendMessageToClients(DataByteList);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    //not supported method in tic tac toe project
    private void sendMessageToClients(byte[] data) {
        for (int clientIndex = 0; clientIndex < clientList.size(); clientIndex++) {
            clientList.get(clientIndex).dataOutPutStream.println(new String(data));
        }
    }

}
