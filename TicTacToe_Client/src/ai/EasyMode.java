/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.ArrayList;
import java.util.Random;
import tictactoe_client.Enums.Shape;
import tictactoe_client.Models.Move;

/**
 *
 * @author hamed
 */
public class EasyMode {
    
    private Shape[] blocksHistory;
    private ArrayList<Integer> freeBlocks;

    public EasyMode(Shape history[]) {
        blocksHistory = history;
        freeBlocks = new ArrayList();
    }
    
    public int getNextIndex(){
        int index = 0;
        for (Shape shape : blocksHistory) {
            if (shape == null) {
                freeBlocks.add(index);
            }
            index++;
        }
        if (!freeBlocks.isEmpty()) 
            return freeBlocks.get(getRandomIndex());
        
       return -1; 
    }
    
    private int getRandomIndex(){
        Random random = new Random();
        return random.nextInt(0 + freeBlocks.size()) + 0;
    }
    
    
    
    
}
