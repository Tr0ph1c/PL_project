package cemsystemjava;

import DirectoryBasedDB.Database;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

enum UserType {
    STUDENT,
    LECTURER,
    ADMINISTRATOR
}

public class UserManagement {
    public static User currentUser;
    
    public static int Login (int id, String password, UserType type)
    {
        String dir = "";
        String ID = Integer.toString(id);
        
        switch (type) {
            case STUDENT:
                dir = Database.TABLE_STUDS;
                break;
            case LECTURER:
                dir = Database.TABLE_LECTS;
                break;
            case ADMINISTRATOR:
                dir = Database.TABLE_ADMIN;
                break;
        }
        
        try {
            if (!Database.recordExists(dir, ID)) return 2;
            
            BufferedReader reader = new BufferedReader(new FileReader(dir+ID));
            String line = "";
            
            for (int i = 0; i <= 2; i++, line = reader.readLine());
            
            if (line.equals(password)) {
                return 0;
            } else {
                return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 2;
        }
    }
}


