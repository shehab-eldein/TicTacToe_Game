/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Models;

/**
 *
 * @author DELL
 */
public class User {
   private String name;
   private String pass;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

   

   

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
   
    
}
