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
   private int id ;
   private String name;
   private String pass;

    public User(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }
   

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
   
    
}