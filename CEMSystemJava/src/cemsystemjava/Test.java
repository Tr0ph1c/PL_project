package cemsystemjava;

import DirectoryBasedDB.Database;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ahmniab
 * 
 */

public class Test {
    public static int ID;
    
    public int test_ID;
    public Question[] questions;
    public int total;
    public String course;
    
    public boolean loadTest(String test_id) {
        try {
            String[] info = Database.getlines(Database.TABLE_EXAMS+test_id);
            this.test_ID = Integer.parseInt(test_id);
            this.total = info.length-1;
            this.course = info[0];
            this.questions = new Question[total];

            for(int i = 0 ; i < total; i++) {
                questions[i] = new Question(info[i+1]);
            }
        } catch (IOException e) {
            System.out.println("Unable to load test " + test_id + ". It doesnt exist.");
            return false;
        }
        
        return true;
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
    public int take_test()
    {
        int mark = 0;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println( "Please choose the correct answer from the following questions: ");
        for(int i = 0 ; i <  total; i++)
        {
            System.out.print( i + " : ");
            System.out.println(questions[i].question);
            System.out.println("(a) " + questions[i].answers[0] + "\t\t" + "(b) " + questions[i].answers[1]);
            System.out.println("(c) " + questions[i].answers[2] + "\t\t" + "(d) " + questions[i].answers[3]);
            System.out.println();
            System.out.print("Please choose (a,b,c,d): ");
            
            char ans = input.nextLine().toLowerCase().charAt(0);
            //mark += (q.answer(ans))? 1 : 0;
            if (questions[i].answer(ans)) {
                mark++;
            }
        }
        
        System.out.println("Your mark is " + mark + '/' + this.total);
        return mark;
    }
    
    public static int generateID () {
        Database.overwriteRecord(Database.TABLE_EXAMS, "ID", new String[] {""+(++ID)});
        return ID;
    }
}
