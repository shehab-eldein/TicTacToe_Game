/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Controllers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ErrorMessageSender;

/**
 *
 * @author Esraa
 */
public class GameHandler implements Runnable {

    private static GameHandler instance;
    private Socket socket;
    private BufferedReader dataInputstream;
    private PrintWriter printStream;
    private boolean isRunning = false;
    private boolean isInGame = false;
    private ErrorMessageSender errorMessageSender;
    private Consumer<String> responseMessage;

    private GameHandler(ErrorMessageSender errorMessageSender, Consumer reConsumer) throws IOException {
        this.errorMessageSender = errorMessageSender;
        responseMessage = reConsumer;
        socket = new Socket("127.0.0.1", 5005);
        dataInputstream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printStream = new PrintWriter(socket.getOutputStream(), true);
    }

    public static GameHandler getInstance(ErrorMessageSender errorMessageSender, Consumer<String> respose) throws IOException {
        if (instance == null) {
            instance = new GameHandler(errorMessageSender, respose);
        }
        instance.errorMessageSender = errorMessageSender;
        instance.responseMessage = respose;
        return instance;
    }

    @Override
    public void run() {

        while (isRunning) {

            try {
                String str = dataInputstream.readLine();

                if (str != null) {
                    if (!str.isEmpty()) {
                        responseMessage.accept(str);
                    }
                }

            } catch (IOException ex) {
                errorMessageSender.send(ex.getMessage());
//                if (!socket.isConnected()) {
                try {
                    disconnect();
                } catch (IOException ex1) {
                    errorMessageSender.send(ex1.getMessage());
                }
//                }
            }

        }

    }

    public void disconnect() throws IOException {
        isRunning = false;
        printStream.close();
        dataInputstream.close();
        socket.close();

    }

    public void connect() throws IOException {
        isRunning = true;
        socket = new Socket("127.0.0.1", 5005);
        new Thread(this).start();
    }

    public void writeData(String msg) {

        printStream.println(msg);

    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public void setIsInGame(boolean inGame) {
        this.isInGame = inGame;
    }

    public boolean getIsInGame() {
        return isInGame;
    }

}
