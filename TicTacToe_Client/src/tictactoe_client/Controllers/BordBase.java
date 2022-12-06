package tictactoe_client.Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import services.DataSaver;
import services.Navigation;
import tictactoe_client.Enums.GameState;
import tictactoe_client.Enums.PlayerType;
import tictactoe_client.Enums.Shape;
import tictactoe_client.Models.Game;
import tictactoe_client.Models.Move;
import tictactoe_client.Models.Player;

public class BordBase extends AnchorPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Button btn0;
    protected final Button btn1;
    protected final Button btn2;
    protected final Button btn3;
    protected final Button btn6;
    protected final Button btn5;
    protected final Button btn4;
    protected final Button btn7;
    protected final Button btn8;
    protected final ImageView backArowPlayerName;
    protected final Label player1Name;
    protected final ImageView backArowPlayerName1;
    protected final Label Player2Name;
    protected final Label label;
    protected final Label player1Score;
    protected final Label player2Score;
    protected Shape shap;

    public BordBase() {

        Game game;
        Player player1;
        Player player2;
        shap = Shape.X;
        boolean shapeProp = true;
//        Move  move = new Move("0",Shape.X);
        GameState gameState;

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        btn0 = new Button();
        btn1 = new Button();
        btn2 = new Button();
        btn3 = new Button();
        btn6 = new Button();
        btn5 = new Button();
        btn4 = new Button();
        btn7 = new Button();
        btn8 = new Button();
        backArowPlayerName = new ImageView();
        player1Name = new Label();
        backArowPlayerName1 = new ImageView();
        Player2Name = new Label();
        label = new Label();
        player1Score = new Label();
        player2Score = new Label();

        // define for game
        player1 = new Player(player1Name.getText(), PlayerType.HUMAN, Shape.X);
        player2 = new Player(Player2Name.getText(), PlayerType.HUMAN, Shape.O);
        game = new Game(player1, player2);

        setId("AnchorPane");
        setPrefHeight(409.0);
        setPrefWidth(560.0);
        setStyle("-fx-background-color: #291660;");

        gridPane.setLayoutX(122.0);
        gridPane.setLayoutY(91.0);
        gridPane.setPrefHeight(265.0);
        gridPane.setPrefWidth(389.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(160.66668701171875);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(113.33331298828125);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(198.3333740234375);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(113.66668701171875);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(163.6666259765625);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(162.0);

        rowConstraints.setMaxHeight(153.66665649414062);
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(91.66665649414062);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(167.33334350585938);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(93.33331298828125);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(158.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(94.66668701171875);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        btn0.setMnemonicParsing(false);
        btn0.setPrefHeight(70.0);
        btn0.setPrefWidth(100.0);
        btn0.setStyle("-fx-background-color: #3F2D73;");
        btn0.setTextFill(javafx.scene.paint.Color.WHITE);
        btn0.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                GameState gameState = game.action(new Move("btn0", shap));
                playState(btn0, gameState, event);

            }
        });

        GridPane.setColumnIndex(btn1, 1);
        btn1.setMnemonicParsing(false);
        btn1.setPrefHeight(70.0);
        btn1.setPrefWidth(100.0);
        btn1.setStyle("-fx-background-color: #3F2D73;");
        btn1.setTextFill(javafx.scene.paint.Color.WHITE);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameState gameState = game.action(new Move("btn1", shap));
                playState(btn1, gameState, event);
            }
        });

        GridPane.setColumnIndex(btn2, 2);
        btn2.setMnemonicParsing(false);
        btn2.setPrefHeight(70.0);
        btn2.setPrefWidth(100.0);
        btn2.setStyle("-fx-background-color: #3F2D73;");
        btn2.setTextFill(javafx.scene.paint.Color.WHITE);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameState gameState = game.action(new Move("btn2", shap));
                playState(btn2, gameState, event);
            }
        });

        GridPane.setRowIndex(btn3, 1);
        btn3.setMnemonicParsing(false);
        btn3.setPrefHeight(70.0);
        btn3.setPrefWidth(100.0);
        btn3.setStyle("-fx-background-color: #3F2D73;");
        btn3.setTextFill(javafx.scene.paint.Color.WHITE);
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameState gameState = game.action(new Move("btn3", shap));
                playState(btn3, gameState, event);
            }
        });

        GridPane.setRowIndex(btn6, 2);
        btn6.setMnemonicParsing(false);
        btn6.setPrefHeight(70.0);
        btn6.setPrefWidth(100.0);
        btn6.setStyle("-fx-background-color: #3F2D73;");
        btn6.setTextFill(javafx.scene.paint.Color.WHITE);
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameState gameState = game.action(new Move("btn6", shap));
                playState(btn6, gameState, event);
            }
        });

        GridPane.setColumnIndex(btn5, 2);
        GridPane.setRowIndex(btn5, 1);
        btn5.setMnemonicParsing(false);
        btn5.setPrefHeight(70.0);
        btn5.setPrefWidth(100.0);
        btn5.setStyle("-fx-background-color: #3F2D73;");
        btn5.setTextFill(javafx.scene.paint.Color.WHITE);
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameState gameState = game.action(new Move("btn5", shap));
                playState(btn5, gameState, event);
            }
        });

        GridPane.setColumnIndex(btn4, 1);
        GridPane.setRowIndex(btn4, 1);
        btn4.setMnemonicParsing(false);
        btn4.setPrefHeight(70.0);
        btn4.setPrefWidth(100.0);
        btn4.setStyle("-fx-background-color: #3F2D73;");
        btn4.setTextFill(javafx.scene.paint.Color.WHITE);
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameState gameState = game.action(new Move("btn4", shap));
                playState(btn4, gameState, event);
            }
        });

        GridPane.setColumnIndex(btn7, 1);
        GridPane.setRowIndex(btn7, 2);
        btn7.setMnemonicParsing(false);
        btn7.setPrefHeight(70.0);
        btn7.setPrefWidth(100.0);
        btn7.setStyle("-fx-background-color: #3F2D73;");
        btn7.setTextFill(javafx.scene.paint.Color.WHITE);
        btn7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameState gameState = game.action(new Move("btn7", shap));
                playState(btn7, gameState, event);
            }
        });

        GridPane.setColumnIndex(btn8, 2);
        GridPane.setRowIndex(btn8, 2);
        btn8.setMnemonicParsing(false);
        btn8.setPrefHeight(70.0);
        btn8.setPrefWidth(100.0);
        btn8.setStyle("-fx-background-color: #3F2D73;");
        btn8.setTextFill(javafx.scene.paint.Color.WHITE);
        btn8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert a;
                GameState gameState = game.action(new Move("btn8", shap));
                playState(btn8, gameState, event);
            }
        });

        backArowPlayerName.setFitHeight(26.0);
        backArowPlayerName.setFitWidth(23.0);
        backArowPlayerName.setLayoutX(16.0);
        backArowPlayerName.setLayoutY(16.0);
        backArowPlayerName.setPickOnBounds(true);
        //   backArowPlayerName.setImage(new Image("tictactoe_client/Views/img/backarow.png"));

        player1Name.setLayoutX(115.0);
        player1Name.setLayoutY(14.0);
        player1Name.setPrefHeight(42.0);
