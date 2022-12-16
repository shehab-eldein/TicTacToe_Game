/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javafx.stage.Stage;

/**
 *
 * @author Esraa
 */
public class StageSaver {
    private  Stage stage;
    private static StageSaver instance;
    
    private StageSaver() {
        
    }
    public static StageSaver getStageSeverInstance(){
        
        if(instance==null){
            instance=new StageSaver();
        }
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
   
    
}
