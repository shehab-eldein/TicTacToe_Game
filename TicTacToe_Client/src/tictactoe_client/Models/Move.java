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
            case "block1" :
                return 0;
            case "block2" :
                return 1;
            case "block3" :
                return 2;
            case "block4" :
                return 3;
            case "block5" :
                return 4;
            case "block6" :
                return 5;
            case "block7" :
               return 6;
            case "block8" :
                return 7;
            case "block9" :
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
