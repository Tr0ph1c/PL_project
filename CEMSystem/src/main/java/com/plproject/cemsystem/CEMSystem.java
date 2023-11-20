package com.plproject.cemsystem;

import MSSQLHelper.Java2SQLConnection;
import java.sql.Connection;

public class CEMSystem {

    public static void main(String[] args) {
        LoginWindow login = new LoginWindow();
        login.setVisible(true);
        
        Connection connection = Java2SQLConnection.connect();
    }
}
