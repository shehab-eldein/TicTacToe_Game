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
import java.util.function.Consumer;
import services.ErrorMessageSender;

/**
 *
 * @author Esraa
 */
public class GameHandler implements Runnable {

    private static GameHandler instance;
    private Socket socket;
    private final DataInputStream dataInputstream;
    private final PrintStream printStream;
    private boolean isRunning = true;
    private  ErrorMessageSender errorMessageSender;
    private  Consumer<String> responseMessage;

    private GameHandler(ErrorMessageSender errorMessageSender, Consumer reConsumer) throws IOException {
        this.errorMessageSender = errorMessageSender;
        responseMessage = reConsumer;
        socket = new Socket("127.0.0.1", 5005);
        dataInputstream = new DataInputStream(socket.getInputStream());
        printStream = new PrintStream(socket.getOutputStream());
    }

    public static GameHandler getInstance(ErrorMessageSender errorMessageSender,Consumer<String> respose) throws IOException {
        if (instance == null) {
            instance = new GameHandler(errorMessageSender,respose);
        }
        instance.errorMessageSender=errorMessageSender;
        instance.responseMessage=respose;
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
        isRunning=true;
        socket= new Socket("127.0.0.1", 5005);
        new Thread(this).start();
                
    }

    public void writeData(String msg) {
        printStream.println(msg);
    }

}
