/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Controller;

import java.net.ServerSocket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe_server.Services.ServerConnector;

/**
 *
 * @author DELL
 */
public class TicTacToe_Server extends Application {
    private ServerConnector serverConnector;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new ServerScreenBase();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        serverConnector = new ServerConnector(new ServerSocket(5005),(message)->{
            System.out.println(message);
        });
        serverConnector.connect();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception 
    {
        super.stop();
        serverConnector.disCounnect();
    }
    
}
