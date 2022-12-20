/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import tictactoe_server.Models.Client;
import tictactoe_server.Models.User;

/**
 *
 * @author DELL
 */
public class Communicator {

    private static Vector<Client> clients = new Vector<>();

    public static void addClient(Client client) throws IOException {
        clients.add(client);
    }

    public static void reset() {
        clients = new Vector<>();
    }

    public static int getUserCount() {
        return clients.size();
    }

    public static void disconnectClosed(){
        clients.removeIf(client -> !client.isStarted());
    }
    public static void disconnectVector(){
        clients.removeIf(client -> client.getRequest() == null);
    }
    
    public static void terminateSockets() throws IOException{
        for (Client client : clients) {
            client.closeSocket();
        }
        clients.clear();
    }

    public static List<User> getUsers(Client user) {
        List<User> users = new ArrayList<>();
        for (Client client : clients) {
            if (!user.getUser().getName().equals(client.getUser().getName())) {
                users.add(client.getUser());
            }
        }
        return users;
    }
    
    public static List<Client> getClients() {
        return clients;
    }

    public static Client getClientByName(String name) {
        Client clientInstance = null;
        for (Client client : clients) {
            if (client.getUser().getName().equals(name)) {
                clientInstance = client;
            }
        }
        return clientInstance;
    }
    
    public static void removeClient(Client client){
        clients.remove(client);
    }
}
