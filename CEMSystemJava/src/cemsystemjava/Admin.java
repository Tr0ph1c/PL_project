package cemsystemjava;

import DirectoryBasedDB.Database;
import cemsystemjava.UserManagement.UserType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 *
 * @author ahmniab
 * 
 */
public class Admin extends User {
    public static int ID;
    
    public Admin(int _id, String name, int age, String password) {
        super(_id, name, age, password, UserType.ADMINISTRATOR);
    }
    
    public void AddUser (User user) {
        if (user.getType() == UserType.STUDENT) {
            AddStudent(new Student(user.getID(), user.getName(), user.getAge(), user.getPassword(), new String[] {""}));
        } else {
            AddLecturer(new Lecturer(user.getID(), user.getName(), user.getAge(), user.getPassword(), ""));
        }
    }
    
    public void DeleteUser (String id, UserType type) {
        switch(type) {
            case STUDENT:
                DeleteStudent(id);
                break;
            case LECTURER:
                DeleteLecturer(id);
                break;
        }
    }
    
    private void AddStudent(Student student)
    {
        int student_id = student.generateID();
        String sName = student.getName();
        int sAge = student.getAge();
        String password = student.getPassword();
        
        String[] lines = {sName, ""+sAge, password, "\n"};
        
        boolean added = Database.writeRecord(Database.TABLE_STUDS,""+student_id, lines);
        
        if (added) {
            File file = new File(Database.TABLE_STUDS+student_id+"tests/");
            file.mkdirs();
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student with ID " + student_id + " already exists.");
        }
    }
    
    private void AddLecturer(Lecturer lecturer)
    {
        int lecturer_id = lecturer.generateID();
        String LName = lecturer.getName();
        int LAge = lecturer.getAge();
        String password = lecturer.getPassword();
        
        String lecturerPath = Database.TABLE_LECTS;

        String[] lecturerInfo = new String[4];
        lecturerInfo[0] = LName;
        lecturerInfo[1] = ""+LAge;
        lecturerInfo[2] = password;
        lecturerInfo[3] = "";

        boolean added = Database.writeRecord(lecturerPath, ""+lecturer_id, lecturerInfo);
        if (added) {
            System.out.println("Lecturer added successfully.");
        } else {
            System.out.println("Lecturer with ID " + lecturer_id + " already exists.");
        }
    }
    
    private void DeleteStudent(String ID)
    {    
        if (Database.removeRecord(Database.TABLE_STUDS, ID)) {
            System.out.println("Student with ID {" + ID + "} deleted successfully.");
        } else {
            System.out.println("Student with ID {" + ID + "} does not exist.");
            return;
        }
        
        //Delete the whole directory with everything inside it
        try (Stream<Path> pathStream = Files.walk(new File(Database.TABLE_STUDS,ID+"tests").toPath())) {
            pathStream.sorted(Comparator.reverseOrder())
              .map(Path::toFile)
              .forEach(File::delete);
        } catch (IOException ex) {
            System.out.println("dir of student's folder already deleted.");
        }
    }
    
    private void DeleteLecturer(String lecturer_id)
    {
        String lecturerPath = Database.TABLE_LECTS;

        boolean deleted = Database.removeRecord(lecturerPath, lecturer_id);
        if (deleted) {
            System.out.println("Lecturer with ID {" + lecturer_id + "} deleted successfully.");
        } else {
            System.out.println("Lecturer with ID {" + lecturer_id + "} does not exist.");
        }
    }
    
    public void AssignCourse (String user_id, String course, UserType type) {
        if (!CourseManager.Exists(course)) CourseManager.AddCourse(course);
        String[] lines ;
        switch (type) {
            case STUDENT:
                try {
                    lines = Database.getlines(Database.TABLE_STUDS + user_id);
                }
                catch (IOException e){
                    System.out.println("Student ID does not exist");
                    return;
                }
                
                String[] fourth = lines[3].split(";");

                for(String c : fourth)
                    if(c.equals(course)) return;

                lines[3] += ";" + course;
                Database.overwriteRecord(Database.TABLE_STUDS , user_id, lines);
                break;
            case LECTURER:
                try {
                    lines = Database.getlines(Database.TABLE_LECTS + user_id);
                }
                catch(IOException e)
                {
                    System.out.println("NON valid ID");
                    return;
                }
                lines[3] = course;
                Database.overwriteRecord(Database.TABLE_LECTS, user_id, lines);
                break;
        }
    }

    
    public static int generateID () {
        Database.overwriteRecord(Database.TABLE_ADMIN, "ID", new String[] {""+(++ID)});
        return ID;
    }
}