/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tictactoe_client.Controllers.GameHandler;

/**
 * FXML Controller class
 *
 * @author hamed
 */
public class ChoosePlayersController implements Initializable {

    @FXML
    private Label backButton;
    @FXML
    private ListView<String> onlinePlayersListView;
    @FXML
    private Button askUserButton;
    @FXML
    private Button playButton;

    /**
     * Initializes the controller class.
     */
    private GameHandler gameHandler;
    private static List<String> clients = new ArrayList<>();
    private String usersName = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
        Platform.runLater(() -> {
            try {
                gameHandler = GameHandler.getInstance((message) -> {
                    System.out.println(message);
                }, (response) -> {
                    usersName = response;
                });
                gameHandler.connect();
                gameHandler.writeData("2");

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            //clients = splitRequest("Rafeef-Esam-Ali-Almahy");
            clients = splitRequest(usersName);
            System.err.println(clients.get(0));

            ObservableList<String> names = FXCollections.observableArrayList(clients);
            onlinePlayersListView.setItems(names);
        });

    }

    private List splitRequest(String data) {
        List<String> queryList = Arrays.stream(data.split("\\-"))
                .map(str -> str.trim()) // remove white-spaces// split on comma
                .collect(Collectors.toList()); // collect to List  
        return queryList;
    }

    public void ask() {
        System.out.println(onlinePlayersListView.getSelectionModel().getSelectedItem());
    }
}
