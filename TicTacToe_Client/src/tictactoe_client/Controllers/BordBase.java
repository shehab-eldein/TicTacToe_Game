package tictactoe_client.Controllers;

import tictactoe_client.Models.History;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import javafx.stage.Stage;
import services.Alerts;
import services.DataSaver;
import services.FileManger;
import services.Navigation;
import services.StageSaver;
import tictactoe_client.Enums.GameState;
import tictactoe_client.Enums.PlayerType;
import tictactoe_client.Enums.Shape;
import tictactoe_client.Models.Game;
import tictactoe_client.Models.Move;
import tictactoe_client.Models.Player;

public class BordBase extends AnchorPane {

    protected GridPane gridPane;
    protected ColumnConstraints columnConstraints;
    protected ColumnConstraints columnConstraints0;
    protected ColumnConstraints columnConstraints1;
    protected RowConstraints rowConstraints;
    protected RowConstraints rowConstraints0;
    protected RowConstraints rowConstraints1;
    protected Button btn0;
    protected Button btn1;
    protected Button btn2;
    protected Button btn3;
    protected Button btn6;
    protected Button btn5;
    protected Button btn4;
    protected Button btn7;
    protected Button btn8;
    protected Button record;
    protected ImageView backArowPlayerName;
    protected Label player1Name;
    protected ImageView backArowPlayerName1;
    protected Label Player2Name;
    protected Label label;
    protected Label player1Score;
    protected Label player2Score;
    protected Shape shap;
    private GameHandler gameHandler;
    private Game game;
    private Player player1;
    private Player player2;
    private String recordGame = "";
    Date date;
    DateFormat dateFormat;
    String strDate;
    String fileName;
    History history;

