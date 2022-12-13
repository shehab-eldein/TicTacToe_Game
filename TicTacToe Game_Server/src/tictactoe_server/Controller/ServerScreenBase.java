package tictactoe_server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import tictactoe_server.Services.ServerConnector;

public class ServerScreenBase extends AnchorPane {

    protected final ImageView imageView;
    protected final Button btnStartServer;
    protected final Label label;
    protected final Label label0;
    protected final Label serverStatus;
    protected final ScrollPane scrollList;
    protected final ListView listView;
    protected final Button btnStopServer;
    private ServerConnector serverConnector;

    public ServerScreenBase() {

        imageView = new ImageView();
        btnStartServer = new Button();
        label = new Label();
        label0 = new Label();
        serverStatus = new Label();
        scrollList = new ScrollPane();
        listView = new ListView();
        btnStopServer = new Button();

        setId("AnchorPane");
        setPrefHeight(409.0);
        setPrefWidth(560.0);
        setStyle("-fx-background-color: #291660;");

        imageView.setFitHeight(416.0);
        imageView.setFitWidth(273.0);
        imageView.setLayoutX(287.0);
        imageView.setPickOnBounds(true);
//        imageView.setImage(new Image(getClass().getResource("../../../TicTacToe_Client/src/tictactoe_client/Views/img/start.png").toExternalForm()));
        try {
            serverConnector =  new ServerConnector(new ServerSocket(5005),(message)->{
                System.out.println(message);
            });
        } catch (IOException ex) {
            Logger.getLogger(ServerScreenBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnStartServer.setLayoutX(187.0);
        btnStartServer.setLayoutY(346.0);
        btnStartServer.setMnemonicParsing(false);
        btnStartServer.setPrefHeight(46.0);
        btnStartServer.setPrefWidth(109.0);
        btnStartServer.setStyle("-fx-background-color: #291660; -fx-border-color: #ff5c9d;");
        btnStartServer.setText("Start");
        btnStartServer.setTextFill(javafx.scene.paint.Color.WHITE);
        btnStartServer.setFont(new Font("System Bold Italic", 20.0));
        btnStartServer.setOnAction((event)->{
            try {
                serverConnector.connect();
            } catch (IOException ex) {
            }
        });
        

        label.setLayoutX(24.0);
        label.setLayoutY(23.0);
        label.setPrefHeight(58.0);
        label.setPrefWidth(256.0);
        label.setText("Tic Tac Toe");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Berlin Sans FB Demi Bold", 32.0));

        label0.setLayoutX(53.0);
        label0.setLayoutY(74.0);
        label0.setPrefHeight(0.0);
        label0.setPrefWidth(124.0);
        label0.setText("Server Status");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setFont(new Font("System Bold Italic", 17.0));

        serverStatus.setId("serverStatus");
        serverStatus.setLayoutX(162.0);
        serverStatus.setLayoutY(72.0);
        serverStatus.setPrefHeight(31.0);
        serverStatus.setPrefWidth(84.0);
        serverStatus.setText("     off");
        serverStatus.setTextFill(javafx.scene.paint.Color.valueOf("#ff5c9d"));
        serverStatus.setFont(new Font("System Italic", 17.0));

        scrollList.setLayoutX(20.0);
        scrollList.setLayoutY(110.0);
        scrollList.setPrefHeight(229.0);
        scrollList.setPrefWidth(256.0);

        listView.setPrefHeight(229.0);
        listView.setPrefWidth(258.0);
        scrollList.setContent(listView);

        btnStopServer.setLayoutX(39.0);
        btnStopServer.setLayoutY(346.0);
        btnStopServer.setMnemonicParsing(false);
        btnStopServer.setPrefHeight(46.0);
        btnStopServer.setPrefWidth(109.0);
        btnStopServer.setStyle("-fx-background-color: #291660; -fx-border-color: #ff5c9d;");
        btnStopServer.setText("Stop");
        btnStopServer.setTextFill(javafx.scene.paint.Color.WHITE);
        btnStopServer.setFont(new Font("System Bold Italic", 20.0));
        btnStopServer.setOnAction((event) -> {
            try {
                serverConnector.disCounnect();
            } catch (IOException ex) {
                Logger.getLogger(ServerScreenBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        

        getChildren().add(imageView);
        getChildren().add(btnStartServer);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(serverStatus);
        getChildren().add(scrollList);
        getChildren().add(btnStopServer);

    }
}
