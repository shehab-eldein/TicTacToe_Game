/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe_server.Models.User;
import tictactoe_server.Services.DBConnector;

/**
 *
 * @author DELL
 */
public class UserRepository {

    public static User create(User user) throws SQLException {
        //deal with new user
        DBConnector.executeUpdate("Insert into Users(name,password)  values('" + user.getName() + "','" + user.getPass() + "')");
        return getByName(user.getName(), user.getPass());
    }

    public static User getByName(String name, String password) throws SQLException {
        // deal with signin
        ResultSet resultSet = DBConnector.executeQuery("select * from Users where name ='" + name + "'and password='" + password + "'");
        while (resultSet.next()) {
            return new User(resultSet.getString("name"), resultSet.getString("password"));
        }
        return null;
    }

    public static boolean checkUniqeName(String name) throws SQLException {
        if (DBConnector.executeQuery("select 1 from Users where name ='" + name + "'").next()) {
            return false;
        }
        return true;
    }

    public static int getUsersCount() throws SQLException {
        ResultSet resultSet =DBConnector.executeQuery("select count(*) \"count\" from Users ");
        resultSet.next();       
        return resultSet.getInt("count");
    }
    
}
