/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.RequestStringHandler;
import tictactoe_server.Models.Client;
import tictactoe_server.Models.User;
import tictactoe_server.Repositories.UserRepository;

/**
 *
 * @author Esraa
 */
public class RequestHandler {
    public static void queryHandler(Client client){
        switch(client.getUser().getQueryType()){
            case 0:
                logIn(client);
                break;
            case 1:
                signUP(client);
                break;
            case 2:
                getActivePlayers(client);
                break;   
        }
        
    }
    private static  void  logIn(Client client){
        try {
            if(UserRepository.getByName(client.getUser().getName(),client.getUser().getPass())!=null)
                ResponseHandler.response(client, "1");
            
            else 
                ResponseHandler.response(client, "0");
        } catch (SQLException ex) {
            ResponseHandler.response(client, "-1");
        }
    }
    private static void signUP(Client client){
        try {
            if(UserRepository.create(client.getUser()) != null)
                ResponseHandler.response(client, "1");
            else
                ResponseHandler.response(client, "0");
        } catch (SQLException ex) {
            ResponseHandler.response(client, "-1");
        }
    }
    
    private static void getActivePlayers(Client client){
        ResponseHandler.response(client, RequestStringHandler.collect(Communicator.getUsers(client)));
    }
}
