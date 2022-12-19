package tictactoe_client.Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import services.Navigation;

public class ChooseLevel extends AnchorPane {

    protected final ImageView imageView;
    protected final Button btnEasy;
    protected final Button btnMedium;
    protected final Button btnHard;
    protected final Label label;
    protected final ImageView backArowPlayerName;

    public ChooseLevel() {
        this.getStylesheets().add("tictactoe_client/Views/style/style.css");

        imageView = new ImageView();
        btnEasy = new Button();
        btnMedium = new Button();
        btnHard = new Button();
        label = new Label();
        backArowPlayerName = new ImageView();

        setId("AnchorPane");
        setPrefHeight(409.0);
        setPrefWidth(560.0);
        setStyle("-fx-background-color: #291660;");

        imageView.setFitHeight(416.0);
        imageView.setFitWidth(273.0);
        imageView.setLayoutX(287.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image("tictactoe_client/Views/img/start.png"));

        btnEasy.setLayoutX(44.0);
        btnEasy.setLayoutY(138.0);
        btnEasy.setMnemonicParsing(false);
        btnEasy.setPrefHeight(46.0);
        btnEasy.setPrefWidth(184.0);
//        btnEasy.setStyle("-fx-background-color: #291660; -fx-border-color: #ff5c9d;");
        btnEasy.setText("Easy");
        btnEasy.setTextFill(javafx.scene.paint.Color.WHITE);
        btnEasy.setFont(new Font("System Bold Italic", 20.0));
        btnEasy.getStyleClass().add("changeButtonStyle");
        btnEasy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Navigation.navigateTo(new PlayerInfo(), event);

            }
        });

        btnMedium.setLayoutX(44.0);
        btnMedium.setLayoutY(208.0);
        btnMedium.setMnemonicParsing(false);
        btnMedium.setPrefHeight(46.0);
        btnMedium.setPrefWidth(184.0);
//        btnMedium.setStyle("-fx-background-color: #291660; -fx-border-color: #ff5c9d;");
        btnMedium.setText("Medium");
        btnMedium.setTextFill(javafx.scene.paint.Color.WHITE);
        btnMedium.setFont(new Font("System Bold Italic", 20.0));
        btnMedium.getStyleClass().add("changeButtonStyle");

        btnHard.setLayoutX(44.0);
        btnHard.setLayoutY(275.0);
        btnHard.setMnemonicParsing(false);
        btnHard.setPrefHeight(46.0);
        btnHard.setPrefWidth(184.0);
//        btnHard.setStyle("-fx-background-color: #291660; -fx-border-color: #ff5c9d;");
        btnHard.setText("Hard");
        btnHard.setTextFill(javafx.scene.paint.Color.WHITE);
        btnHard.setFont(new Font("System Bold Italic", 20.0));
        btnHard.getStyleClass().add("changeButtonStyle");

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

        label.setLayoutX(31.0);
        label.setLayoutY(45.0);
        label.setPrefHeight(58.0);
        label.setPrefWidth(256.0);
        label.setText("Tic Tac Toe");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Berlin Sans FB Demi Bold", 32.0));

        getChildren().add(imageView);
        getChildren().add(btnEasy);
        getChildren().add(btnMedium);
        getChildren().add(btnHard);
        getChildren().add(label);
        getChildren().add(backArowPlayerName);

    }
}
