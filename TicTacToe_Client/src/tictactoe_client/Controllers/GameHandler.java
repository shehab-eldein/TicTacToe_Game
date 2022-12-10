/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Controllers;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import services.ErrorMessageSender;

/**
 *
 * @author Esraa
 */
public class GameHandler extends Thread {

    private final Socket socket;
    private final DataInputStream dataInputstream;
    private final PrintStream printStream;
    private boolean isRunning = true;
    private final ErrorMessageSender errorMessageSender;

    public GameHandler(ErrorMessageSender errorMessageSender) throws IOException {
        
        this.errorMessageSender=errorMessageSender;
        
        socket = new Socket("127.0.0.1", 5005);
        dataInputstream = new DataInputStream(socket.getInputStream());
        printStream = new PrintStream(socket.getOutputStream());
        

    }
    

    @Override
    public void run() {

        while (isRunning) {

            try {

                String str = dataInputstream.readLine();

                if (str != null) {
                    if (!str.isEmpty()) {
                        System.out.println(str);
                    }
                }

            } catch (IOException ex) {
                errorMessageSender.send(ex.getMessage());
                if (!socket.isConnected()) {

                    try {
                        disconnect();
                    } catch (IOException ex1) {
                       errorMessageSender.send(ex1.getMessage());
                    }
                }
            }

        }

    }

    void disconnect() throws IOException {
        isRunning = false;
        printStream.close();
        dataInputstream.close();
        socket.close();

    }

    GameHandler connect() throws IOException {
        GameHandler gameHandler = new GameHandler(errorMessageSender);
        gameHandler.start();
        return gameHandler;
    }

    void writeData(String msg) {

        printStream.println(msg);
        System.out.println("connected");

    }
    
}
