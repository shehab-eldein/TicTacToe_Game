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


/**
 *
 * @author hamed
 */

public class FileManger {

    private static FileOutputStream fileOutputStream;
    private static FileInputStream fileInputStream;
 

    public static void saveFile(String fileName, String data) throws IOException {
        openOutputStream(fileName);
        fileOutputStream.write(data.getBytes());
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
        if(bytesList.isEmpty())
            return "";
        return new String(bytesListToArray(bytesList));
    }
    
    private static byte[] bytesListToArray(ArrayList<Byte> bytesList){
        byte[] bytesArray = new byte[bytesList.size()];
        for (int index = 0; index < bytesList.size() - 1; index++) {
            bytesArray[index] = bytesList.get(index);
        }
        return bytesArray;
    }

    private static File getFile(String fileName) {
        if(!createFolderIfNotExist())
            return new File(System.getProperty("user.home")+"/tic_tac_toe_files",fileName+".txt");
        
        return null;
    }
    
    private static boolean createFolderIfNotExist(){
        File file = new File(System.getProperty("user.home")+"/tic_tac_toe_files");
        if(!file.exists()){
            file.mkdir();
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
    
    private static void closeInputStream() throws IOException{
        fileInputStream.close();
    }
    
    private static void closeOutputStream() throws IOException{
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
