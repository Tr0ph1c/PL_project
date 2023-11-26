package DirectoryBasedDB;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    public static final String DB = "../database/";
    public static final String TABLE_ADMIN = DB + "administrators/";
    public static final String TABLE_COURSES = DB + "courses/";
    public static final String TABLE_EXAMS = DB + "exams/";
    public static final String TABLE_LECTS = DB + "lecturers/";
    public static final String TABLE_STUDS = DB + "students/";
    
    public static boolean recordExists (String path, String RecordID) {
        return new File(path + RecordID + ".txt").exists();
    }
    
    public static void overwriteRecord (String path, String RecordID, String[] lines) {
        File file = new File(path + RecordID + ".txt");
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            
            for (int i = 0; i < lines.length; i++) {
                writer.append(lines[i] + "\n");
            }
            
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean removeRecord (String path, String RecordID) {
        return new File(path + RecordID + ".txt").delete();
    }
}
