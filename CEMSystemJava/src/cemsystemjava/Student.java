package cemsystemjava;

import DirectoryBasedDB.Database;
import Utils.Helper;
import java.util.Arrays;

/**
 *
 * @author ahmniab
 * Same thing in void methods
 * 
 */
public class Student extends User{
    public static int ID;
    
    private String[] Courses;

    public Student(int _id, String name, int age, String password, String[] courses) {
        super(_id, name, age, password, UserManagement.UserType.STUDENT);
        Courses = courses;
    }
    
    public String[] getCourses () {
        return Courses;
    }
    
    public void TakeTest(String test_id) {
        if (Helper.getFileNames(Database.TABLE_STUDS+super.getID()+"tests").contains(test_id)) {
            System.out.println("Can't take a test that you already did.");
            return;
        }
        Test test = new Test();
        if (!test.loadTest(test_id)) {return;}
        if (!Arrays.asList(Courses).contains(test.course)) {
            System.out.println("Can't take a test that isn't in your assigned courses.");
            return;
        }
        int mark = test.take_test();
        String[] ReportLines = {test.course, mark + "/" + test.total};
        Database.overwriteRecord(Database.TABLE_STUDS+super.getID()+"tests/", test_id, ReportLines);
    }
    
    public static int generateID () {
        Database.overwriteRecord(Database.TABLE_STUDS, "ID", new String[] {""+(++ID)});
        return ID;
    }
}
