/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Models;

import tictactoe_client.Enums.PlayerType;
import tictactoe_client.Enums.Shape;

/**
 *
 * @author DELL
 */
public class Player {
    String name;
    PlayerType type;
    Shape shape;

    public Player(String name, PlayerType type, Shape shape) {
        this.name = name;
        this.type = type;
        this.shape = shape;
    }
    
    
 }
