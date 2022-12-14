/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.Alerts;
import services.Navigation;

/**
 * FXML Controller class
 *
 * @author hamed
 */
public class SignUpScreenController implements Initializable {

    private GameHandler gameHandler;
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
            gameHandler = new GameHandler((String message) -> {
                Alerts.showAlert("The server is down!", (e) -> {
                    //Navigation.navigateTo(new ChooseMode(), (Stage) logInButton.getScene().getWindow());
                });
            });

        } catch (IOException ex) {
            Alerts.showAlert("The server is down!", (e) -> {
                //Navigation.navigateTo(new ChooseMode(), (Stage) signUpButton.getScene().getWindow());
            });
        }
    }

    @FXML
    private void LogInButtonClick(ActionEvent event) {
        if (signUpUserNameTextField.getText().isEmpty()) {
            Alerts.showAlert("Please Enter your user name");
        } else if (signUpPasswordTextField.getText().isEmpty()) {
            Alerts.showAlert("Please Enter your password");
        } else {
            gameHandler.writeData(signUpUserNameTextField.getText()
                    + "-" + signUpPasswordTextField.getText() + "-0");
        }
    }

    @FXML
    public void logInAcountClick(MouseEvent event) {
        try {
            Navigation.navigateTo(new FXMLLoader().load(tictactoe_client.TicTacToe_Client.class.getResource("Views/SignInScreen.fxml")), event);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
