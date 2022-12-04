/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author hamed
 */
public class Navigation {
    private static Scene scene;
    private static Stage stage;
     
  
    // navigate by event action parameter
    public static void navigateTo(Parent distinationRoot, ActionEvent event){
        scene = new Scene(distinationRoot);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        showScene();
    }
    
    // navigate by event parameter
    public static void navigateTo(Parent distinationRoot, Event event){
        scene = new Scene(distinationRoot);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        showScene();
    }
    
    // navigate by stage parameter
    public static void navigateTo(Parent distinationRoot, Stage currentStage){
        scene = new Scene(distinationRoot);
        stage = currentStage;
        showScene();
    }
    
    private static void showScene(){
        stage.setScene(scene);
        stage.show();
    }
}