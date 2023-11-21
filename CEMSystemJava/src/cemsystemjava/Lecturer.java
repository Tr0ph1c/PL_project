package cemsystemjava;

/**
 *
 * @author ahmniab
 * void methods has left to implementer
 * 
 */
public class Lecturer extends User{
    private String[] Courses ;
    public Lecturer(String name, int age, String password) {
        super(name, age, password);
    }
    
    void AddTest(){
        System.out.println("Add Test");
    }
    void DeleteTest(int test_id)
    {
        System.out.println("Delete test");
    }
    void GenerateReport(int student_id){
        System.out.println("Generate Report");
    }
}
