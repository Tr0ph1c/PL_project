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

public class Test {
    public static int ID;
    
    public int test_ID;
    public String[] questions ;
    public int mark;
    public boolean isDone;
    public String course;
    public Test(){
        this.test_ID = /*generateID()*/ 1230;
        
    }
    
    public boolean CreateTest()
    {
        Scanner input = new Scanner(System.in);

        int QN /* Questions Num */;
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

            Question question = new Question();
            System.out.println("Please write question number [" + (i + 1) + "]:");
            question.question = input.nextLine() ;

            for(int j = 0 ; j < 4 ; j++)
            {
                System.out.print("Answer ("+(char)('a' + j )+") : ");
                question.answers[j] = input.nextLine() ;
                
            }
            
            System.out.print("Enter the correct answer in one single char: ");
            char ans = ("" + input.nextLine().charAt(0)).toLowerCase().charAt(0);
            if (ans == 'a' || ans == 'b' || ans == 'c' || ans == 'd') question.correctAnswer = ans - 96 ;
            else
            {
                System.out.println("invalid input, defaulting to answer A");
                question.correctAnswer = 1;
            }

            // add this line to line array
            Lines[i] = question.toString() ;
        }
        
        return Database.writeRecord(Database.TABLE_EXAMS + /*course +*/ "/", Integer.toString(this.test_ID), Lines);
    
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
    
    private static int generateID () {
        Database.overwriteRecord(Database.TABLE_EXAMS, "ID", new String[] {""+(++ID)});
        return ID;
    }
}
