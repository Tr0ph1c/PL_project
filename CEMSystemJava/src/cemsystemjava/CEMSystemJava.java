package cemsystemjava;

import MySQLConnection.J2SQL;
import java.sql.Connection;

public class CEMSystemJava {

    public static void main(String[] args) {
        LoginWindow login = new LoginWindow();
        login.setVisible(true);
        
        J2SQL.Connect();
        Connection con = J2SQL.con;
    }
}
