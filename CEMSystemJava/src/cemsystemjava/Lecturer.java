package cemsystemjava;

import DirectoryBasedDB.Database;

import java.util.Scanner;

/**
 *
 * @author ahmniab
 * void methods has left to implementer
 * 
 */
public class Lecturer extends User {
    public static int ID;
    
    private String course;
    
    public Lecturer(int _id, String name, int age, String password, String course) {
        super(_id, name, age, password, UserManagement.UserType.LECTURER);
        this.course = course;
    }
    
    public String getCourse() {
        return course;
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

        String[] Lines = new String[QN + 1] ;
        Lines[0] = course;
        
        for (int i = 0 ; i < QN ; i++)
        {
        //  Build the line :-)

            Question question = new Question();
            System.out.println("Please write question number [" + (i + 1) + "]:");
            question.question = input.nextLine() ;

            for(int j = 0 ; j < 4 ; j++)
            {
                System.out.print("Answer ("+(char)('a' + j )+") : ");
                question.answers[j] = input.nextLine();
            }
            
            System.out.print("Enter the correct answer in one single char: ");
            char ans = input.nextLine().toLowerCase().charAt(0);
            if (ans == 'a' || ans == 'b' || ans == 'c' || ans == 'd') question.correctAnswer = ans - 96 ;
            else
            {
                System.out.println("invalid input, defaulting to answer A");
                question.correctAnswer = 1;
            }

            // add this line to line array (with offset of 1 for course name at line[0])
            Lines[i+1] = question.toString() ;
        }
        
        return Database.writeRecord(Database.TABLE_EXAMS, Integer.toString(Test.generateID()), Lines);
    }
    
    public void DeleteTest(String test_id)
    {
        if (!Database.getlines(Database.TABLE_EXAMS+test_id)[0].equals(course)) {
            System.out.println("Can't delete test outside of lecturer's scope!");
        } else if (Database.delRecord(Database.TABLE_EXAMS , test_id)) {
            System.out.println("Test " + test_id + " deleted successfully.");
        } else {
            System.out.println("Test " + test_id + " does not exist.");
        }
    }
    
    public static int generateID () {
        Database.overwriteRecord(Database.TABLE_LECTS, "ID", new String[] {""+(++ID)});
        return ID;
    }
}
