/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_client.Models;
import tictactoe_client.Enums.Shape;

/**
 *
 * @author hamed
 */
public class Move {
    private Shape shape;
    private String blockId;
    
    public Move(String id, Shape shape) {
        blockId = id;
        this.shape = shape;
    }
    
    
    private int getPostionByBlockId(){
        switch (blockId) {
            case "btn0" :
                return 0;
            case "btn1" :
                return 1;
            case "btn2" :
                return 2;
            case "btn3" :
                return 3;
            case "btn4" :
                return 4;
            case "btn5" :
                return 5;
            case "btn6" :
               return 6;
            case "btn7" :
                return 7;
            case "btn8" :
               return 8;
        }
        return -1;
    }
    
    
    public int getIndex(){
        return getPostionByBlockId();
    }
    
    public Shape getShape(){
        return shape;
    }
    
  
}
