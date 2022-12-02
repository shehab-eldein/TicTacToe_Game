/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Models;

import tictactoe_client.Enums.GameState;
import tictactoe_client.Enums.Shape;

/**
 *
 * @author DELL
 */
public class Board {

    private Shape squares[];

    public Board() {
        squares = new Shape[9];
    }

    public GameState applyMove(Move move) {
        squares[move.getIndex()] = move.getShape();
        return calculateGameState(move);
    }

    private GameState calculateGameState(Move move) {
        //clculation algorithm
        Shape shape = move.getShape();
        //WIN 
        if (isWin(shape)) {
            if (shape == Shape.X) {
                return GameState.X_WIN;
            } else {
                return GameState.O_WIN;
            }
        }
        //Draw
        if (isDraw()) {
            return GameState.DRAW;
        }
        // O Turn
        if (shape == Shape.X) {
            return GameState.O_TURN;
        }
        // XTurn  
        return GameState.X_TURN;

    }

    public boolean isValidMove(Move move) {
        return squares[move.getIndex()] == null;
    }

    private boolean isDraw() {
        for (Shape shape : squares) {
            if (shape == null) {
                return false;
            }
        }
        return true;
    }

    private boolean isWin(Shape shape) {
        if (squares[2] == shape && squares[4] == shape && squares[6] == shape) {
            //Win
            return true;
        }
        if (squares[0] == shape && squares[4] == shape && squares[8] == shape) {
            //Win
            return true;
        }
        if (squares[2] == shape && squares[5] == shape && squares[8] == shape) {
            //Win
            return true;
        }
        if (squares[1] == shape && squares[4] == shape && squares[7] == shape) {
            //Win
            return true;
        }
        if (squares[0] == shape && squares[3] == shape && squares[6] == shape) {
            //Win
            return true;
        }
        if (squares[0] == shape && squares[1] == shape && squares[2] == shape) {
            //Win
            return true;
        }
        if (squares[3] == shape && squares[4] == shape && squares[5] == shape) {
            //Win
            return true;
        }
        if (squares[6] == shape && squares[7] == shape && squares[8] == shape) {
            //Win
            return true;
        } else {
            return false;
        }

    }
}
