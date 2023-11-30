package cemsystemjava;

/**
 *
 * @author ahmniab
 * Same thing in void methods
 * 
 */
public class Student extends User{
    private String[] Courses ;

    public Student(String name, int age, String password, String[] courses) {
        super(name, age, password, UserManagement.UserType.STUDENT);
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
    
}
