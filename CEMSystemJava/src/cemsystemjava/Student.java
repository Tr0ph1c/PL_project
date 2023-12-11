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
    
    private String[] Courses;

    public Student(int _id, String name, int age, String password, String[] courses) {
        super(_id, name, age, password, UserManagement.UserType.STUDENT);
        Courses = courses;
    }
    
    public String[] getCourses () {
        return Courses;
    }
    
    public void TakeTest(String test_id) {
        if (CEMApp.getFileNames(Database.TABLE_STUDS+super.getID()+"tests").contains(test_id)) {
            System.out.println("Cant take a test that you already did.");
            return;
        }
        Test test = new Test();
        test.loadTest(test_id);
        int mark = test.take_test();
        String[] ReportLines = {test.course, mark + "/" + test.total};
        Database.overwriteRecord(Database.TABLE_STUDS+super.getID()+"tests/", test_id, ReportLines);
    }
    
    public static int generateID () {
        Database.overwriteRecord(Database.TABLE_STUDS, "ID", new String[] {""+(++ID)});
        return ID;
    }
}
