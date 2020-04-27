import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class for File management
 */

public class FileManagement {
    private File songfile;
    private Scanner scan = null;


    public void getFile(String filename) {
        songfile = new File(filename);
        try {
            scan = new Scanner(songfile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);

        }
    }

    /**
     *
     * @param filename takes the file path
     * @return HashST Song library to be used
     */

    public HashST<String, Song> readFile(String filename) {
        getFile(filename);
        HashST<String, Song> song = new HashST<String, Song>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            try{
                String[] details = line.split("\t");
                String title = details[0];
                String artist = details[1];
                int time = Integer.parseInt(details[2]);
                String videofile = details[3];
                Song newsong = new Song(title, artist, time, videofile);
                song.put(title.toLowerCase(), newsong);
            }catch(ArrayIndexOutOfBoundsException exception) {
                System.out.println("File not valid");
                System.exit(0);
            }
        }
        //}
        return song;
    }


}