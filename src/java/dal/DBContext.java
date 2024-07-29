/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DBContext {



    public static Connection DBContext() { 
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String user = "sa";
            String pass = "hung1234";
            String url = "jdbc:sqlserver://localhost:1433;databaseName= ComputerShopes";
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Success!");
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return connection;
    }
}

 