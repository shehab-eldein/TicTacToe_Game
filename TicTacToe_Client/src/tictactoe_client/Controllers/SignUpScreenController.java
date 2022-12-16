/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.Alerts;
import services.Navigation;

/**
 * FXML Controller class
 *
 * @author hamed
 */
public class SignUpScreenController implements Initializable {

    private Stage stage;
    private GameHandler gameHandler;
    @FXML
    private TextField signUpUserNameTextField;
    @FXML
    private TextField signUpPasswordTextField;
    @FXML
    private Button signUpButton;
    @FXML
    private Label logInAccoutnButton;
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
                    Platform.runLater(() -> Navigation.navigateTo(new BordBase(), stage));
                    signUpButton.setDisable(false);
                } else if (response.equals("-1")) {
                    connectingLable.setVisible(false);
                    Platform.runLater(() -> {
                        Alerts.showAlert("The server is down!", (e) -> {
                            signUpUserNameTextField.clear();
                            signUpPasswordTextField.clear();
                            signUpButton.setDisable(false);
                        });
                    });

                } else if (response.equals("0")) {
                    connectingLable.setVisible(true);
                    incorrectLable.setVisible(true);
                    signUpUserNameTextField.clear();
                    signUpPasswordTextField.clear();
                    signUpButton.setDisable(false);
                }
            });
        } catch (IOException ex) {
            Alerts.showAlert("The server is down!", (e) -> {
                Navigation.navigateTo(new ChooseMode(), stage);
            });
        }
    }

    @FXML
    private void SignUPButtonClick(ActionEvent event) {
        if (stage == null) {
            try {
                gameHandler.connect();
            } catch (IOException ex) {
            }
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        incorrectLable.setVisible(false);
        if (signUpUserNameTextField.getText().isEmpty()) {
            Alerts.showAlert("Please Enter your user name");
        } else if (signUpPasswordTextField.getText().isEmpty()) {
            Alerts.showAlert("Please Enter your password");
        } else {
            gameHandler.writeData("0-" + signUpUserNameTextField.getText()
                    + "-" + signUpPasswordTextField.getText() + "-1");
            signUpButton.setDisable(true);
            connectingLable.setVisible(true);

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