    public BordBase(Player player1, Player player2) {
        DataSaver dataSaver = DataSaver.dataSaverInstance();
        player1Name = new Label(player1.getName());
        Player2Name = new Label(player2.getName());
        DataSaver.dataSaverInstance().setPlayer1Data(player1Name.getText());
        DataSaver.dataSaverInstance().setPlayer2Data(Player2Name.getText());
        this.player1 = player1;
        this.player2 = player2;
        game = new Game(player1, player2);
        init();
        Platform.runLater(() -> {
            try {
                gameHandler = GameHandler.getInstance((message) -> {
                }, (response) -> {
                    if (response.split("-")[0].equals("7")) {
                        Platform.runLater(() -> {
                            Move move = new Move(response.split("-")[1]);
                            GameState gameState = game.action(move);
                            playState(getBordButtonByNumber(move.getIndex()), gameState, (Stage) gridPane.getScene().getWindow());
                        });
                    } else if (response.split("-")[0].equals("408")) {
                        gameHandler.setIsInGame(false);
                        Platform.runLater(() -> {
                            try {
                                Navigation.navigateTo(FXMLLoader.load(tictactoe_client.TicTacToe_Client.class.getResource("Views/ChoosePlayers.fxml")), StageSaver.getStageSeverInstance().getStage());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                    }
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public BordBase() {
        player1Name = new Label(DataSaver.dataSaverInstance().getPlayer1Data());
        Player2Name = new Label(DataSaver.dataSaverInstance().getPlayer2Data());
        player1 = new Player(player1Name.getText(), PlayerType.HUMAN, Shape.X);
        player2 = new Player(Player2Name.getText(), PlayerType.HUMAN, Shape.O);
        game = new Game(player1, player2);
        init();
    }

    private void init() {
        shap = Shape.X;
        this.getStylesheets().add("tictactoe_client/Views/style/style.css");
        date = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("E dd MMM yyyy HH mm ss z");
        strDate = dateFormat.format(date);
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
        record = new Button();

        backArowPlayerName = new ImageView();
        backArowPlayerName1 = new ImageView();
        label = new Label();
        player1Score = new Label();
        player2Score = new Label();

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
        btn0.setId("btn0");
        btn0.setStyle("-fx-background-color: #3F2D73;");
        btn0.setTextFill(javafx.scene.paint.Color.WHITE);
        btn0.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (isPlayerTurn()) {
                    Move move = new Move(0, shap);
                    sendMoveToServer(move);
                    GameState gameState = game.action(move);
                    playState(btn0, gameState, event);

                }
            }
        });

        GridPane.setColumnIndex(btn1, 1);
        btn1.setMnemonicParsing(false);
        btn1.setPrefHeight(70.0);
        btn1.setPrefWidth(100.0);
        btn1.setId("btn1");
        btn1.setStyle("-fx-background-color: #3F2D73;");
        btn1.setTextFill(javafx.scene.paint.Color.WHITE);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isPlayerTurn()) {
                    Move move = new Move(1, shap);
                    sendMoveToServer(move);
                    GameState gameState = game.action(move);
                    playState(btn1, gameState, event);
                }
            }
        });

        GridPane.setColumnIndex(btn2, 2);
        btn2.setMnemonicParsing(false);
        btn2.setPrefHeight(70.0);
        btn2.setPrefWidth(100.0);
        btn2.setId("btn2");
        btn2.setStyle("-fx-background-color: #3F2D73;");
        btn2.setTextFill(javafx.scene.paint.Color.WHITE);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isPlayerTurn()) {
                    Move move = new Move(2, shap);
                    sendMoveToServer(move);
                    GameState gameState = game.action(move);
                    playState(btn2, gameState, event);
                }
            }
        });

        GridPane.setRowIndex(btn3, 1);
        btn3.setMnemonicParsing(false);
        btn3.setPrefHeight(70.0);
        btn3.setPrefWidth(100.0);
        btn3.setId("btn3");
        btn3.setStyle("-fx-background-color: #3F2D73;");
        btn3.setTextFill(javafx.scene.paint.Color.WHITE);
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isPlayerTurn()) {
                    Move move = new Move(3, shap);
                    sendMoveToServer(move);
                    GameState gameState = game.action(move);
                    playState(btn3, gameState, event);
                }
            }
        });

        GridPane.setRowIndex(btn6, 2);
        btn6.setMnemonicParsing(false);
        btn6.setPrefHeight(70.0);
        btn6.setPrefWidth(100.0);
        btn6.setId("btn6");
        btn6.setStyle("-fx-background-color: #3F2D73;");
        btn6.setTextFill(javafx.scene.paint.Color.WHITE);
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isPlayerTurn()) {
                    Move move = new Move(6, shap);
                    sendMoveToServer(move);
                    GameState gameState = game.action(move);
                    playState(btn6, gameState, event);
                }
            }
        });

        GridPane.setColumnIndex(btn5, 2);
        GridPane.setRowIndex(btn5, 1);
        btn5.setMnemonicParsing(false);
        btn5.setPrefHeight(70.0);
        btn5.setPrefWidth(100.0);
        btn5.setId("btn5");
        btn5.setStyle("-fx-background-color: #3F2D73;");
        btn5.setTextFill(javafx.scene.paint.Color.WHITE);
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isPlayerTurn()) {
                    Move move = new Move(5, shap);
                    sendMoveToServer(move);
                    GameState gameState = game.action(move);
                    playState(btn5, gameState, event);
                }
            }
        });

        GridPane.setColumnIndex(btn4, 1);
        GridPane.setRowIndex(btn4, 1);
        btn4.setMnemonicParsing(false);
        btn4.setPrefHeight(70.0);
        btn4.setPrefWidth(100.0);
        btn4.setId("btn4");
        btn4.setStyle("-fx-background-color: #3F2D73;");
        btn4.setTextFill(javafx.scene.paint.Color.WHITE);
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isPlayerTurn()) {
                    Move move = new Move(4, shap);
                    sendMoveToServer(move);
                    GameState gameState = game.action(move);
                    playState(btn4, gameState, event);
                }
            }
        });

        GridPane.setColumnIndex(btn7, 1);
        GridPane.setRowIndex(btn7, 2);
        btn7.setMnemonicParsing(false);
        btn7.setPrefHeight(70.0);
        btn7.setPrefWidth(100.0);
        btn7.setId("btn7");
        btn7.setStyle("-fx-background-color: #3F2D73;");
        btn7.setTextFill(javafx.scene.paint.Color.WHITE);
        btn7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isPlayerTurn()) {
                    Move move = new Move(7, shap);
                    sendMoveToServer(move);
                    GameState gameState = game.action(move);
                    playState(btn7, gameState, event);
                }
            }
        });

        GridPane.setColumnIndex(btn8, 2);
        GridPane.setRowIndex(btn8, 2);
        btn8.setMnemonicParsing(false);
        btn8.setPrefHeight(70.0);
        btn8.setPrefWidth(100.0);
        btn8.setId("btn8");
        btn8.setStyle("-fx-background-color: #3F2D73;");
        btn8.setTextFill(javafx.scene.paint.Color.WHITE);
        btn8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert a;
                if (isPlayerTurn()) {
                    Move move = new Move(8, shap);
                    sendMoveToServer(move);
                    GameState gameState = game.action(move);
                    playState(btn8, gameState, event);
                }
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
        player1Name.setTextFill(javafx.scene.paint.Color.WHITE);
        player1Name.setFont(new Font("System Bold Italic", 25.0));
        player1Name.setStyle("-fx-text-fill:#46C464;");

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

        record.setMnemonicParsing(false);
        record.setPrefHeight(10);

        record.setPrefWidth(80);
        record.setLayoutX(420);
        record.setLayoutY(360);
        if (DataSaver.dataSaverInstance().getModeData() == "Online Mode") {
            record.setVisible(false);
        }
        record.setText("Record");
        record.setStyle("-fx-background-color: #ff5c9d;");
        record.setTextFill(javafx.scene.paint.Color.WHITE);
        record.setFont(new Font("System Bold Italic", 15.0));
        record.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println(recordGame);
//                    System.out.print(strDate);
                    fileName = player1Name.getText() + "" + Player2Name.getText() + "" + strDate;
