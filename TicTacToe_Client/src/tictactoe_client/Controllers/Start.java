package tictactoe_client.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import services.Navigation;

public abstract class Start extends AnchorPane {

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
//        imageView.setImage(new Image(getClass().getResource("../start.png").toExternalForm()));
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
        btnStart.setPrefHeight(42.0);
        btnStart.setPrefWidth(105.0);
        btnStart.setStyle("-fx-background-color: #FF5C9D;");
        btnStart.setText("Start");
        btnStart.setTextFill(javafx.scene.paint.Color.WHITE);
        btnStart.setFont(new Font("System Bold Italic", 23.0));
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            //    Parent root = new ChooseMode();
            Parent root = new ChooseMode() {
            };
            Navigation nav = new Navigation();

            @Override
            public void handle(ActionEvent event) {
                nav.navigateTo(root, event);
            }
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
