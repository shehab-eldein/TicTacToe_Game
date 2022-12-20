package tictactoe_client.Controllers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import services.Alerts;
import services.DataSaver;
import services.Navigation;
import services.StageSaver;

public class WinScreen extends AnchorPane {

    protected final MediaView winVideo;
    protected final Button btnWinPlayAgain;
    protected final Button btnWinExit;
    protected final Label label;
    protected final Label labelWinPlayerName;
    private File file;
    private MediaPlayer mediaplayer;
    private Media media;
    private GameHandler gameHandler;

    public WinScreen() {
        this.getStylesheets().add("tictactoe_client/Views/style/style.css");

        winVideo = new MediaView();
        btnWinPlayAgain = new Button();
        btnWinExit = new Button();
        label = new Label();
        labelWinPlayerName = new Label();

        setId("AnchorPane");
        setPrefHeight(409.0);
        setPrefWidth(560.0);
        setStyle("-fx-background-color: #291660;");

        AnchorPane.setRightAnchor(winVideo, 40.0);
        winVideo.setFitHeight(230.0);
        winVideo.setFitWidth(490.0);
        winVideo.setLayoutX(40.0);
        winVideo.setLayoutY(14.0);
        winVideo.setPreserveRatio(false);
        file = new File("src/tictactoe_client/Views/img/win.mp4");
        media = new Media(file.toURI().toString());
        mediaplayer = new MediaPlayer(media);
        winVideo.setMediaPlayer(mediaplayer);
        mediaplayer.play();

        btnWinPlayAgain.setLayoutX(193.0);
        btnWinPlayAgain.setLayoutY(290.0);
        btnWinPlayAgain.setMnemonicParsing(false);
        btnWinPlayAgain.setPrefHeight(38.0);
        btnWinPlayAgain.setPrefWidth(162.0);
//        btnWinPlayAgain.setStyle("-fx-border-color: #ff5c9d; -fx-background-color: #291660;");
        btnWinPlayAgain.setText("play Again");
        btnWinPlayAgain.setTextFill(javafx.scene.paint.Color.WHITE);
        btnWinPlayAgain.setFont(new Font("System Bold Italic", 19.0));
        btnWinPlayAgain.setFont(new Font("System Bold Italic", 19.0));
        btnWinPlayAgain.getStyleClass().add("changeButtonStyle");
        //play again

        try {
            gameHandler = GameHandler.getInstance((erro) -> {
            }, (response) -> {
                if (response.split("-")[0].equals("409")) {
                    
                    Platform.runLater(() -> {
                        Alerts.showAlert(response.split("-")[1] + " is win", (error) -> {
                        });
                        try {
                            gameHandler.setIsInGame(false);
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
        btnWinPlayAgain.setOnAction((event) -> {
            if (DataSaver.dataSaverInstance().getModeData() == "Online Mode") {
                Platform.runLater(() -> {
                    try {
                        gameHandler.writeData("9-" + DataSaver.dataSaverInstance().getPlayer2Data());
                         Thread.sleep(1500);
                         gameHandler.setIsInGame(false);
                        Navigation.navigateTo(FXMLLoader.load(tictactoe_client.TicTacToe_Client.class.getResource("Views/ChoosePlayers.fxml")), event);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });

                //Navigation.navigateTo(new BordBase(), event);
            } else {
                Navigation.navigateTo(new BordBase(), event);
            }
        });

        btnWinExit.setLayoutX(193.0);
        btnWinExit.setLayoutY(338.0);
        btnWinExit.setMnemonicParsing(false);
        btnWinExit.setPrefHeight(38.0);
        btnWinExit.setPrefWidth(162.0);
//        btnWinExit.setStyle("-fx-border-color: #ff5c9d; -fx-background-color: #291660;");
        btnWinExit.setText("Exit");
        btnWinExit.setTextFill(javafx.scene.paint.Color.WHITE);
        btnWinExit.setFont(new Font("System Bold Italic", 19.0));
        btnWinExit.getStyleClass().add("changeButtonStyle");
        // exit game
        btnWinExit.setOnAction((event) -> {

            Navigation.navigateTo(new ChooseMode(), event);
        });

        label.setLayoutX(180.0);
        label.setLayoutY(255.0);
        label.setPrefHeight(17.0);
        label.setPrefWidth(112.0);
        label.setText("Congratulation");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("System Italic", 16.0));

        labelWinPlayerName.setLayoutX(292.0);
        labelWinPlayerName.setLayoutY(257.0);
        labelWinPlayerName.setPrefHeight(21.0);
        labelWinPlayerName.setPrefWidth(112.0);
        labelWinPlayerName.setText(DataSaver.dataSaverInstance().getwinnerData());
        labelWinPlayerName.setTextFill(javafx.scene.paint.Color.valueOf("#ff5c9d"));
        labelWinPlayerName.setFont(new Font("System Italic", 15.0));

        getChildren().add(winVideo);
        getChildren().add(btnWinPlayAgain);
        getChildren().add(btnWinExit);
        getChildren().add(label);
        getChildren().add(labelWinPlayerName);

    }

}
