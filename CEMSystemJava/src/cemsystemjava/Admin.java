package cemsystemjava;

/**
 *
 * @author ahmniab
 * this methods datatype maybe bool but i left it to the implementer
 * 
 */
public class Admin extends User{
    
    public Admin(String name, int age, String password) {
        super(name, age, password);
    }
    void AddStudent(int student_id ,String password , String[] courses)
    {
        System.out.println("Add student");
        
    }
    void AddLecturer(int lecturer_id ,String password , String[] courses)
    {
        System.out.println("Add Lecturer");

    }
    void DeleteStudent(int ID)
    {
        System.out.println("Delete student");

    }
    
    void DeleteLecturer(int ID)
    {
        System.out.println("Delete Lecturer");

    }
    void AssignCourseToStudent(String coruse, int stdudent_id)
    {
        System.out.println("Assign Course To Student");

    }
    void AssignCourseToLecturer(String course, int lecturer_id)
    {
        System.out.println("Assign Course To Lecturer");

    }
}

