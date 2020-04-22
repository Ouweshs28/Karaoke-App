import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManagement {
    File songfile;
    Scanner scan=null;

    public void getFile(String filename){
        songfile=new File(filename);
        try {
            scan = new Scanner(songfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    
}