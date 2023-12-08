package cemsystemjava;

import DirectoryBasedDB.Database;
import java.io.File;
import cemsystemjava.UserManagement.UserType;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CEMApp {

    public static void main(String[] args) {
        LoadIDs();
        CourseManager.LoadCourses();
        
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Main Menu");
            System.out.println("0- Exit");
            System.out.println("1- Login");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 0) {
                System.out.println("Exiting the program...");
                break; // Terminate the program
            } else if (choice == 1) {
                UserType type;
                System.out.println("Login");
                System.out.println("1 - Admin");
                System.out.println("2 - Student");
                System.out.println("3 - Lecturer");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                switch (choice) {
                    case 1 -> type = UserType.ADMINISTRATOR;
                    case 2 -> type = UserType.STUDENT;
                    case 3 -> type = UserType.LECTURER;
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                    }
                }
                System.out.print("Enter ID: ");
                String id = scanner.nextLine();
                
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                int loginResult = UserManagement.Login(id, password, type);

                if (loginResult == 0) {
                    System.out.println("Login successful");
                    LoadMenu();
                } else if (loginResult == 1) {
                    System.out.println("Unsuccessful login: Incorrect password");
                } else if (loginResult == 2) {
                    System.out.println("Unsuccessful login: User ID doesn't exist");
                } else {
                    System.out.println("Unsuccessful login: Unknown reason");
                }
            } else {
                System.out.println("Invalid option");
            }

            System.out.println(); // Print an empty line for formatting
        }

        scanner.close();
    }
    
    private static void LoadMenu() {
        switch (UserManagement.currentUser.getType()) {
            case STUDENT:
                StudentMenu();
                break;
            case LECTURER:
                LecturerMenu();
                break;
            case ADMINISTRATOR:
                AdminMenu();
                break;
            default:
                break;
        }
    }
    
    private static void StudentMenu() {
        Scanner scanner = new Scanner(System.in);
        Student u = (Student)UserManagement.currentUser;
        
        while (true) {
            char choice;
            System.out.println("Name: " + u.getName() + "\t\t\tAge: " + u.getAge() + "\n\n");
            System.out.println("Assigned Courses: " + Arrays.toString(u.getCourses()) + "\n");
            System.out.println("Menu:\n\n0-Logout\n1-Change Info\n2-Take Test\n3-Check Marks");
            choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '0' -> { return; } //logs out by returning from function to main loop
                case '1' -> UserManagement.ChangeInfo();
                case '2' -> FetchTests(u);
                case '3' -> FetchMarks(u);
                default -> System.out.println("Invalid choice, please try again.");
            }   
        }
    }
    
    private static void FetchTests(Student u) {
        Scanner scanner = new Scanner(System.in);
        String[] inp;
        String dir = Database.TABLE_EXAMS;
        
        while (true) {
            List<String> takenTests = getFileNames(Database.TABLE_STUDS+u.getID()+"tests");
            
            System.out.println("ID" + "\t" + "Course" + "\t" + "No. of Questions");
            File[] files = new File(dir).listFiles();

            for (File file : files) {
                String fileName = file.toPath().getFileName().toString();
                if (fileName.equals("ID") || takenTests.contains(fileName)) continue;
                String[] lines = Database.getlines(dir+fileName);
                System.out.println(fileName + "\t" + lines[0] + "\t" + (lines.length - 1));
            }
            
            System.out.println("--------\nTAKETEST {test id}\nEXIT");
            
            inp = scanner.nextLine().split(" ");
            String cmd = inp[0];
            
            if ("taketest".equals(cmd.toLowerCase())) {
                if (inp.length != 2) {
                    System.out.println("Inappropriate number of arguemnts");
                    continue;
                }
                u.TakeTest(inp[1]);
            } else if ("exit".equals(cmd.toLowerCase())) {
                return;
            } else {
                System.out.println("Invalid command");
            }
        }
    }
    
    private static void FetchMarks(Student u) {
        Scanner scanner = new Scanner(System.in);
        String inp;
        String dir = Database.TABLE_STUDS+u.getID()+"tests";
        
        while (true) {
            System.out.println("Test ID" + "\t" + "Course" + "\t" + "Mark");
            File[] files = new File(dir).listFiles();

            for (File file : files) {
                String fileName = file.toPath().getFileName().toString();
                String[] lines = Database.getlines(dir+fileName);
                System.out.println(fileName + "\t" + lines[0] + "\t" + (lines.length - 1));
            }
            
            System.out.println("--------\ntype 'EXIT' to leave this page");
            
            inp = scanner.nextLine().toLowerCase();
            
            if ("exit".equals(inp)) {
                return;
            } else {
                System.out.println("Invalid command");
            }
        }
    }
    
    private static void LecturerMenu() {
        Scanner scanner = new Scanner(System.in);
        Lecturer u = (Lecturer)UserManagement.currentUser;
        
        while (true) {
            char choice;
            System.out.println("Name: " + u.getName() + "\t\t\tAge: " + u.getAge());
            System.out.println("Assigned Course: " + u.getCourse() + "\n");
            System.out.println("Menu:\n\n0-Logout\n1-Change Info\n2-Create Test\n3-Manage Tests");
            choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '0' -> { return; } //logs out by returning from function to main loop
                case '1' -> UserManagement.ChangeInfo();
                case '2' -> u.CreateTest();
                case '3' -> FetchTests(u);
                default  -> System.out.println("Invalid choice, please try again.");
            }
        }
    }
    
    private static void FetchTests (Lecturer u) {
        Scanner scanner = new Scanner(System.in);
        String[] inp;
        String course = u.getCourse();
        String dir = Database.TABLE_EXAMS;
        
        while (true) {
            File[] files = new File(dir).listFiles();
            
            System.out.println("ID" + "\t" + "Course" + "\t" + "No. of Questions");
            for (File file : files) {
                String fileName = file.toPath().getFileName().toString();
                if (fileName.equals("ID")) continue;
                String[] lines = Database.getlines(dir+fileName);
                System.out.println(fileName + "\t" + lines[0] + "\t" + (lines.length - 1));
            }
            
            System.out.println("--------\nDELTEST {test id}\nSHOWMARKS {test id}\nEXIT");
            
            inp = scanner.nextLine().split(" ");
            String cmd = inp[0];
            
            if ("deltest".equals(cmd.toLowerCase())) {
                if (inp.length != 2) {
                    System.out.println("Inappropriate number of arguemnts");
                    continue;
                }
                u.DeleteTest(inp[1]);
            } else if ("showmarks".equals(cmd.toLowerCase())) {
                if (inp.length != 2) {
                    System.out.println("Inappropriate number of arguemnts");
                    continue;
                }
                System.out.println("showing marks of students on test " + inp[1]);
            } else if ("exit".equals(cmd.toLowerCase())) {
                return;
            } else {
                System.out.println("Invalid command");
            }
        }
    }
    
    private static void AdminMenu() {
        Scanner scanner = new Scanner(System.in);
        Admin u = (Admin)UserManagement.currentUser;
        
        while (true) {
            char choice;
            System.out.println("Name: " + u.getName() + "\t\t\tAge: " + u.getAge() + "\n\n");
            System.out.println("Menu:\n\n0-Logout\n1-Change Info\n2-Manage Students\n3-Manage Lecturers");
            choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '0' -> { return; } //logs out by returning from function to main loop
                case '1' -> UserManagement.ChangeInfo();
                case '2' -> FetchUsers(UserType.STUDENT, u);
                case '3' -> FetchUsers(UserType.LECTURER, u);
                default  -> System.out.println("Invalid choice, please try again.");
            }
        }
    }
    
    private static void FetchUsers (UserType type, Admin u) {
        Scanner scanner = new Scanner(System.in);
        String[] inp;
        String dir = (type==UserType.STUDENT)? Database.TABLE_STUDS:Database.TABLE_LECTS;
        
        while (true) {
            File[] files = new File(dir).listFiles();
            
            System.out.println("ID" + "\t" + "Name" + "\t" + "Age" + "\t" + "Courses");
            for (File file : files) {
                String fileName = file.toPath().getFileName().toString();
                if (file.isDirectory() || fileName.equals("ID")) continue;
                String[] lines = Database.getlines(dir+fileName);
                String courses = "..";
                if (lines.length >= 4) courses = lines[3];
                System.out.println(fileName + "\t" + lines[0] + "\t" + lines[1] + "\t" + courses);
            }
            
            System.out.println("--------\nADDUSER {username} {age} {password}\nDELUSER {user id}\nASSIGN {course name} {lect/stud id}\nEXIT");
            
            inp = scanner.nextLine().split(" ");
            String cmd = inp[0];
            
            if ("adduser".equals(cmd.toLowerCase())) {
                if (inp.length != 4) {
                    System.out.println("Inappropriate number of arguemnts");
                    continue;
                }
                u.AddUser(new User(0,inp[1],Integer.parseInt(inp[2]),inp[3], type));
            } else if ("deluser".equals(cmd.toLowerCase())) {
                if (inp.length != 2) {
                    System.out.println("Inappropriate number of arguemnts");
                    continue;
                }
                u.DeleteUser(inp[1], type);
            } else if ("assign".equals(cmd.toLowerCase())) {
                if (inp.length != 3) {
                    System.out.println("Inappropriate number of arguemnts");
                    continue;
                }
                u.AssignCourse(inp[1], inp[2], type);
            } else if ("exit".equals(cmd.toLowerCase())) {
                return;
            } else {
                System.out.println("Invalid command");
            }
        }
    }
    
    public static List<String> getFileNames (String dir) {
        File[] files = new File(dir).listFiles();
        String[] arr = new String[files.length];
        for (int i = 0; i < files.length; ++i) {
            arr[i] = files[i].getName();
        }
        return Arrays.asList(arr);
    }
    
    private static void LoadIDs () {
        String stuID = Database.getlines(Database.TABLE_STUDS+"ID")[0];
        String lectID = Database.getlines(Database.TABLE_LECTS+"ID")[0];
        String admID = Database.getlines(Database.TABLE_ADMIN+"ID")[0];
        String tstID = Database.getlines(Database.TABLE_EXAMS+"ID")[0];
        
        Student.ID = Integer.parseInt(stuID);
        Lecturer.ID = Integer.parseInt(lectID);
        Admin.ID = Integer.parseInt(admID);
        Test.ID = Integer.parseInt(tstID);
    }
}
