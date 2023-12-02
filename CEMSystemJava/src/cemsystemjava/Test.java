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
 * Test implementation without Question class is impossible !
 * 
 */

//class Question{
//    String question ;
//    String[] answers ; 
//    int correctAnswer ;
//    boolean isCorrect ;
//    Question(){
//        this.answers = new String[4];
//    }
//}

public class Test {
    public int test_ID;
    public String[] questions ;
    public int mark;
    boolean isDone;
    public String course;
    
    public boolean CreateTest()
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

        String[] Lines = new String[QN] ;

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
    /*
    *
    *@author ahmniab
    * Function: take_test
    * pre :
    *   -The test is exist 
    * Parameters:
    *   - testID : an int represent the id of the test .
    * Returns:
    *   The mark of the test.
    *
    */
    public int take_test()
    {
        System.out.println("Take test will complete after Question class");
        return 1;
    }
}
