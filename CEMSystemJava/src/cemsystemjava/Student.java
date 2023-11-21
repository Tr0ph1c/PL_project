
package cemsystemjava;

/**
 *
 * @author ahmniab
 * Same thing in void methods
 * 
 */
public class Student extends User{
    private String[] Courses ;

    public Student(String name, int age, String password) {
        super(name, age, password);
    }
    
    void TakeTest(int test_id){
        System.out.println("Take Test");
    }
    void CheckReports()
    {
        System.out.println("Check Reports");
    }
    
}
