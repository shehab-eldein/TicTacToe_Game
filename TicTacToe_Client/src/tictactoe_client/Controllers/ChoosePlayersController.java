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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import services.Alerts;
import services.Navigation;
import services.StageSaver;
import tictactoe_client.Controllers.GameHandler;
import tictactoe_client.Enums.PlayerType;
import tictactoe_client.Enums.Shape;
import tictactoe_client.Models.Player;

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
    String requestResponse;
    Thread reloadUsers;

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
                    System.out.println(response);
                    if (splitRequest(response).get(0).equals("4")) {
                        Platform.runLater(() -> {
                            Alerts.showRequestAlert(splitRequest(response).get(1)
                                    + " wants to play with You", (accept) -> {
                                        System.out.println("accept");
                                        gameHandler.writeData("5-" + splitRequest(response).get(1));
//                                        Platform.runLater(() -> {
//
//                                            Navigation.navigateTo(new BordBase(), StageSaver.getStageSeverInstance().getStage());
//
//                                        });

                                    }, (reject) -> {

                                        System.out.println("reject");
                                        gameHandler.writeData("6-" + splitRequest(response).get(1));

                                    });

                        });

                    } else if (splitRequest(response).get(0).equals("5")) {
                        Platform.runLater(() -> {
                            String myName = splitRequest(response).get(1);
                            String opponentName = splitRequest(response).get(2);
                            String myShape = splitRequest(response).get(3);
                            Player player1;
                            Player player2;
                            if(myShape.equals("X")){
                               player1 = new Player(myName, PlayerType.HUMAN, Shape.X);
                               player2 = new Player(opponentName, PlayerType.SERVER, Shape.O);
                            }else{
                               player1 = new Player(opponentName, PlayerType.SERVER, Shape.X);
                               player2 = new Player(myName, PlayerType.HUMAN, Shape.O);
                            }
                            reloadUsers.interrupt();
                            Navigation.navigateTo(new BordBase(player1, player2), StageSaver.getStageSeverInstance().getStage());

                        });

                    } else if (splitRequest(response).get(0).equals("6")) {
                        Platform.runLater(() -> {
                            Alerts.showAlert(splitRequest(response).get(1) + " reject your request", (message) -> {

                            });
                        });

                    } else if (splitRequest(response).get(0).equals("404")) {
                        Platform.runLater(() -> {
                            Alerts.showAlert(splitRequest(response).get(1) + " is already in another game", (message) -> {

                            });
                        });

                    } else {
                        ObservableList<String> names = FXCollections.observableArrayList(splitRequest(response));
                        onlinePlayersListView.setItems(names);
                    }
                });
                
                reloadUsers = new Thread(() -> {
                    while(true){
                        try{
                            gameHandler.writeData("2");
                            Thread.sleep(10000);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                reloadUsers.start();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

    }

    private List<String> splitRequest(String data) {
        List<String> queryList = Arrays.stream(data.split("\\-"))
                .map(str -> str.trim()) // remove white-spaces// split on comma
                .collect(Collectors.toList()); // collect to List  
        return queryList;
    }

    @FXML
    public void ask() {
        gameHandler.writeData("3-" + onlinePlayersListView.getSelectionModel().getSelectedItem());
    }

}