/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author hamed
 */
public class DataSaver {

    private static DataSaver dataSaverInstance;
    private String player1;
    private String player2;
    private String winner;
    private String mode;

    private DataSaver() {

    }

    public static DataSaver dataSaverInstance() {
        if (dataSaverInstance == null) {
            dataSaverInstance = new DataSaver();
        }
        return dataSaverInstance;
    }

    public void setModeData(String data) {
        mode = data;
    }
    public void setPlayer1Data(String data) {
        player1 = data;
    }
    
    public void setPlayer2Data(String data) {
        player2 = data;
    }
    
    public void setwinnerData(String data) {
        winner = data;
    }

    public String getPlayer1Data() {
        return player1;
    }
    public String getPlayer2Data() {
        return player2;
    }
    public String getwinnerData() {
       return winner;
    }
    public String  getModeData() {
        return mode;
    }
}
