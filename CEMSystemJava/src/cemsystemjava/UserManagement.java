package cemsystemjava;

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
        String fileName = id + ".txt"; // add this line to get the file name based on the ID
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(type.toString()) && Integer.parseInt(parts[1]) == id) {
                    found = true;
                    if (parts[2].equals(password)) {
                        System.out.println("logging in");
                        return 0;
                    } else {
                        System.out.println("wrong password");
                        return 1;
                    }
                }
            }
            if (!found) {
                System.out.println("user not found");
                return 2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}


