/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import services.RequestStringHandler;
import tictactoe_server.Models.Client;
import tictactoe_server.Models.User;
import tictactoe_server.Repositories.UserRepository;

/**
 *
 * @author Esraa
 */
public class RequestHandler {

    public static void queryHandler(Client client) throws IOException {
        switch (client.getRequestCode()) {
            case 0:
                setUserData(client);
                logIn(client);
                break;
            case 1:
                setUserData(client);
                signUP(client);
                break;
            case 2:
                getAllActivePlayers();
                break;
            case 3:
                sendGameRequest(client);
                break;
            case 5:
                sendAcceptGame(client);
                break;
            case 6:
                sendRejectGame(client);
                break;
            case 7:
                sendMove(client);
                break;
            //todo 8 game interupted
            case 9:
                endGame(client);
                break;

        }

    }

    private static void logIn(Client client) throws IOException {
        try {
            if (UserRepository.getByName(client.getUser().getName(), client.getUser().getPass()) != null) {
                ResponseHandler.response(client, "1");
                Communicator.addClient(client);
            } else {
                ResponseHandler.response(client, "0");
            }
        } catch (SQLException ex) {
            ResponseHandler.response(client, "-1");
        }
    }

    private static void signUP(Client client) throws IOException {
        try {
            if (UserRepository.checkUniqeName(client.getUser().getName())) {
                if (UserRepository.create(client.getUser()) != null) {
                    ResponseHandler.response(client, "1");
                    Communicator.addClient(client);
                } else {
                    ResponseHandler.response(client, "0");
                }
            }else{
                ResponseHandler.response(client, "407");
            }

        } catch (SQLException ex) {
            ResponseHandler.response(client, "-1");
        }
    }

    private static void getActivePlayers(Client client) {
        ResponseHandler.response(client, RequestStringHandler.collect(Communicator.getUsers(client)));
    }

    public static void getAllActivePlayers() {
        List<Client> clients = Communicator.getClients();
        for (Client aClient : clients) {
            if (clients.size() == 1) {
                ResponseHandler.response(aClient, "405");
            } else {
                getActivePlayers(aClient);
            }
        }
    }

    private static void setUserData(Client client) {
        client.setUser(splitRequest(client.getRequest()));
    }

    private static User splitRequest(String data) {
        List<String> queryList = Arrays.stream(data.split("\\-")) // split on comma
                .map(str -> str.trim()) // remove white-spaces
                .collect(Collectors.toList()); // collect to List  

        return new User(queryList.get(1), queryList.get(2));
    }

    private static String splitUserName(String data) {
        List<String> queryList = Arrays.stream(data.split("\\-")) // split on comma
                .map(str -> str.trim()) // remove white-spaces
                .collect(Collectors.toList()); // collect to List  

        return queryList.get(1);
    }

    private static void sendGameRequest(Client client) {
        String reques = "4-" + client.getUser().getName();
        Client clientResponsed = Communicator.getClientByName(splitUserName(client.getRequest()));
        if (!clientResponsed.getIsBusy()) {
            ResponseHandler.response(clientResponsed, reques);
        } else {
            ResponseHandler.response(client, "404-" + clientResponsed.getUser().getName());
        }
    }

    private static void sendAcceptGame(Client client) {
        Client client1 = client;
        Client client2 = Communicator.getClientByName(splitUserName(client.getRequest()));
        String request1 = "5-" + client1.getUser().getName() + "-" + client2.getUser().getName() + "-" + "X";
        String request2 = "5-" + client2.getUser().getName() + "-" + client1.getUser().getName() + "-" + "O";
        ResponseHandler.response(client1, request1);
        ResponseHandler.response(client2, request2);
        client1.setIsBusy(true);
        client1.setOpponentName(client2.getUser().getName());
        client2.setIsBusy(true);
        client2.setOpponentName(client1.getUser().getName());
    }

    private static void sendRejectGame(Client client) {
        String reques = "6-" + client.getUser().getName();
        ResponseHandler.response(Communicator.getClientByName(splitUserName(client.getRequest())),
                reques);
    }

    private static void sendMove(Client client) {
        String move = client.getRequest().split("-")[2];
        ResponseHandler.response(Communicator.getClientByName(splitUserName(client.getRequest())),
                "7-" + move);
    }

    private static void endGame(Client client) {
        client.setIsBusy(false);
        client.setOpponentName(null);
    }
}
