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
   private int queryType; 

    public User(int id, String name, String pass, int queryType) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.queryType = queryType;
    }

    public int getQueryType() {
        return queryType;
    }
   
    public void setQueryType(int queryType) {
        this.queryType = queryType;
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
