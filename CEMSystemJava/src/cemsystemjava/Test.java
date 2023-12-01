/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cemsystemjava;

import DirectoryBasedDB.Database;
import java.util.Scanner;

/**
 *
 * @author ahmniab
 */

class Question{
    String question ;
    String[] answers ; 
    int correctAnswer ;
    boolean isCorrect ;
    Question(){
        this.answers = new String[4];
    }
}

public class Test {
    int test_ID;
    Question[] questions ;
    int mark;
    boolean isDone;
    static String course;
    public static boolean CreateTest()
    {
        Scanner input = new Scanner(System.in);

        int rand_name = (int) (Math.random() * 10000) , QN /* Questions Num */;
        while (true){
            try {
                System.out.print("Please enter the number of questions: ");
                QN = input.nextInt();
                input.nextLine(); // This is for skip the new line
                break;
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Only numeric values allowed!");
            }
        }

        Question[] Lines = new Question[QN] ;

        for (int i = 0 ; i < QN ; i++)
        {
        //  Build the line :-)

            String Question = "";
            System.out.println("Please write question number [" + i + "]:");
            Question += input.nextLine() + ";" ;

            System.out.print("Answer (a) : ");
            Question += input.nextLine().charAt(0) + ";" ;

            System.out.print("Answer (b) : ");
            Question += input.nextLine().charAt(0) + ";" ;

            System.out.print("Answer (c) : ");
            Question += input.nextLine().charAt(0) + ";" ;

            System.out.print("Answer (d) : ");
            Question += input.nextLine().charAt(0) + ";" ;

            System.out.print("Enter the correct answer in one single char: ");
            char ans = ("" + input.nextLine().charAt(0)).toLowerCase().charAt(0);
            if (ans == 'a' || ans == 'b' || ans == 'c' || ans == 'd') Question += ans - 96 ;
            else
            {
                System.out.println("invalid input, defaulting to answer A");
                Question += '1';
            }

            // add this line to line array
            Lines[i] = Question ;
        }
        
        //change the ID of the test later
        return Database.writeRecord(Database.TABLE_EXAMS + course + "/", Integer.toString(rand_name), Lines);
    
    }
}
