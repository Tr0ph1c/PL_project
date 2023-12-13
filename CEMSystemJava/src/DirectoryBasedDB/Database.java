package DirectoryBasedDB;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Database {
    private static final String DB = "../database/";
    public static final String TABLE_ADMIN = DB + "administrators/";
    public static final String TABLE_COURSES = DB + "courses/";
    public static final String TABLE_EXAMS = DB + "exams/";
    public static final String TABLE_LECTS = DB + "lecturers/";
    public static final String TABLE_STUDS = DB + "students/";
    
    public static boolean recordExists (String path, String RecordID) {
        return new File(path + RecordID).exists();
    }
    
    /*
    *@author ahmniab
    * Function: getLines
    * Description: Retrieves lines from a file specified by the given path.
    * pre :
    *   -The file is exist 
    * Parameters:
    *   - path: A string representing the file path.
    * Returns:
    *   An array containing lines read from the specified file.
    */
    public static String[] getlines (String path) throws IOException {
        String[] linesArray  = new String[0];
        
        List<String> lines = Files.readAllLines(Paths.get(path));
        linesArray = lines.toArray(new String[0]);
        
        return linesArray;
    }
    
    public static void overwriteRecord (String path, String RecordID, String[] lines) {
        File file = new File(path + RecordID);
        
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
    
    public static boolean writeRecord (String path, String RecordID, String[] lines) {
          if(!recordExists(path,RecordID)) {overwriteRecord(path,RecordID,lines);return true;}
          else {return false;}
    }

    public static boolean removeRecord (String path, String RecordID) {
        return new File(path + RecordID).delete();
    }
}
