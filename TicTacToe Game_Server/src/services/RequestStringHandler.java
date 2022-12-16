/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import tictactoe_server.Models.Client;
import tictactoe_server.Models.User;

/**
 *
 * @author hamed
 */
public class RequestStringHandler {
    
    public static String collect(List<User> clients){
        if(clients.isEmpty())
            return "-1";
        String request = "";
        int index = 1;
        for(User client : clients){
            request += (index +" "+ client.getName() + "-");
        }
        return request;
    }
    
}
