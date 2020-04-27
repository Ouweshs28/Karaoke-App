
public class TesterConsole {
    public static void main(String[] args) {
        FileManagement songfile = new FileManagement();
        songfile.getFile("sample_song_data");
        HashST<String, Song> song = new HashST<String, Song>();
        while (songfile.scan.hasNextLine()) {
            String line = songfile.scan.nextLine();
            String[] details = line.split("\t");
            String title = details[0];
            String artist = details[1];
            int time = Integer.parseInt(details[2]);
            String videofile = details[3];
            Song newsong = new Song(title, artist, time, videofile);
            song.put(title, newsong); 
        }
    
        }

}