import org.junit.*;
import static org.junit.Assert.*;

public class KaraokeAppTest {

@Test
public void readFileTest(){
    String filepath="sample_song_data";
    FileManagement songFile = new FileManagement();
    HashST<String, Song> song=songFile.readFile(filepath);
    assertNotNull("should not be null", song);
}

@Test
public void addSongTester(){
    String filepath="sample_song_data";
    FileManagement songFile = new FileManagement();
    HashST<String, Song> song=songFile.readFile(filepath);
    String title="Hello";
    String artist="Adelle";
    String duration="320";
    String filename="test.mp4";
    AddSong songAdder=new AddSong();
    Song toplaylist=songAdder.AddSongTest(title,artist,duration,filename,song);
    Song newSong=new Song(title,artist,Integer.parseInt(duration),filename);
    assertTrue("failure - should be true", newSong.equals(toplaylist));

}

@Test
public void addToPLaylistTest(){
    String filepath="sample_song_data";
    FileManagement songFile = new FileManagement();
    HashST<String, Song> song=songFile.readFile(filepath);
    String songtitle="Multiply";
    Song getSong=song.get(songtitle);
    PlayList playList= new PlayList();
    playList.addLast(getSong);
    Song firstSong= new Song();
    try {
        firstSong =playList.getFirst();
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Playlist empty");
    }
    assertSame("should be same",firstSong, getSong);
}

@Test
public void removeFromPlaylist(){
    String filepath="sample_song_data";
    FileManagement songFile = new FileManagement();
    HashST<String, Song> song=songFile.readFile(filepath);
    String songtitle="Multiply";
    Song getSong=song.get(songtitle);
    PlayList playList= new PlayList();
    playList.addLast(getSong);
    Song firstSong= new Song();
    try {
        firstSong =playList.getFirst();
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Playlist empty");
    }
    try {
        playList.removeFirst();
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error deleting, no first element");
    }
    assertEquals("failure - strings are not equal", 0, playList.size());

}

}
