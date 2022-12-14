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
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static void queryHandler(Client client, Consumer onlineClients) throws IOException {
        switch (client.getRequestCode()) {
            case 0:
                setUserData(client);
                logIn(client);
                onlineClients.accept("login");
                break;
            case 1:
                setUserData(client);
                signUP(client);
                onlineClients.accept("sign up");
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
            case 8:
                sendEndGameRequest(client);
                onlineClients.accept("remove");
                break;
            case 9:
                sendPlayAgainRequest(client);
                break;
            case 11:
                ResponseHandler.response(client, "411");
                Communicator.removeClient(client);
                onlineClients.accept("remove");
                break;

        }

    }

    private static void logIn(Client client) throws IOException {
        try {
            if (Communicator.getClientByName(client.getUser().getName()) == null) {
                if (UserRepository.getByName(client.getUser().getName(), client.getUser().getPass()) != null) {
                    ResponseHandler.response(client, "1");
                    Communicator.addClient(client);
                } else {
                    ResponseHandler.response(client, "0");
                }
            } else {
                ResponseHandler.response(client, "409");
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
            } else {
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
            if (clients.size() <= 1) {
                ResponseHandler.response(aClient, "405");
            } else {
                getActivePlayers(aClient);
            }
        }
    }

    private static void setUserData(Client client) {
        client.setUser(new User(client.getRequest().split("\\-")[1], client.getRequest().split("\\-")[2]));
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

    private static void sendEndGameRequest(Client client) {
        Client clientResponsed = Communicator.getClientByName(client.getOpponentName());
        ResponseHandler.response(clientResponsed, "408-" + client.getUser().getName());
        endGame(clientResponsed);
        endGame(client);
    }

    private static void sendPlayAgainRequest(Client client) {
        String response = "409-" + client.getUser().getName();
        Client clientResponsed = Communicator.getClientByName(client.getOpponentName());
        ResponseHandler.response(clientResponsed, response);
        endGame(clientResponsed);
        endGame(client);
    }

}
