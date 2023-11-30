package cemsystemjava;

import DirectoryBasedDB.Database;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 *
 * @author ahmniab
 * this methods datatype maybe bool but i left it to the implementer
 * 
 */
public class Admin extends User {
    
    public Admin(String name, int age, String password) {
        super(name, age, password, UserManagement.UserType.ADMINISTRATOR);
    }
    
    public void AddStudent(Student student)
    {
        int student_id = student.getID();
        String sName = student.getName();
        int sAge = student.getAge();
        String password = student.getPassword();
        String[] courses = student.getCourses();
        
        String[] line= {sName, ""+sAge, password, ""};
        for(int i=0;i<courses.length;i++){
            line[3] += courses[i] + ";";
        }
        Database.writeRecord(Database.TABLE_STUDS,""+student_id,line);
        File file = new File(Database.TABLE_STUDS+""+student_id+"tests/");
        file.mkdirs();
        
        Database.writeRecord(Database.TABLE_LECTS, password, courses);
    }
    
    public void AddLecturer(Lecturer lecturer)
    {
        int lecturer_id = lecturer.getID();
        String LName = lecturer.getName();
        int LAge = lecturer.getAge();
        String password = lecturer.getPassword();
        String course = lecturer.getCourse();
        
        String lecturerPath = Database.TABLE_LECTS;

        String[] lecturerInfo = new String[4];
        lecturerInfo[0] = LName;
        lecturerInfo[1] = ""+LAge;
        lecturerInfo[2] = password;
        lecturerInfo[3] = course;

        boolean added = Database.writeRecord(lecturerPath, ""+lecturer_id, lecturerInfo);
        if (added) {
            System.out.println("Lecturer added successfully.");
        } else {
            System.out.println("Lecturer with ID " + lecturer_id + " already exists.");
        }
    }
    
    public void DeleteStudent(int ID)
    {
        try (Stream<Path> pathStream = Files.walk(new File(Database.TABLE_STUDS,""+ID+"tests").toPath())) {
            pathStream.sorted(Comparator.reverseOrder())
              .map(Path::toFile)
              .forEach(File::delete);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        if (Database.removeRecord(Database.TABLE_STUDS,""+ID)) {
            System.out.println("Student with ID {" + ID + "} deleted successfully.");
        } else {
            System.out.println("Student with ID {" + ID + "} does not exist.");
        }
    }
    
    public void DeleteLecturer(int lecturer_id)
    {
        String lecturerPath = Database.TABLE_LECTS;

        boolean deleted = Database.delRecord(lecturerPath, ""+lecturer_id);
        if (deleted) {
            System.out.println("Lecturer with ID {" + lecturer_id + "} deleted successfully.");
        } else {
            System.out.println("Lecturer with ID {" + lecturer_id + "} does not exist.");
        }
    }
    
    /*
    *@author ahmniab
    * Function: AssignCourseToStudent
    * pre :
    *   -The file is exist 
    *   -The 4th line is exist
    * Parameters:
    *   - coruse      : A string representing the course.
    *   - stdudent_id : An int representing the Target student ز
    * Returns:
    *   True if assigend Successfully .
    *   False if the course already exists.
    *
    */
    public static boolean AssignCourseToStudent(String course, int student_id)
    {
        String[] lines =  Database.getlines(Database.TABLE_STUDS + student_id);
        String[] fourth = lines[3].split(";");
        
        for(String c : fourth)
            if(c.equals(course)) return false;
        
        lines[3] += ";" + course ;
        Database.overwriteRecord(Database.TABLE_STUDS ,""+ student_id, lines);
        return true ;
        

        
    }
    
    public void AssignCourseToLecturer(String course, int lecturer_id)
    {
        System.out.println("Assign Course To Lecturer");
    }
}

