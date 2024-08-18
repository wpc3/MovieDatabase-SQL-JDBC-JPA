package io.zipcoder.persistenceapp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnnectionTest {

    static String username = "root";
    static String password = "Mightymac18!";
    static String dbUrl = "jdbc:mysql://localhost:3306/movieTheatre";

    public static void main(String[] args) throws SQLException{
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(dbUrl,username,password);
            System.out.println("Connection Established");
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        finally {
            conn.close();
        }
    }

}
