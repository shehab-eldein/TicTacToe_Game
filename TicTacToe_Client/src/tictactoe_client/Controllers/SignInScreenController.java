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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private TextField userNameTextField;
    private TextField passwordTextField;
    private Button logInButton;
    @FXML
    private TextField signUpUserNameTextField;
    @FXML
    private TextField signUpPasswordTextField;
    @FXML
    private Button signUpButton;
    @FXML
    private Label logInAccoutnButton;

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

    @FXML
    public void LogInButtonClick(ActionEvent event) {
        //Navigation.navigateTo(new Start(), event);

        if (userNameTextField == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter your Name");
            alert.showAndWait();
        } else if (passwordTextField == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter your password");
            alert.showAndWait();
        } else {
            gameHandler.writeData(userNameTextField.getText() + "-" + passwordTextField.getText() + "-1");

        }

    }

    @FXML
    public void createAcountClick(MouseEvent event) {
        try {
            Navigation.navigateTo(new FXMLLoader().load(tictactoe_client.TicTacToe_Client.class.getResource("Views/SignUPScreen.fxml")), event);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
