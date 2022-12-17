/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Models;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import tictactoe_server.Models.User;
import tictactoe_server.Repositories.UserRepository;
import tictactoe_server.Services.Communicator;
import tictactoe_server.Services.RequestHandler;

/**
 *
 * @author hamed
 */
//client handler version beta not supported it's just for trying
public class Client extends Thread {

    private static boolean isStarted = true;
    private DataInputStream dataInputStream;
    private PrintStream dataOutPutStream;
    private User user;
    private int requestCode;
    private String request; 
    private boolean isBusy = false;
    private Socket socket;
    private String opponentName;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        StartSocket();
        start();
    }

    @Override
    public void run() {
        while (isStarted) {
            try {
                request = dataInputStream.readLine();
                setRequestCode(request);
                RequestHandler.queryHandler(this);
            } catch (IOException ex) {
                try {
                    closeSocket();
                    isStarted = false;
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
            }

        }
    }

    public boolean isStarted() {
        return isStarted;
    }


    public PrintStream getDataOutPutStream() {
        return dataOutPutStream;
    }
    private void setRequestCode(String data){
       requestCode = Integer.parseInt(data.charAt(0) + "");
    }

    public void closeSocket() throws IOException {
        dataInputStream.close();
        dataOutPutStream.close();
        socket.close();
        isStarted = false;
    }

    public void StartSocket() throws IOException {
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutPutStream = new PrintStream(socket.getOutputStream());
        isStarted = true;
        isBusy = false;
    }

    public void setIsBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }

    
    public boolean getIsBusy() {
        return isBusy;
    }

    public boolean getIsStarted() {
        return isStarted;
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public int getRequestCode() {
        return requestCode;
    }

    public String getRequest() {
        return request;
    }
    
    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }
}