//        backArowPlayerName.setImage(new Image("tictactoe_client/Views/img/backarow.png"));
        backArowPlayerName.setOnMouseClicked(new EventHandler() {

            @Override
            public void handle(Event event) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("This is checkmark");
                a.show();
            }
        });
        player1Name.setPrefWidth(119.0);
        player1Name.setText(DataSaver.dataSaverInstance().getPlayer1Data());
        player1Name.setTextFill(javafx.scene.paint.Color.WHITE);
        player1Name.setFont(new Font("System Bold Italic", 25.0));

        backArowPlayerName1.setFitHeight(26.0);
        backArowPlayerName1.setFitWidth(23.0);
        backArowPlayerName1.setLayoutX(16.0);
        backArowPlayerName1.setLayoutY(16.0);
        backArowPlayerName1.setPickOnBounds(true);
        backArowPlayerName1.setImage(new Image("tictactoe_client/Views/img/backarow.png"));
        backArowPlayerName1.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                Navigation.navigateTo(new PlayerInfo(), event);

            }
        });

        Player2Name.setLayoutX(365.0);
        Player2Name.setLayoutY(14.0);
        Player2Name.setPrefHeight(42.0);
        Player2Name.setPrefWidth(119.0);
        Player2Name.setText(DataSaver.dataSaverInstance().getPlayer2Data());
        Player2Name.setTextFill(javafx.scene.paint.Color.WHITE);
        Player2Name.setFont(new Font("System Bold Italic", 25.0));

        label.setLayoutX(263.0);
        label.setLayoutY(12.0);
        label.setPrefHeight(52.0);
        label.setPrefWidth(54.0);
        label.setText("VS");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#ff5c9d"));
        label.setFont(new Font("System Bold", 25.0));

        player1Score.setLayoutX(146.0);
        player1Score.setLayoutY(53.0);
        player1Score.setText("0");
        player1Score.setTextFill(javafx.scene.paint.Color.WHITE);
        player1Score.setFont(new Font("System Bold", 26.0));

        player2Score.setLayoutX(402.0);
        player2Score.setLayoutY(53.0);
        player2Score.setText("0");
        player2Score.setTextFill(javafx.scene.paint.Color.WHITE);
        player2Score.setFont(new Font("System Bold", 26.0));

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(btn0);
        gridPane.getChildren().add(btn1);
        gridPane.getChildren().add(btn2);
        gridPane.getChildren().add(btn3);
        gridPane.getChildren().add(btn6);
        gridPane.getChildren().add(btn5);
        gridPane.getChildren().add(btn4);
        gridPane.getChildren().add(btn7);
        gridPane.getChildren().add(btn8);
        getChildren().add(gridPane);
        getChildren().add(backArowPlayerName);
        getChildren().add(player1Name);
        getChildren().add(backArowPlayerName1);
        getChildren().add(Player2Name);
        getChildren().add(label);
        getChildren().add(player1Score);
        getChildren().add(player2Score);

    }

    //play sate
    public void playState(Button b, GameState gameState, ActionEvent event) {

        switch (gameState) {
            case X_TURN:
                shap = Shape.X;
                b.setText("o");
                b.setStyle("-fx-font-size:34;"
                        + " -fx-text-fill: #9C4F6E;"
                        + "-fx-background-color: #3F2D73;"
                        + "-fx-font-weight: bold ");
                break;
            case O_TURN:
                shap = Shape.O;
                b.setText("x");
                b.setStyle("-fx-font-size:34;"
                        + " -fx-text-fill: #BCB9E5;"
                        + "-fx-background-color: #3F2D73;"
                        + "-fx-font-weight: bold ");
                break;
            case X_WIN:
                Navigation.navigateTo(new WinScreen(), event);
                break;
            case O_WIN:
                Navigation.navigateTo(new WinScreen(), event);
                break;
            default:
                Navigation.navigateTo(new DrawScreen(), event);
                break;
        }
        b.setDisable(true);

    }
}
