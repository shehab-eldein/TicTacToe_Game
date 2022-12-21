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
import static javafx.application.Application.launch;
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

    private GameHandler gameHandler;

    @Override
    public void start(Stage stage) throws Exception {
        Navigation.navigateTo(new Start(), stage);
        StageSaver stageSever = StageSaver.getStageSeverInstance();
        stageSever.setStage(stage);
        DataSaver.dataSaverInstance().setModeData("normal");

        stage.setOnCloseRequest(event -> {
            if (DataSaver.dataSaverInstance().getModeData().equals("Online Mode")) {
                try {
                    gameHandler = GameHandler.getInstance((error) -> {
                    }, (response) -> {
                    });
                    if (gameHandler.getIsRunning() && gameHandler.getIsInGame()) {
                        Alerts.showAlert("you will leave the game", (accept) -> {
                            try {
                                gameHandler.writeData("8");
                                Thread.sleep(1500);
                                gameHandler.disconnect();
                                stage.close();
                                event.consume();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            } catch (IOException ex) {
                                Logger.getLogger(TicTacToe_Client.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    } else if (gameHandler.getIsRunning()) {
                        Alerts.showRequestAlert("do you want to close the connection", (accept) -> {

                            try {
                                gameHandler.disconnect();
                                stage.close();
                            } catch (IOException ex) {
                            }
                        }, (reject) -> {

                        });
                    } else {
                        Alerts.showAlert("do you want to close the application", (accept) -> {
                            stage.close();
                        });
                    }

                } catch (IOException ex) {
                    Alerts.showAlert("do you want to close the application", (accept) -> {
                        stage.close();
                    });
                }
            } else {
                Alerts.showAlert("do you want to close the application", (accept) -> {
                    stage.close();
                });
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
