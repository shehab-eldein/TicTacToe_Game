/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author hamed
 */
public class Navigation {
    private Scene scene;
    private Stage stage;
     
    public Navigation() {
        scene = null;
        stage = null;
    }
   
    // navigate by event parameter
    public void navigateTo(Parent distinationRoot, ActionEvent event){
        scene = new Scene(distinationRoot);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        showScene();
    }
    
    // navigate by stage parameter
    public void navigateTo(Parent distinationRoot, Stage stage){
        scene = new Scene(distinationRoot);
        this.stage = stage;
        showScene();
    }
    
    private void showScene(){
        stage.setScene(scene);
        stage.show();
    }
}