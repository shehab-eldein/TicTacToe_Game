/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tictactoe_server.Repositories.UserRepository;
import tictactoe_server.Services.Communicator;
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
    private PieChart UsersPieChart;
    private ServerConnector serverConnector;
    private PieChart.Data online;
    private PieChart.Data offline;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            serverConnector = ServerConnector.getServerConnectorInstance(5005, (message) -> {
            }, (count) -> {
                System.out.println("count ");
                Platform.runLater(() -> {
                    try {

                        online = new PieChart.Data("online", Communicator.getUserCount());
                        offline = new PieChart.Data("offline", UserRepository.getUsersCount() - Communicator.getUserCount());
                        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(online, offline);
                        UsersPieChart.setData(pieChartData);
                        //System.out.println("count ");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                });

            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void startServerButtonClicked(ActionEvent event) {
        try {
            if (startServerButton.getText().equals("Start")) {
                serverConnector.connect();
                serverStatusLable.setText("On");
                startServerButton.setText("Stop");
                UsersPieChart.setVisible(true);

            } else {
                serverConnector.disCounnect();
                serverStatusLable.setText("Off");
                startServerButton.setText("Start");
                UsersPieChart.setVisible(false);
            }
        } catch (IOException ex) {
            serverStatusLable.setText("Off");
        }
    }

}
