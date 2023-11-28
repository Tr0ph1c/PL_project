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
    
    public void AddStudent(int student_id , String sName, int sAge, String password , String[] courses)
    {
        String[] line= {sName, ""+sAge, password};
        for(int i=0;i<courses.length;i++){
            line[3] += courses[i] + ";";
        }
        Database.writeRecord(Database.TABLE_STUDS,""+student_id,line);
    }
    
    public void AddLecturer(int lecturer_id, String LName, int LAge, String password, String[] courses)
    {
        System.out.println("Add Lecturer");
    }
    
    public boolean DeleteStudent(int ID)
    {
        return Database.removeRecord(Database.TABLE_STUDS,""+ID);
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

