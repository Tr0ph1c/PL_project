package cemsystemjava;

import DirectoryBasedDB.Database;

/**
 *
 * @author ahmniab
 * Same thing in void methods
 * 
 */
public class Student extends User{
    public static int ID;
    
    private String[] Courses ;

    public Student(int _id, String name, int age, String password, String[] courses) {
        super(_id, name, age, password, UserManagement.UserType.STUDENT);
        Courses = courses;
    }
    
    public String[] getCourses () {
        return Courses;
    }
    
    public void TakeTest(int test_id){
        System.out.println("Take Test");
    }
    
    public void CheckReports()
    {
        System.out.println("Check Reports");
    }
    
    public static int generateID () {
        Database.overwriteRecord(Database.TABLE_STUDS, "ID", new String[] {""+(++ID)});
        return ID;
    }
}
