/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import tictactoe_server.Models.Client;

/**
 *
 * @author Esraa
 */
public class ResponseHandler {
    
    public static void response(Client client,String message){
        client.getDataOutPutStream().println(message);
        
       
        
    }
    
}
