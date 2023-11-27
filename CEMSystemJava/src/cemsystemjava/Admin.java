package cemsystemjava;

import DirectoryBasedDB.Database;

/**
 *
 * @author ahmniab
 * this methods datatype maybe bool but i left it to the implementer
 * 
 */
public class Admin extends User {
    
    public Admin(String name, int age, String password) {
        super(name, age, password);
    }
    
    public void AddStudent(int student_id ,String password , String[] courses)
    {
        String[] line={password};
        for(int i=0;i<courses.length;i++){
            line[0]+=" "+courses[i];
            }
        Database.writeRecord(Database.TABLE_STUDS,""+student_id,line);
        
    }
    
    public void AddLecturer(int lecturer_id ,String password , String[] courses)
    {
        System.out.println("Add Lecturer");

    }
    
    public void DeleteStudent(int ID)
    {
        
        Database.removeRecord(Database.TABLE_STUDS,""+ID);

    }
    
    public void DeleteLecturer(int ID)
    {
        System.out.println("Delete Lecturer");

    }
    
    public void AssignCourseToStudent(String coruse, int stdudent_id)
    {
        System.out.println("Assign Course To Student");

    }
    
    public void AssignCourseToLecturer(String course, int lecturer_id)
    {
        System.out.println("Assign Course To Lecturer");

    }
}

