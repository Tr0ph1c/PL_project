package cemsystemjava;

import DirectoryBasedDB.Database;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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

    public static void ChangeInfo() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        
        while (continueLoop) {
            System.out.println("Do you want to change name, age, password, or exit?");
            System.out.println("1. Name");
            System.out.println("2. Age");
            System.out.println("3. Password");
            System.out.println("4. Exit");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter new name:");
                    String newName = scanner.next();
                    currentUser.setName(newName);
                    break;
                case 2:
                    System.out.println("Enter new age:");
                    int newAge = scanner.nextInt();
                    currentUser.setAge(newAge);
                    break;
                case 3:
                    System.out.println("Enter new password:");
                    String newPassword = scanner.next();
                    currentUser.setPassword(newPassword);
                    break;
                case 4:
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        
        scanner.close();
    }
}



