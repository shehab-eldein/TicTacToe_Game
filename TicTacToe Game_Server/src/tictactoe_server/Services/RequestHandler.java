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
                getActivePlayers(client);
                break;
            case 3:
                sendGameRequest(client);
                break;
            case 5:
                sendRequestGameStatus(client, "5");
                break;
            case -5:
                sendRequestGameStatus(client, "-5");
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
            if (UserRepository.create(client.getUser()) != null) {
                ResponseHandler.response(client, "1");
                Communicator.addClient(client);
            } else {
                ResponseHandler.response(client, "0");
            }
        } catch (SQLException ex) {
            ResponseHandler.response(client, "-1");
        }
    }

    private static void getActivePlayers(Client client) {
        ResponseHandler.response(client, RequestStringHandler.collect(Communicator.getUsers(client)));
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
        ResponseHandler.response(Communicator.getClientByName(splitUserName(client.getRequest())),
                reques);
    }

    private static void sendRequestGameStatus(Client client, String code) {
        String reques = code + "-" + client.getUser().getName();
        ResponseHandler.response(Communicator.getClientByName(splitUserName(client.getRequest())),
                reques);
    }
}
