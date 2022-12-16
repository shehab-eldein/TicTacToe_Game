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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private Stage stage;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button logInButton;
    @FXML
    private Label createAccountButton;
    @FXML
    private Label incorrectLable;
    @FXML
    private Label connectingLable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            gameHandler = GameHandler.getInstance((message) -> {
                Alerts.showAlert("The server is down!", (e) -> {
//                    //Navigation.navigateTo(new ChooseMode(), (Stage) logInButton.getScene().getWindow());
                });
            }, (response) -> {
                if (response.equals("1")) {
                    Platform.runLater(() -> {
                        try {
                            Navigation.navigateTo(FXMLLoader.load(tictactoe_client.TicTacToe_Client.class.getResource("Views/ChoosePlayers.fxml")), stage);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    logInButton.setDisable(false);
                } else if (response.equals("-1")) {
                    connectingLable.setVisible(false);
                    Platform.runLater(() -> {
                        Alerts.showAlert("The server is down!", (e) -> {
                            userNameTextField.clear();
                            passwordTextField.clear();
                            logInButton.setDisable(false);
                        });
                    });

                } else if (response.equals("0")) {
                    connectingLable.setVisible(true);
                    incorrectLable.setVisible(true);
                    userNameTextField.clear();
                    passwordTextField.clear();
                    logInButton.setDisable(false);
                }
            });
        } catch (IOException ex) {
            Alerts.showAlert("The server is down!", (e) -> {
                Navigation.navigateTo(new ChooseMode(), stage);
            });
        }
    }

    @FXML
    public void LogInButtonClick(ActionEvent event) {
        if (stage == null) {
            try {
                gameHandler.connect();
            } catch (IOException ex) {
            }
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        incorrectLable.setVisible(false);
        if (userNameTextField.getText().isEmpty()) {
            Alerts.showAlert("please Enter your user name");
        } else if (passwordTextField.getText().isEmpty()) {
            Alerts.showAlert("please Enter your password");

        } else {
            gameHandler.writeData("0-" + userNameTextField.getText() + "-"
                    + passwordTextField.getText() + "-0");
            logInButton.setDisable(true);
            connectingLable.setVisible(true);

        }

    }

    @FXML
    public void logInAcountClick(MouseEvent event) {
        try {
            Navigation.navigateTo(FXMLLoader.load(tictactoe_client.TicTacToe_Client.class.getResource("Views/SignUPScreen.fxml")), event);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
