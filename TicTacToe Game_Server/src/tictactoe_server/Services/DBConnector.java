/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_server.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;

/**
 *
 * @author DELL
 */
public class DBConnector {

    private static Connection startConnection() throws SQLException {
        //driver
        //connect DB
        Connection connection = null;
        DriverManager.registerDriver(new ClientDriver());
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TicTacToe_Server", "root", "root");
        System.out.println("Connected to DataBase");
        return connection;
    }

    public static void executeUpdate(String query) throws SQLException {
        // write query
        Connection connection = startConnection();
        PreparedStatement pst = connection.prepareStatement(query);
        // run query
        int rs = pst.executeUpdate();
        System.out.println("Result::::" + rs);
        connection.close();
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        ResultSet resultSet = null;
        // write query
        Connection connection = startConnection();
        PreparedStatement pst = connection.prepareStatement(query);
        // run query
        resultSet = pst.executeQuery();
        System.out.println("Result::::" + resultSet);
        //connection.close();
        return resultSet;
    }
}
