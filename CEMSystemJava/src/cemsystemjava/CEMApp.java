package cemsystemjava;

import DirectoryBasedDB.Database;
import java.io.File;
import java.util.Scanner;

public class CEMApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("0 - Exit");
            System.out.println("1 - Login");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Login Menu:");
                    System.out.println("1 - Admin");
                    System.out.println("2 - Student");
                    System.out.println("3 - Lecturer");
                    System.out.print("Enter your choice: ");
                    int loginChoice = scanner.nextInt();                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        
       }
    }
}

