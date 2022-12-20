/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Eltaweel
 */
public class History {

    private SimpleStringProperty date;
    private SimpleStringProperty player1;
    private SimpleStringProperty player2;
    private SimpleStringProperty player1Score;
    private SimpleStringProperty player2Score;

    public History(String date, String player1, String player2, String player1Score, String player2Score) {
        this.date = new SimpleStringProperty(date);
        this.player1 = new SimpleStringProperty(player1);
        this.player2 = new SimpleStringProperty(player2);
        this.player1Score = new SimpleStringProperty(player1Score);
        this.player2Score = new SimpleStringProperty(player2Score);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(SimpleStringProperty date) {
        this.date = date;
    }

    public String getPlayer1() {
        return player1.get();
    }

    public void setPlayer1(SimpleStringProperty player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2.get();
    }

    public void setPlayer2(SimpleStringProperty player2) {
        this.player2 = player2;
    }

    public String getPlayer1Score() {
        return player1Score.get();
    }

    public void setPlayer1Score(SimpleStringProperty player1Score) {
        this.player1Score = player1Score;
    }

    public String getPlayer2Score() {
        return player2Score.get();
    }

    public void setPlayer2Score(SimpleStringProperty player2Score) {
        this.player2Score = player2Score;
    }
    

}
