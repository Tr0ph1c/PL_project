package cemsystemjava;

import DirectoryBasedDB.Database;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public final class UserManagement {
    public static enum UserType {
        STUDENT,
        LECTURER,
        ADMINISTRATOR
    }
    
    public static User currentUser;
    
    public static int Login (String ID, String password, UserType type)
    {
        String dir = "";
        
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
            String name = "";
            String age = "";
            
            
            for (int i = 0; i <= 2; i++) {
                line = reader.readLine();
                switch (i) {
                    case 0 -> name = line;
                    case 1 -> age = line;
                }
            }
            
            if (type == UserType.LECTURER) {
                String course = reader.readLine();
                Lecturer u = new Lecturer(Integer.parseInt(ID), name, Integer.parseInt(age), password, course);
                currentUser = u;
            } else if (type == UserType.STUDENT) {
                String[] courses = reader.readLine().split(";");
                Student u = new Student(Integer.parseInt(ID), name, Integer.parseInt(age), password, courses);
                currentUser = u;
            } else if (type == UserType.ADMINISTRATOR) {
                Admin u = new Admin(Integer.parseInt(ID), name, Integer.parseInt(age), password);
                currentUser = u;
            }
            
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
    @SuppressWarnings("empty-statement")
    public static void ChangeInfo() {
        UserType type = currentUser.getType();
        
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to change your name, age, password, or exit? Enter the corresponding number.");
            System.out.println("1. Change Name");
            System.out.println("2. Change Age");
            System.out.println("3. Change Password");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            String newValue = "";
                switch (choice) {
                    case 1:
                        System.out.println("Enter your new name:");
                        newValue = scanner.nextLine();
                        currentUser.setName(newValue);
                        break;
                    case 2:
                        System.out.println("Enter your new age:");
                        newValue = scanner.next();
                        currentUser.setAge(Integer.parseInt(newValue));
                        break;
                    case 3:
                        System.out.println("Enter your new password:");
                        newValue = scanner.next();
                        currentUser.setPassword(newValue);
                        break;
                    case 4:
                         return;
                    default:
                         System.out.println("Invalid choice.");
                         return;
                }


            String fileName = ""+currentUser.getID();

            String dir = "";

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
            String name = currentUser.getName();
            String password = currentUser.getPassword();
            String age = Integer.toString (currentUser.getAge());
            String[] lines ={name,age,password};
            Database.overwriteRecord (dir, fileName, lines);
       }
    }
}


