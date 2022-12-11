/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Navigation;
import tictactoe_client.Controllers.Start;

/**
 *
 * @author DELL
 */
public class TicTacToe_Client extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Navigation.navigateTo(new Start(), stage);
   
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
