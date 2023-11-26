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
    private String course;
    
    public Lecturer(String name, int age, String password, String course) {
        super(name, age, password);
        this.course = course;
    }

    public boolean AddTest(){
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
            switch(input.nextLine().charAt(0)) {
                case 'a':
                    Question += '1';
                    break;
                case 'b':
                    Question += '2';
                    break;
                case 'c':
                    Question += '3';
                    break;
                case 'd':
                    Question += '4';
                    break;
                default:
                    System.out.println("invalid input, defaulting to answer A");
                    Question += '1';
            }

            // add this line to line array
            Lines[i] = Question ;
        }
        
        return Database.writeRecord(Database.TABLE_EXAMS, Integer.toString(rand_name), Lines);
    }
    
    public boolean DeleteTest(int test_id)
    {
        return Database.delRecord(Database.TABLE_EXAMS , Integer.toString(test_id));
    }
    
    public void GenerateReport(int student_id){
        System.out.println("Generate Report");
    }
}
