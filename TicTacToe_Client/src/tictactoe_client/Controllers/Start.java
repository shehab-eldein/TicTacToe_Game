package tictactoe_client.Controllers;

import java.io.IOException;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import services.ErrorMessageSender;

import services.Navigation;

public class Start extends AnchorPane {

    protected final ImageView imageView;
    protected final Label label;
    protected final Button btnStart;
    protected final Label label0;
    

    public Start() {

        imageView = new ImageView();
        label = new Label();
        btnStart = new Button();
        label0 = new Label();

        setId("AnchorPane");
        setPrefHeight(409.0);
        setPrefWidth(560.0);
        setStyle("-fx-background-color: #291660;");

        imageView.setFitHeight(418.0);
        imageView.setFitWidth(254.0);
        imageView.setLayoutX(307.0);
        imageView.setPickOnBounds(true);
        // imageView.setImage(new Image(getClass().getResource("../start.png").toExternalForm()));
        imageView.setImage(new Image("tictactoe_client/Views/img/start.png"));
        label.setLayoutX(21.0);
        label.setLayoutY(30.0);
        label.setMinHeight(16);
        label.setMinWidth(69);
        label.setText("Tic Tac Toe");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Berlin Sans FB Demi Bold", 49.0));

        btnStart.setLayoutX(87.0);
        btnStart.setLayoutY(260.0);
        btnStart.setMnemonicParsing(false);
        btnStart.setPrefHeight(46.0);
        btnStart.setPrefWidth(184.0);
        btnStart.setText("Start");
        btnStart.setTextFill(javafx.scene.paint.Color.WHITE);
        btnStart.setFont(new Font("System Bold Italic", 23.0));
        btnStart.getStyleClass().add("changeButtonStyle");
        this.getStylesheets().add("tictactoe_client/Views/style/style.css");

        btnStart.setOnAction((ActionEvent event) -> {
            Navigation.navigateTo(new ChooseMode(), event);
        });

        label0.setLayoutX(72.0);
        label0.setLayoutY(82.0);
        label0.setPrefHeight(65.0);
        label0.setPrefWidth(206.0);
        label0.setText("Welcome To Our Game");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setFont(new Font("Bell MT Italic", 20.0));

        getChildren().add(imageView);
        getChildren().add(label);
        getChildren().add(btnStart);
        getChildren().add(label0);

    }
}
