package cemsystemjava;

import DirectoryBasedDB.Database;
import java.io.File;
import java.util.Scanner;

public class CEMApp {

    public static void main(String[] args) {
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
                UserManagement.UserType type;
                System.out.println("Login");
                System.out.println("1 - Admin");
                System.out.println("2 - Student");
                System.out.println("3 - Lecturer");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                switch (choice) {
                    case 1 -> type = UserManagement.UserType.ADMINISTRATOR;
                    case 2 -> type = UserManagement.UserType.STUDENT;
                    case 3 -> type = UserManagement.UserType.LECTURER;
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
                    // Perform actions for successful login
                    // ...            choice = scanner.nextInt();
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
