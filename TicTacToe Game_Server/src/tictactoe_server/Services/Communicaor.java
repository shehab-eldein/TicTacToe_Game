/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import tictactoe_server.Models.User;
import tictactoe_server.Models.Client;

/**
 *
 * @author DELL
 */
public class Communicaor {

    private static Vector<Client> clients;

    public static void addClient(Client client) {
        clients.add(client);
        //Start thread of client
        client.start();
    }

    public static void removeAll() {
        clients = new Vector<>();
    }

    public static int getUserCount() {
        return clients.size();
    }

    public static void disconnectClosed() {
        for(Client client: clients) {
            if(!client.isConnected(){
               clients.remove(client);
            }
        }
        clients.removeIf(client -> !client.isConnected());
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
