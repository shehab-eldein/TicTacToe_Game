package tictactoe_client.Controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class HistoryBase extends AnchorPane {

    protected final Label history;
    protected final ImageView backArowPlayerName;
    protected final TableView tableView;
    protected final TableColumn dateCol;
    protected final TableColumn player1Col;
    protected final TableColumn player2Col;
    protected final TableColumn player1ScoreCol;
    protected final TableColumn player2ScoreCol;

    public HistoryBase() {

        history = new Label();
        backArowPlayerName = new ImageView();
        tableView = new TableView();
        dateCol = new TableColumn();
        player1Col = new TableColumn();
        player2Col = new TableColumn();
        player1ScoreCol = new TableColumn();
        player2ScoreCol = new TableColumn();

        setId("AnchorPane");
        setPrefHeight(409.0);
        setPrefWidth(560.0);
        setStyle("-fx-background-color: #291660;");

        history.setLayoutX(214.0);
        history.setLayoutY(6.0);
        history.setMinHeight(16);
        history.setMinWidth(69);
        history.setText("History");
        history.setTextFill(javafx.scene.paint.Color.WHITE);
        history.setFont(new Font("System Bold Italic", 38.0));

        backArowPlayerName.setFitHeight(26.0);
        backArowPlayerName.setFitWidth(23.0);
        backArowPlayerName.setLayoutX(6.0);
        backArowPlayerName.setLayoutY(6.0);
        backArowPlayerName.setPickOnBounds(true);
        backArowPlayerName.setImage(new Image("tictactoe_client/Views/img/backarow.png"));

        tableView.setLayoutX(39.0);
        tableView.setLayoutY(64.0);
        tableView.setPrefHeight(318.0);
        tableView.setPrefWidth(485.0);

        dateCol.setPrefWidth(150.0);
        dateCol.setText("Date");

        player1Col.setMinWidth(0.0);
        player1Col.setPrefWidth(75.0);
        player1Col.setText("Player1");

        player2Col.setMinWidth(0.0);
        player2Col.setPrefWidth(85.33331298828125);
        player2Col.setText("Player2");

        player1ScoreCol.setMinWidth(0.0);
        player1ScoreCol.setPrefWidth(85.33331298828125);
        player1ScoreCol.setText("Player1Score");

        player2ScoreCol.setMinWidth(0.0);
        player2ScoreCol.setPrefWidth(85.33331298828125);
        player2ScoreCol.setText("Player2Score");
        
        

        getChildren().add(history);
        getChildren().add(backArowPlayerName);
        tableView.getColumns().add(dateCol);
        tableView.getColumns().add(player1Col);
        tableView.getColumns().add(player2Col);
        tableView.getColumns().add(player1ScoreCol);
        tableView.getColumns().add(player2ScoreCol);
                tableView.getItems().add("Rafeef");

        getChildren().add(tableView);

    }
}
