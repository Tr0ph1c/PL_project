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
    
    public boolean DeleteTest(int test_id)
    {
        return Database.delRecord(Database.TABLE_EXAMS , Integer.toString(test_id));
    }
    
    public void GenerateReport(int student_id){
        System.out.println("Generate Report");
    }
    
    public static int generateID () {
        Database.overwriteRecord(Database.TABLE_LECTS, "ID", new String[] {""+(++ID)});
        return ID;
    }
}