//                    System.out.println(fileName);
                    FileManger.saveFile(fileName, recordGame);
                    record.setDisable(true);
                } catch (IOException ex) {
                    Logger.getLogger(BordBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Player2Name.setLayoutX(365.0);
        Player2Name.setLayoutY(14.0);
        Player2Name.setPrefHeight(42.0);
        Player2Name.setPrefWidth(119.0);
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
        player1Score.setText("x");
        player1Score.setTextFill(javafx.scene.paint.Color.WHITE);
        player1Score.setFont(new Font("System Bold", 26.0));

        player2Score.setLayoutX(402.0);
        player2Score.setLayoutY(53.0);
        player2Score.setText("o");
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
        if (DataSaver.dataSaverInstance().getModeData() == "Online Mode"){
            backArowPlayerName1.setVisible(false);
        }
        getChildren().add(gridPane);
        getChildren().add(backArowPlayerName);
        getChildren().add(backArowPlayerName1);
        getChildren().add(player1Name);
        getChildren().add(Player2Name);
        getChildren().add(label);
        getChildren().add(player1Score);
        getChildren().add(player2Score);
        getChildren().add(record);
    }

    //play sate
    public void playState(Button b, GameState gameState, ActionEvent event) {
        playState(b, gameState, (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void playState(Button b, GameState gameState, Stage stage) {
        try {
            DataSaver dataSaver = DataSaver.dataSaverInstance();
            Button a = null;
            switch (gameState) {
                case X_TURN:
                    shap = Shape.X;
                    b.setText("o");
                    recordGame = recordGame + b.getId() + "-o-";
                    b.getStyleClass().add("OStyle");
                    player1Name.setStyle("-fx-text-fill:#46C464;");
                    Player2Name.setStyle(" -fx-text-fill:#ffffff;"
                            + "    -fx-opacity: 0.5;");
                    break;
                case O_TURN:
                    shap = Shape.O;
                    b.setText("x");
                    recordGame = recordGame + b.getId() + "-x-";
                    b.getStyleClass().add("XStyle");
                    Player2Name.setStyle("-fx-text-fill:#46C464;");
                    player1Name.setStyle(" -fx-text-fill:#ffffff;"
                            + "    -fx-opacity: 0.5;");
                    if (DataSaver.dataSaverInstance().getModeData() == "Single Mode") {
                        int blocId = game.getAiTurn();
                        playState(getBordButtonByNumber(blocId), game.action(new Move(blocId, shap)), stage);
                    }

                    break;
                case X_WIN:
                    b.setText("x");
                    b.getStyleClass().add("XStyle");
                    recordGame = recordGame + b.getId() + "-x-";
                    FileManger.saveFile(fileName, recordGame);
                    if (DataSaver.dataSaverInstance().getModeData() != "Online Mode") {
                        history = new History(strDate, player1Name.getText(),
                                Player2Name.getText(), player1Name.getText(),
                                Player2Name.getText());
                        FileManger.writeData(history);
                    }
                    dataSaver.setwinnerData(dataSaver.getPlayer1Data());
                    Navigation.navigateTo(new WinScreen(), stage);
                    break;
                case O_WIN:
                    b.setText("x");
                    b.getStyleClass().add("XStyle");
                    recordGame = recordGame + b.getId() + "-o-";
                    FileManger.saveFile(fileName, recordGame);
                    if (DataSaver.dataSaverInstance().getModeData() != "Online Mode") {
                        history = new History(strDate, player1Name.getText(),
                                Player2Name.getText(), Player2Name.getText(),
                                player1Name.getText()
                        );
                        FileManger.writeData(history);
                    }
                    dataSaver.setwinnerData(dataSaver.getPlayer2Data());
                    Navigation.navigateTo(new WinScreen(), stage);
                    break;
                default:
                    if (DataSaver.dataSaverInstance().getModeData() != "Online Mode") {
                        history = new History(strDate, player1Name.getText(),
                                Player2Name.getText(), "draw", "draw"
                        );
                        FileManger.writeData(history);
                    }
                    recordGame = recordGame + b.getId() + "-x-";
                    FileManger.saveFile(fileName, recordGame);
                    Navigation.navigateTo(new DrawScreen(), stage);
                    break;
            }
            b.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(BordBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Button getBordButtonByNumber(int bordNumber) {
        switch (bordNumber) {
            case 0:
                return btn0;
            case 1:
                return btn1;
            case 2:
                return btn2;
            case 3:
                return btn3;
            case 4:
                return btn4;
            case 5:
                return btn5;
            case 6:
                return btn6;
            case 7:
                return btn7;
            case 8:
                return btn8;
        }
        return null;
    }

    private boolean isPlayerTurn() {
        if (DataSaver.dataSaverInstance().getModeData() == "Online Mode") {
            if (shap == Shape.X && player1.getType() == PlayerType.HUMAN) {
                return true;
            } else if (shap == Shape.O && player2.getType() == PlayerType.HUMAN) {
                return true;
            }
            return false;
        }
        return true;
    }

    private void sendMoveToServer(Move move) {
        if (DataSaver.dataSaverInstance().getModeData() == "Online Mode") {
            String opponentName;
            if (player1.getType() == PlayerType.SERVER) {
                opponentName = player1.getName();
            } else {
                opponentName = player2.getName();
            }
            gameHandler.writeData("7-" + opponentName + "-" + move.toString());
        }
    }
}
