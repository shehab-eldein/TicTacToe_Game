/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import tictactoe_server.Models.Client;
import tictactoe_server.Models.User;
import tictactoe_server.Repositories.UserRepository;

/**
 *
 * @author Esraa
 */
public class RequestHandler {
    public static void queryHandler(User user,Client client){
        switch(user.getQueryType()){
            case 0:
                logIn(user,client);
                break;
            case 1:
                signUP(user,client);
        }
        
    }
    private static  void  logIn(User user,Client client){
       if(UserRepository.getByName(user.getName(),user.getPass())!=null)
           ResponseHandler.response(client, "1");
       else
           ResponseHandler.response(client, "0"); 
    }
    private static void signUP(User user,Client client){
         ;
        if(UserRepository.create(user) != null)
            ResponseHandler.response(client, "1");
        else
            ResponseHandler.response(client, "0");
    }
    
}
