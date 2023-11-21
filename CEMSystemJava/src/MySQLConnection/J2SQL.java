package MySQLConnection;

import java.sql.*;

public class J2SQL {
    public static Connection con;
    
    private static final String URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11663733?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "sql11663733";
    private static final String PWORD = "6S497PdVvE";
    
    public static void Connect () {
        try {
            con = DriverManager.getConnection(URL, USER, PWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
