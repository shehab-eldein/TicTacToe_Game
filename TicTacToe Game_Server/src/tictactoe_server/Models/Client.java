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

/**
 *
 * @author hamed
 */
//client handler version beta not supported it's just for trying
public class Client extends Thread {
    private static boolean isStarted = false;
    private DataInputStream dataInputStream;
    private PrintStream dataOutPutStream;
    private User user;
    private boolean isBusy;
    private Socket socket;


    public Client(Socket socket) throws IOException {
       this.socket = socket; 
    }

    @Override
    public void run() {
        while (isStarted) {
            try {
                //open stream not handled it will be soon
                String data = dataInputStream.readLine();
                user = splitRequest(data);
                if(user.getIsSigned() == 0)
                    UserRepository.create(user);
            } catch (IOException ex) {
                try {
                    closeSocket();
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
            }

        }
    }

    
    private User splitRequest(String data){
     List<String> queryList = Arrays.stream(data.split("\\-")) // split on comma
                .map(str -> str.trim()) // remove white-spaces
                .collect(Collectors.toList()); // collect to List  
        return new User(Integer.parseInt(queryList.get(0)), queryList.get(1), queryList.get(2),Integer.parseInt(queryList.get(3)));
    }

    public void closeSocket() throws IOException{
        dataInputStream.close();
       dataOutPutStream.close();
       isStarted = false;
    }
    
       public void StartSocket() throws IOException {
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutPutStream = new PrintStream(socket.getOutputStream());
        isStarted = true;
        isBusy = false;
        start();
    }

    public static boolean isIsStarted() {
        return isStarted;
    }

    public boolean isIsBusy() {
        return isBusy;
    }
    
    
    public boolean getIsStarted(){
        return isStarted;
    }

    public User getUser() {
        return user;
    }
}