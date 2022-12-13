/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tictactoe_server.Models.Client;
import tictactoe_server.Models.User;


/**
 *
 * @author DELL
 */
public class Communicator {
    
      private static List<Client> clients = new ArrayList<>();

    public static void addClient (Client client) throws IOException {
        clients.add(client);
        client.StartSocket();
    }

    public static void reset() {
        clients = new ArrayList<>();
    }

    public static int getUserCount() {
        return clients.size();
    }

    public static void disconnectClosed() {
      for(Client client: clients) {
            if(!client.getIsStarted()){
                clients.remove(client);
                
            }
        }
        clients.removeIf(client -> !client.getIsStarted());
    }

    public static List<User> getUsers() {
    List<User> users = new ArrayList<>();
        for(Client client: clients) {
            users.add(client.getUser());
        }
        return users;
       // return clients.stream().map(Client::getUser).collect(Collectors.toList());
    }
}
