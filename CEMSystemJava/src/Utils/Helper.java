package Utils;

import DirectoryBasedDB.Database;
import cemsystemjava.Admin;
import cemsystemjava.Lecturer;
import cemsystemjava.Student;
import cemsystemjava.Test;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class Helper {
    public static void LoadIDs () {
        try {
            String stuID = Database.getlines(Database.TABLE_STUDS+"ID")[0];
            String lectID = Database.getlines(Database.TABLE_LECTS+"ID")[0];
            String admID = Database.getlines(Database.TABLE_ADMIN+"ID")[0];
            String tstID = Database.getlines(Database.TABLE_EXAMS+"ID")[0];

            Student.ID = Integer.parseInt(stuID);
            Lecturer.ID = Integer.parseInt(lectID);
            Admin.ID = Integer.parseInt(admID);
            Test.ID = Integer.parseInt(tstID);
        } catch (IOException e) {
            System.out.println("ID files for auto increment dont exist.");
        }
    }
    
    //returns array of file names in a directory because such function isnt built-in java
    public static List<String> getFileNames (String dir) {
        File[] files = new File(dir).listFiles();
        String[] arr = new String[files.length];
        for (int i = 0; i < files.length; ++i) {
            arr[i] = files[i].getName();
        }
        return Arrays.asList(arr);
    }
}
