package MSSQLHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Java2SQLConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;database=CEMSystem;integratedSecurity=true;instanceName=SQLExpress;";
    
    public static Connection connect () {
        try {
            Connection connection = DriverManager.getConnection(URL);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't establish connection.");
            return null;
        }
    }
}
