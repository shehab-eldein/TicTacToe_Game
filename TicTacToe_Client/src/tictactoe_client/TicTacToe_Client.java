/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Alerts;
import services.DataSaver;
import services.Navigation;
import services.StageSaver;
import tictactoe_client.Controllers.GameHandler;
import tictactoe_client.Controllers.Start;

/**
 *
 * @author DELL
 */
public class TicTacToe_Client extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Navigation.navigateTo(new Start(), stage);
        StageSaver stageSever = StageSaver.getStageSeverInstance();
        stageSever.setStage(stage);

        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                GameHandler gameHandler = GameHandler.getInstance((error) -> {
                }, (response) -> {
                });
                if (gameHandler.getIsRunning() && gameHandler.getIsInGame()) {
                    Alerts.showAlert("you will leave the game", (accept) -> {
                        try {
                            gameHandler.writeData("8-" + DataSaver.dataSaverInstance().getPlayer2Data());
                            gameHandler.disconnect();
                            stage.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                } else {
                    Alerts.showRequestAlert("do you want to close the application", (accept) -> {
                        try {
                            gameHandler.disconnect();
                            stage.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }, (reject) -> {
                        event.consume();
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {

    }

}
