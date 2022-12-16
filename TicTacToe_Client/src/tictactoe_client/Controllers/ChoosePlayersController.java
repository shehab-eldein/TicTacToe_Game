/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
    private GameHandler gameHandler;
    private static List<String> clients = new ArrayList<>();
    String usersName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //        try {
        // TODO
        /*  gameHandler.writeData("2");
            gameHandler = GameHandler.getInstance((message) -> {
            }, (response) -> {
                usersName = response;
            });
            clients = splitRequest(usersName); */
        clients = splitRequest("Rafeef-Esam-Ali-Almahy");
        ObservableList<String> names = FXCollections.observableArrayList(
                clients);
        onlinePlayersListView.setItems(names);
//        } catch (IOException ex) {
//            Logger.getLogger(ChoosePlayersController.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    private List splitRequest(String data) {
        List<String> queryList = Arrays.stream(data.split("\\-")) // split on comma
                .map(str -> str.trim()) // remove white-spaces
                .collect(Collectors.toList()); // collect to List  
        return queryList;
    }

    private void ask() {
        System.out.println(onlinePlayersListView.getSelectionModel().getSelectedItem());
    }

}
