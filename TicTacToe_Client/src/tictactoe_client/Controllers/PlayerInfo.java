package tictactoe_client.Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import services.DataSaver;
import services.Navigation;

public  class PlayerInfo extends AnchorPane {

    protected final Label labelPlayer1;
    protected final ImageView imageView;
    protected final Label labelPlayer2;
    protected final TextField player1Name;
    protected final TextField player2Name;
    protected final Label label;
    protected final Button btnStartGame;
    protected final ImageView backArowPlayerName;

    public PlayerInfo() {
        labelPlayer1 = new Label();
        imageView = new ImageView();
        labelPlayer2 = new Label();
        player1Name = new TextField();
        player2Name = new TextField();
        label = new Label();
        btnStartGame = new Button();
        backArowPlayerName = new ImageView();

        setId("AnchorPane");
        setPrefHeight(409.0);
        setPrefWidth(560.0);
        setStyle("-fx-background-color: #291660;");

        labelPlayer1.setLayoutX(53.0);
        labelPlayer1.setLayoutY(152.0);
        labelPlayer1.setMinHeight(16);
        labelPlayer1.setMinWidth(69);
        labelPlayer1.setPrefHeight(29.0);
        labelPlayer1.setPrefWidth(84.0);
        labelPlayer1.setText("Player1:");
        labelPlayer1.setTextFill(javafx.scene.paint.Color.WHITE);
        labelPlayer1.setFont(new Font("System Bold", 20.0));

        imageView.setFitHeight(428.0);
        imageView.setFitWidth(263.0);
        imageView.setLayoutX(308.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image("tictactoe_client/Views/img/start.png"));

        labelPlayer2.setLayoutX(50.0);
        labelPlayer2.setLayoutY(208.0);
        labelPlayer2.setMinHeight(16);
        labelPlayer2.setMinWidth(69);
        labelPlayer2.setPrefHeight(29.0);
        labelPlayer2.setPrefWidth(84.0);
        labelPlayer2.setText("Player2:");
        labelPlayer2.setTextFill(javafx.scene.paint.Color.WHITE);
        labelPlayer2.setFont(new Font("System Bold", 20.0));

        player1Name.setLayoutX(137.0);
        player1Name.setLayoutY(154.0);
        player1Name.setPromptText("Enter Your Name");
        player1Name.setStyle("-fx-border-color: #ff5c9b;");

        player2Name.setLayoutX(134.0);
        player2Name.setLayoutY(210.0);
        player2Name.setPromptText("Enter Your Name");
        player2Name.setStyle("-fx-border-color: #ff5c9b;");

        label.setLayoutX(12.0);
        label.setLayoutY(50.0);
        label.setMinHeight(16);
        label.setMinWidth(69);
        label.setText("Tic Tac Toe");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("System Bold Italic", 38.0));

        btnStartGame.setLayoutX(111.0);
        btnStartGame.setLayoutY(296.0);
        btnStartGame.setMnemonicParsing(false);
        btnStartGame.setPrefHeight(37.0);
        btnStartGame.setPrefWidth(113.0);
        btnStartGame.setStyle("-fx-background-color: #FF5C9D;");
        btnStartGame.setText("Start");
        btnStartGame.setTextFill(javafx.scene.paint.Color.WHITE);
        btnStartGame.setFont(new Font("System Bold Italic", 23.0));
        btnStartGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if (player1Name.getText().isEmpty()) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Player1 name is required");
                    a.show();
                } else if (player2Name.getText().isEmpty()) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Player2 name is required");
                    a.show();
                } else {
                    DataSaver dataSaver = DataSaver.dataSaverInstance();
                    dataSaver.setPlayer1Data(player1Name.getText());
                    dataSaver.setPlayer2Data(player2Name.getText());
                    Navigation.navigateTo(new BordBase(), event);
                }
            }
        });

        backArowPlayerName.setFitHeight(26.0);
        backArowPlayerName.setFitWidth(23.0);
        backArowPlayerName.setLayoutX(6.0);
        backArowPlayerName.setLayoutY(6.0);
        backArowPlayerName.setPickOnBounds(true);
        backArowPlayerName.setImage(new Image("tictactoe_client/Views/img/backarow.png"));
        backArowPlayerName.setOnMouseClicked(new EventHandler() {
            

            @Override
            public void handle(Event event) {
                Navigation.navigateTo(new ChooseMode(), event);
              
            }
        });

        getChildren().add(labelPlayer1);
        getChildren().add(imageView);
        getChildren().add(labelPlayer2);
        getChildren().add(player1Name);
        getChildren().add(player2Name);
        getChildren().add(label);
        getChildren().add(btnStartGame);
        getChildren().add(backArowPlayerName);

    }
}
