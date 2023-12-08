package cemsystemjava;

import DirectoryBasedDB.Database;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class CourseManager {
    //arraylist is mutable, list is not, and a normal array doesnt have append
    public static List<String> courses = new ArrayList<>();
    
    public static void LoadCourses () {
        courses = new ArrayList<>(Arrays.asList(Database.getlines(Database.TABLE_COURSES + "courses")));
    }
    
    public static void AddCourse (String course) {
        if (Exists(course)) return;
        
        courses.add(course);
        Database.overwriteRecord(Database.TABLE_COURSES, "courses", courses.toArray(new String[0]));
    }
    
    public static boolean Exists (String course) {
        //if (courses == null) return false;
        return courses.contains(course);
    }
    
    public static void RemoveCourse (String course) {
        if (!Exists(course)) return;
        
        courses.remove(course);
        Database.overwriteRecord(Database.TABLE_COURSES, "courses", courses.toArray(new String[0]));
        //Delete the whole directory with everything inside it
        try (Stream<Path> pathStream = Files.walk(new File(Database.TABLE_EXAMS,course).toPath())) {
            pathStream.sorted(Comparator.reverseOrder())
              .map(Path::toFile)
              .forEach(File::delete);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
