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
import javafx.scene.control.Alert;
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
 * @author DELL
 */
public class SignUPScreenController implements Initializable {

    @FXML
    private TextField signUpUserNameTextField;
    @FXML
    private TextField signUpPasswordTextField;
    @FXML
    private Button signUpButton;
    @FXML
    private Label logInAccoutnButton;
    private GameHandler gameHandler;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    

    @FXML
    private void LogInButtonClick(ActionEvent event) {
        
        
    }
    
    
}
