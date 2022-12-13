/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Controllers;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.Navigation;
import tictactoe_client.Controllers.GameHandler;
import tictactoe_client.Controllers.Start;

/**
 * FXML Controller class
 *
 * @author hamed
 */
public class SignInScreenController implements Initializable {

    private GameHandler gameHandler;
    private Socket mysocket;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button logInButton;
    @FXML
    private Label createAccountButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            gameHandler = new GameHandler((String message) -> {
                Alerts.showAlert("The server is down!", (e) -> {
                    //Navigation.navigateTo(new ChooseMode(), (Stage) logInButton.getScene().getWindow());
                });
            });
        } catch (IOException ex) {
            Alerts.showAlert("The server is down!", (e) -> {
                Navigation.navigateTo(new ChooseMode(), (Stage) logInButton.getScene().getWindow());
            });
        }
    }

//    @FXML
//    public void LogInButtonClick(ActionEvent event) {
//        //Navigation.navigateTo(new Start(), event);
//        gameHandler.writeData("1-hamed-123456-0");
//
//    }
    
    @FXML
    public void printFromTextField(ActionEvent event){
        String name = userNameTextField.getText();
        String password = passwordTextField.getText();
        System.out.println(name);
        System.out.println(name);
    }
}
