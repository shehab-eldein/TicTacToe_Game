/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tictactoe_server.Services.ServerConnector;

/**
 * FXML Controller class
 *
 * @author hamed
 */
public class ServerScreenController implements Initializable {

    @FXML
    private Button startServerButton;
    @FXML
    private Label serverStatus1;
    @FXML
    private Label serverStatusLable;
    @FXML
    private Button stopServerButton;
    @FXML
    private PieChart UsersPieChart;
    private ServerConnector serverConnector;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            serverConnector = new ServerConnector(new ServerSocket(5005), (message) -> {
                System.out.println(message);
            });
        } catch (IOException ex) {
            Logger.getLogger(ServerScreenBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void startServerButtonClicked(ActionEvent event) {
        try {
            serverConnector.connect();
            serverStatusLable.setText("On");
        } catch (IOException ex) {
            serverStatusLable.setText("Off");
        }
    }
    
    
    @FXML
    public void stopServerButtonClicked(ActionEvent event) {
        try {
            serverConnector.disCounnect();
            serverStatusLable.setText("Off");
        } catch (IOException ex) {
            serverStatusLable.setText("On");
        }
    }

}
