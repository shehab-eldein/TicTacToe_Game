/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;

/**
 *
 * @author hamed
 */
public class Alerts {
    private static final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private static Consumer<?> consumer;
    
    public static void showAlert(String message, Consumer consumer){
        alert.setContentText(message);
        if(alert.showAndWait().get().getButtonData()==ButtonBar.ButtonData.OK_DONE)
            consumer.accept(null);
        alert.show();
    }
    
    public static void showAlert(String message){
        alert.setContentText(message);
        alert.show();
    }
    
}
