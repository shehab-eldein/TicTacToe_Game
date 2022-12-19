package tictactoe_client.Controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import services.Alerts;
import services.DataSaver;
import services.FileManger;
import services.Navigation;

public class PlayerInfo extends AnchorPane {

    protected final Label labelPlayer1;
    protected final ImageView imageView;
    protected final Label labelPlayer2;
    protected final TextField player1Name;
    protected final TextField player2Name;
    protected final Label label;
    protected final Button btnStartGame;
    protected final ImageView backArowPlayerName;
    protected final ListView listView;
    protected final Label label0;
    List fileNames;

    public PlayerInfo() {
        labelPlayer1 = new Label();
        imageView = new ImageView();
        labelPlayer2 = new Label();
        player1Name = new TextField();
        player2Name = new TextField();
        label = new Label();
        btnStartGame = new Button();
        backArowPlayerName = new ImageView();
        listView = new ListView();
        label0 = new Label();
        fileNames = new ArrayList();

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
        labelPlayer1.setText("Player1");
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
        labelPlayer2.setText("Player2");
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
        switchToSingleMode();

        btnStartGame.setLayoutX(111.0);
        btnStartGame.setLayoutY(296.0);
        btnStartGame.setMnemonicParsing(false);
        btnStartGame.setPrefHeight(46.0);
        btnStartGame.setPrefWidth(170.0);
        btnStartGame.setText("Start");
        btnStartGame.setTextFill(javafx.scene.paint.Color.WHITE);
        btnStartGame.setFont(new Font("System Bold Italic", 23.0));
        btnStartGame.getStyleClass().add("changeButtonStyle");
        this.getStylesheets().add("tictactoe_client/Views/style/style.css");
        System.out.println();

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

        listView.setLayoutX(299.0);
        listView.setLayoutY(71.0);
        listView.setPrefHeight(327.0);
        listView.setPrefWidth(250.0);
        getFilesNames();
        ObservableList<String> names = FXCollections.observableArrayList(fileNames);
        listView.setItems(names);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    DataSaver.dataSaverInstance().setGame(FileManger.loadFile((String) listView.getSelectionModel().getSelectedItem()));
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Do you want to watch the record game?");
                    if (a.showAndWait().get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                        Navigation.navigateTo(new RecordedBordBase(), event);
                    }
                    System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
                    System.out.println(DataSaver.dataSaverInstance().getGame());
                } catch (IOException ex) {
                    Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        label0.setLayoutX(362.0);
        label0.setLayoutY(38.0);
        label0.setText("Recorded Games");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setFont(new Font("System Bold Italic", 16.0));

        getChildren().add(labelPlayer1);
//        getChildren().add(imageView);
        getChildren().add(labelPlayer2);
        getChildren().add(player1Name);
        getChildren().add(player2Name);
        getChildren().add(label);
        getChildren().add(btnStartGame);
        getChildren().add(backArowPlayerName);
        getChildren().add(listView);
        getChildren().add(label0);
    }

    public void switchToSingleMode() {
        if (DataSaver.dataSaverInstance().getModeData() == "Single Mode") {
            player2Name.setVisible(false);
            player2Name.setText("Computer");
            labelPlayer2.setVisible(false);
        }
    }

    public void getFilesNames() {
        FileManger.createFolderIfNotExist();
        File folder = new File(System.getProperty("user.home") + "/tic_tac_toe_files");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println(listOfFiles[i].getName());
                int dotIndex = listOfFiles[i].getName().lastIndexOf('.');

                String fileNameWithOutExtention = listOfFiles[i].getName().substring(0, dotIndex);

                fileNames.add(fileNameWithOutExtention);
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
//        return fileNames;
    }
}
