/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe_client.Models.History;

/**
 *
 * @author hamed
 */
public class FileManger {

    private static FileOutputStream fileOutputStream;
    private static FileInputStream fileInputStream;
    private static FileOutputStream localFileOutputStream;
    private static FileInputStream localFileIntputStream;
   public static  File localFile;
    public static  String localData;

    public static void saveFile(String fileName, String data) throws IOException {
        openOutputStream(fileName);
        fileOutputStream.write(data.getBytes());
        getFile(fileName).setReadable(false, true);
    }

    public static String loadFile(String fileName) throws IOException {
        openInputStream(fileName);
        ArrayList<Byte> bytesList = new ArrayList();
        int data;
        data = fileInputStream.read();
        while (data != -1) {
            bytesList.add((byte) data);
            data = fileInputStream.read();
        }

        if (bytesList.isEmpty()) {
            return "";
        }
        return new String(bytesListToArray(bytesList));
    }
    
        public FileManger() throws IOException {
        localFile = new File("localDataBase.txt");
        if (!localFile.exists()) {
            localFile.createNewFile();
        }
        localData = readData();
    }

    public String readData() throws IOException {
        localFileIntputStream = new FileInputStream(localFile);
        String text = new String();
        int i = localFileIntputStream.read();
        while (!(i == -1)) {
            char c = (char) i;
            text+=String.valueOf(c);
            
            i = localFileIntputStream.read();
            
        }
        return text;
    }

    public static void writeData(History history) {
       
        try {
            String delimiter= ",";
            localFileOutputStream = new FileOutputStream(new File("localDataBase.txt") ,true);
            localFileOutputStream.write((history.getDate()).getBytes());
            localFileOutputStream.write(delimiter.getBytes());
            
            localFileOutputStream.write((history.getPlayer1()).getBytes());
            localFileOutputStream.write(delimiter.getBytes());
            localFileOutputStream.write((history.getPlayer2()).getBytes());
            localFileOutputStream.write(delimiter.getBytes());
            
            localFileOutputStream.write((history.getPlayer1Score()).getBytes());
            localFileOutputStream.write(delimiter.getBytes());
            localFileOutputStream.write((history.getPlayer2Score()).getBytes());
            localFileOutputStream.write(delimiter.getBytes());
            
            localFileOutputStream.write("//t".getBytes());
            localFileOutputStream.flush();
            localFileOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManger.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

    private static byte[] bytesListToArray(ArrayList<Byte> bytesList) {
        byte[] bytesArray = new byte[bytesList.size()];
        for (int index = 0; index < bytesList.size() - 1; index++) {
            bytesArray[index] = bytesList.get(index);
        }
        return bytesArray;
    }

    private static File getFile(String fileName) {
        createFolderIfNotExist();
        return new File(System.getProperty("user.home") + "/tic_tac_toe_files", fileName + ".encrypted");
    }

    public static boolean createFolderIfNotExist() {
        File file = new File(System.getProperty("user.home") + "/tic_tac_toe_files");
        if (!file.exists()) {
            file.mkdir();

            return true;
        }
        return false;
    }

    public static boolean checkFileExistance(String fileName) {
        File file = new File(System.getProperty("user.home") + "/tic_tac_toe_files",
                fileName + ".encrypted");
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private static void openOutputStream(String fileName) throws FileNotFoundException {
        fileOutputStream = new FileOutputStream(getFile(fileName));
    }

    private static void openInputStream(String fileName) throws FileNotFoundException {
        fileInputStream = new FileInputStream(getFile(fileName));
    }

    private static void closeInputStream() throws IOException {
        fileInputStream.close();
    }

    private static void closeOutputStream() throws IOException {
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
