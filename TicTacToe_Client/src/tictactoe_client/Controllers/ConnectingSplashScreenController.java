/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import services.Alerts;
import services.Navigation;
import services.StageSaver;

/**
 * FXML Controller class
 *
 * @author hamed
 */
public class ConnectingSplashScreenController implements Initializable {

    @FXML
    private Label percentageLable;
    private int percentage = 0;
    private GameHandler gameHandler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Timer timer = new Timer();
        try {
            gameHandler = GameHandler.getInstance((error) -> {
            }, (response) -> {
            });
            gameHandler.connect();
        } catch (IOException ex) {
            timer.cancel();
            Alerts.showAlert("The server is down!", (e) -> {
                Navigation.navigateTo(new ChooseMode(), StageSaver.getStageSeverInstance().getStage());
            });
        }
        // TODO
        TimerTask task = new TimerTask() {

            public void run() {
                percentage += 1;
                Platform.runLater(() -> {
                    percentageLable.setText(percentage + "%");
                    if (percentage <= 20) {

                    } else if (percentage == 100) {
                        timer.cancel();
                        try {
                            Navigation.navigateTo(new FXMLLoader().load(tictactoe_client.TicTacToe_Client.class.getResource("Views/SignInScreen.fxml")), StageSaver.getStageSeverInstance().getStage());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

            }
        };
        timer.schedule(task, 0, 70);

    }

}
