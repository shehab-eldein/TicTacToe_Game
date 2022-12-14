/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import services.Navigation;
import tictactoe_server.Services.ServerConnector;

/**
 *
 * @author DELL
 */
public class TicTacToe_Server extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Navigation.navigateTo(new FXMLLoader().load(getClass().getClassLoader().getResource("Views/ServerScreen.fxml")), stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        ServerConnector sever = ServerConnector.getServerConnectorInstance(5005, (message) -> {
        },(count)->{});
        if (sever.getIsServerConected()) {
            sever.disCounnect();
        }
    }

}
