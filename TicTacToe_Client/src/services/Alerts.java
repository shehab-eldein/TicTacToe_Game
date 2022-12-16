/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Optional;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import tictactoe_client.Controllers.GameHandler;

/**
 *
 * @author hamed
 */
public class Alerts {
    
    private static final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private static Consumer<?> consumer;
    
    public static void showAlert(String message, Consumer consumer) {
        alert.setContentText(message);
        if (alert.showAndWait().get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        }
        consumer.accept(null);
    }
    
    public static void showRequestAlert(String message, Consumer acceptConcumer, Consumer rejectConsumer) {
        ButtonType accept = new ButtonType("accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType reject = new ButtonType("reject", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alertShowReq = new Alert(AlertType.CONFIRMATION, message,
                accept,
                reject);
        if (alertShowReq.showAndWait().get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            acceptConcumer.accept(null);
        } else {
            rejectConsumer.accept(null);
        }
    }
    
    public static void showAlert(String message) {
        alert.setContentText(message);
        alert.show();
    }
    
}
