package cemsystemjava;

import DirectoryBasedDB.Database;
import java.io.File;
import java.util.Scanner;

public class CEMApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManagement userManagement = new UserManagement();
        int choice;

        while (true) {
            System.out.println("Main Menu");
            System.out.println("0- Exit");
            System.out.println("1- Login");
            System.out.print("Enter your choice: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (option == 0) {
                System.out.println("Exiting the program...");
                break; // Terminate the program
            } else if (option == 1) {
                System.out.println("Login");
                System.out.print("Enter ID: ");

                String id = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                int loginResult = userManagement.login(id, password);

                if (loginResult == 0) {
                    System.out.println("Login successful");
                    // Perform actions for successful login
                    // ...            choice = scanner.nextInt();\
                    System.out.println("Login Menu:");
                    System.out.println("1 - Admin");
                    System.out.println("2 - Student");
                    System.out.println("3 - Lecturer");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            int loginChoice = scanner.nextInt();
                            break;
                        case 2:
                            break;
                        case 3:
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
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
}
class UserManagement {

    public int login(String id, String password) {
        // Perform the login process and return an appropriate result code
        // Replace this with your actual implementation logic

        if (id.equals("admin") && password.equals("admin123")) {
            return 0; // Successful login
        } else if (id.equals("student") && password.equals("student123")) {
            return 0; // Successful login
        } else if (id.equals("lecturer") && password.equals("lecturer123")) {
            return 0; // Successful login
        } else if (password.equals("admin123")) {
            return 2; // User ID doesn't exist
        } else {
            return 1; // Incorrect password
        }
    }
}
