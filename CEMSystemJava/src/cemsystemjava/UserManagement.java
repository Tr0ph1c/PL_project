package cemsystemjava;

import java.sql.Connection;

enum UserType {
    STUDENT,
    LECTURER,
    ADMINISTRATOR
}

public class UserManagement {
    public static User currentUser;
    
    public static boolean Login (int id, String password) {
        //Check if ID and password match from database return false if not
        //Open window with the ID as a precursor
        //set User to an object with the login info
        System.out.println("logging in");
        return true;
    }
}
