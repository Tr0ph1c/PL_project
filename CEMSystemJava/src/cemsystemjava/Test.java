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
    public int total;
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
            if (ans == 'a' || ans == 'b' || ans == 'c' || ans == 'd') question.correctAnswer = ans - 'a' ;
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
    * 
    *
    */
    public void take_test()
    {
        Scanner input = new Scanner(System.in);
        String[] Lines = 
                Database.getlines(Database.TABLE_EXAMS + '/' /*+ course + '/' */ + this.test_ID  );
        this.total = Lines.length;
        
        
        System.out.println( "Please choose the correct answer from the following questions :");
        for(int i = 0 ; i <  total; i++)
        {
            System.out.print( i + " : ");
            Question q = new Question(Lines[i]);
            System.out.println(q.question);
            System.out.println("(a) " + q.answers[0] + "\t\t" + "(b) " + q.answers[1]);
            System.out.println("(c) " + q.answers[2] + "\t\t" + "(d) " + q.answers[3]);
            System.out.println();
            System.out.print("Please choose (a,b,c,d) : ");
            
            int ans = input.nextLine().charAt(0) - 'a';
            if(ans == q.correctAnswer) this.mark++;
            
            
        }
        
        System.out.println("Your mark is " + this.mark + '/' + this.total);
        
        
    }
    
    private static int generateID () {
        Database.overwriteRecord(Database.TABLE_EXAMS, "ID", new String[] {""+(++ID)});
        return ID;
    }
}
