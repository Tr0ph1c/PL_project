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
    private String Course;
    
    public Lecturer(String name, int age, String password) {
        super(name, age, password);
    }

    public boolean AddTest(){
        Database db = new Database();
        Scanner input = new Scanner(System.in);

        int rand_name = (int) (Math.random() * 10000) , QN /* Questions Num */;
        while (true){
            try {
                System.out.print("Please enter te number of questions : ");
                QN = input.nextInt();
                input.nextLine(); // This is for skip the new line
                break;
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Only numeric values allowed !");
            }
        }

        String[] Lines = new String[QN] ;

        for (int i = 0 ; i < QN ; i++)
        {
        //  Build the line :-)

            String Question = "";
            System.out.println("Please write 'end' to close add test");
            System.out.println("Please write your question :");
            Question += input.nextLine() + ";" ;

            System.out.print("Answer (a) : ");
            Question += input.nextLine().charAt(0) + ";" ;

            System.out.print("Answer (b) : ");
            Question += input.nextLine().charAt(0) + ";" ;

            System.out.print("Answer (c) : ");
            Question += input.nextLine().charAt(0) + ";" ;

            System.out.print("Answer (d) : ");
            Question += input.nextLine().charAt(0) + ";" ;

            System.out.print("Enter the correct answer in one single char : ");
            Question += input.nextLine().charAt(0);

            // add this line to line array
            Lines[i] = Question ;

        }
        return db.writeRecord(db.TABLE_EXAMS,"" + rand_name , Lines) ;


    }
    
    public boolean DeleteTest(int test_id)
    {
        Database db = new Database();
        return db.delRecord(db.TABLE_EXAMS , ""+test_id);
    }
    
    public void GenerateReport(int student_id){
        System.out.println("Generate Report");
    }
}
