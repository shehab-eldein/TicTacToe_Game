/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Models;

import ai.EasyMode;
import java.util.ArrayList;
import tictactoe_client.Enums.GameState;

/**
 *
 * @author DELL
 */
public class Game {

    Player playerX;
    Player playerO;
    Board board;
    GameState gameState;
    ArrayList<Move> history;

    public Game(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        board = new Board();
        gameState = GameState.X_TURN;
        //history
    }

    public GameState action(Move move) {

        if (board.isValidMove(move)) {
            gameState = board.applyMove(move);
            // add move to history 
            // history.add(move);
        } else {
            //not Valid Move
        }
        return gameState;
    }

    void save() {
        //in data base in server
        //OR
        //in text File
    }
    int aiTurn() {
        int place = new EasyMode(board.getSquares()).getNextIndex();
        if (place != -1) {
             return place;
        }
           return -1;    
                
    } 

   
}
