package tictactoe_client.Controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.DataSaver;
import services.Navigation;

public class ChooseMode extends AnchorPane {

    protected final ImageView imageView;
    protected final Button btnSingleMode;
    protected final Button btnMultiMode;
    protected final Button btnOnlineMode;
    protected final Label label;
     private GameHandler gameHandler;
   
    public ChooseMode() {

        imageView = new ImageView();
        btnSingleMode = new Button();
        btnMultiMode = new Button();
        btnOnlineMode = new Button();
        label = new Label();
        
        setId("AnchorPane");
        setPrefHeight(409.0);
        setPrefWidth(560.0);
        setStyle("-fx-background-color: #291660;");

        imageView.setFitHeight(416.0);
        imageView.setFitWidth(273.0);
        imageView.setLayoutX(287.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image("tictactoe_client/Views/img/start.png"));

        btnSingleMode.setLayoutX(44.0);
        btnSingleMode.setLayoutY(138.0);
        btnSingleMode.setMnemonicParsing(false);
        btnSingleMode.setPrefHeight(46.0);
        btnSingleMode.setPrefWidth(184.0);
        btnSingleMode.setText("Single Mode");
        btnSingleMode.setTextFill(javafx.scene.paint.Color.WHITE);
        btnSingleMode.setFont(new Font("System Bold Italic", 20.0));
        btnSingleMode.getStyleClass().add("changeButtonStyle");
        this.getStylesheets().add("tictactoe_client/Views/style/style.css");

        
        System.out.println();

        btnSingleMode.setOnAction((event)->{
            DataSaver.dataSaverInstance().setModeData(btnSingleMode.getText());
            Navigation.navigateTo(new ChooseLevel(), event);
        });


        btnMultiMode.setLayoutX(44.0);
        btnMultiMode.setLayoutY(208.0);
        btnMultiMode.setMnemonicParsing(false);
        btnMultiMode.setPrefHeight(46.0);
        btnMultiMode.setPrefWidth(184.0);
        btnMultiMode.setText("Multi Mode");
        btnMultiMode.setTextFill(javafx.scene.paint.Color.WHITE);
        btnMultiMode.setFont(new Font("System Bold Italic", 20.0));
        
       
        btnMultiMode.getStyleClass().add("changeButtonStyle");
        this.getStylesheets().add("tictactoe_client/Views/style/style.css");
        System.out.println();
      
        
        btnMultiMode.setOnAction((ActionEvent event) -> {
            DataSaver.dataSaverInstance().setModeData(btnMultiMode.getText());
            Navigation.navigateTo(new PlayerInfo(), event);
        });

        btnOnlineMode.setLayoutX(44.0);
        btnOnlineMode.setLayoutY(275.0);
        btnOnlineMode.setMnemonicParsing(false);
        btnOnlineMode.setPrefHeight(46.0);
        btnOnlineMode.setPrefWidth(184.0);
        btnOnlineMode.setText("Online Mode");
        btnOnlineMode.setTextFill(javafx.scene.paint.Color.WHITE);
        btnOnlineMode.setFont(new Font("System Bold Italic", 20.0));
        btnOnlineMode.getStyleClass().add("changeButtonStyle");
        this.getStylesheets().add("tictactoe_client/Views/style/style.css");
        btnOnlineMode.setOnAction((event)->{
            try {
                DataSaver.dataSaverInstance().setModeData(btnOnlineMode.getText());
                Navigation.navigateTo(new FXMLLoader().load(tictactoe_client.TicTacToe_Client.class.getResource("Views/ConnectingSplashScreen.fxml")), event);
            } catch (IOException ex) {
//                ex.printStackTrace();
            }
            
        });

        label.setLayoutX(31.0);
        label.setLayoutY(45.0);
        label.setPrefHeight(58.0);
        label.setPrefWidth(256.0);
        label.setText("Tic Tac Toe");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Berlin Sans FB Demi Bold", 32.0));

        getChildren().add(imageView);
        getChildren().add(btnSingleMode);
        getChildren().add(btnMultiMode);
        getChildren().add(btnOnlineMode);
        getChildren().add(label);
        
        
       

    }
}
