package cemsystemjava;

//import MSSQLHelper.Java2SQLConnection;
import java.sql.*;

public class CEMSystemJava {

    public static void main(String[] args) {
        LoginWindow login = new LoginWindow();
        login.setVisible(true);
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cemsystem?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "12345");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
