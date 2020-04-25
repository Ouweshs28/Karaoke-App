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

    public HashST<String,Song> readFile(){
        getFile("sample_song_data");
        HashST<String, Song> song = new HashST<String, Song>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] details = line.split("\t");
            String title = details[0];
            String artist = details[1];
            double time = Double.parseDouble(details[2])/60;
            String videofile = details[3];
            Song newsong = new Song(title, artist, time, videofile);
            song.put(title.toLowerCase(), newsong);
        }
        return song;
    }

    
    
}