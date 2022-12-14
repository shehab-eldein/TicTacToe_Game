/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import tictactoe_server.Models.User;
import tictactoe_server.Repositories.UserRepository;

/**
 *
 * @author Esraa
 */
public class QueryTypeHandler {
    public static void queryHandler(User user){
        switch(user.getQueryType()){
            case 0:
                logIn(user);
                break;
            case 1:
                signUP(user);
        }
        
    }
    private static  User logIn(User user){
        return UserRepository.getByName(user.getName());
    }
    private static void signUP(User user){
         UserRepository.create(user);
        
    }
    
}
