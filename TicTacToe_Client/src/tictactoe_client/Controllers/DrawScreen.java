package tictactoe_client.Controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import services.Navigation;

public class DrawScreen extends AnchorPane {

    protected final MediaView DrawVideo;
    protected final Button btnDrawPlayAgain;
    protected final Button btnDrawExit;
    protected final Label label;
    private File file;
    private MediaPlayer mediaplayer;
    private Media media;

    public DrawScreen() {

        DrawVideo = new MediaView();
        btnDrawPlayAgain = new Button();
        btnDrawExit = new Button();
        label = new Label();

        setId("AnchorPane");
        setPrefHeight(409.0);
        setPrefWidth(560.0);
        setStyle("-fx-background-color: #291660;");

        AnchorPane.setRightAnchor(DrawVideo, 40.0);
        DrawVideo.setFitHeight(230.0);
        DrawVideo.setFitWidth(490.0);
        DrawVideo.setLayoutX(40.0);
        DrawVideo.setLayoutY(14.0);
        DrawVideo.setPreserveRatio(false);
        file = new File("src/tictactoe_client/Views/img/draw.mp4");
        media = new Media(file.toURI().toString());
        mediaplayer = new MediaPlayer(media);
        DrawVideo.setMediaPlayer(mediaplayer);
        mediaplayer.setAutoPlay(true);

        btnDrawPlayAgain.setLayoutX(193.0);
        btnDrawPlayAgain.setLayoutY(290.0);
        btnDrawPlayAgain.setMnemonicParsing(false);
        btnDrawPlayAgain.setPrefHeight(38.0);
        btnDrawPlayAgain.setPrefWidth(162.0);
        btnDrawPlayAgain.setText("play Again");
        btnDrawPlayAgain.setTextFill(javafx.scene.paint.Color.WHITE);
        btnDrawPlayAgain.setFont(new Font("System Bold Italic", 19.0));
        btnDrawPlayAgain.getStyleClass().add("changeButtonStyle");
        this.getStylesheets().add("tictactoe_client/Views/style/style.css");
        System.out.println();

        btnDrawPlayAgain.setOnAction((ActionEvent event) -> {
            Navigation.navigateTo(new BordBase(), event);
        });

        btnDrawExit.setLayoutX(193.0);
        btnDrawExit.setLayoutY(338.0);
        btnDrawExit.setMnemonicParsing(false);
        btnDrawExit.setPrefHeight(38.0);
        btnDrawExit.setPrefWidth(162.0);
        btnDrawExit.setText("Exit");
        btnDrawExit.setTextFill(javafx.scene.paint.Color.WHITE);
        btnDrawExit.setFont(new Font("System Bold Italic", 19.0));
        btnDrawExit.getStyleClass().add("changeButtonStyle");
        this.getStylesheets().add("tictactoe_client/Views/style/style.css");
        System.out.println();

        btnDrawExit.setOnAction((ActionEvent event) -> {

            Navigation.navigateTo(new ChooseMode(), event);
        });

        label.setLayoutX(182.0);
        label.setLayoutY(244.0);
        label.setPrefHeight(43.0);
        label.setPrefWidth(196.0);
        label.setText("           Draw ");

        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("System Bold Italic", 23.0));

        getChildren().add(DrawVideo);
        getChildren().add(btnDrawPlayAgain);
        getChildren().add(btnDrawExit);
        getChildren().add(label);

    }
}
