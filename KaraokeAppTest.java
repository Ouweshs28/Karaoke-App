import javafx.application.Application;
import org.junit.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;

public class KaraokeAppTest {
    /**
     * Test if default file is read properly and there is data in the hash table
     */
    @Test
    public void readFileTest() {
        String filepath = "sample_song_data";
        FileManagement songFile = new FileManagement();
        HashST<String, Song> song = songFile.readFile(filepath);
        assertNotNull("should not be null", song);
    }

    // Wrapper thread updates this if
    // the JavaFX application runs without a problem.
    // Declared volatile to ensure that writes are visible to every thread.
    private volatile boolean success = false;

    /**
     * Test that a JavaFX application launches.
     */
    @Test
    public void testMain() {
        Thread thread = new Thread() { // Wrapper thread.
            @Override
            public void run() {
                try {
                    Application.launch(KaraokeApp.class); // Run JavaFX application.
                    success = true;
                } catch (Throwable t) {
                    if (t.getCause() != null && t.getCause().getClass().equals(InterruptedException.class)) {
                        // We expect to get this exception since we interrupted
                        // the JavaFX application.
                        success = true;
                        return;
                    }
                    // This is not the exception we are looking for so log it.
                    Logger.getLogger(KaraokeAppTest.class.getName()).log(Level.SEVERE, null, t);
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(2000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch (InterruptedException ex) {

        }
        thread.interrupt();
        try {
            thread.join(1); // Wait 1 second for our wrapper thread to finish.
        } catch (InterruptedException ex) {

        }
        assertTrue(success);
    }

    /**
     * In case of invalid input the song will not be added to the song list
     */

    @Test
    public void addSongTesterFail() {
        String filepath = "sample_song_data";
        FileManagement songFile = new FileManagement();
        HashST<String, Song> song = songFile.readFile(filepath);
        String title = "Hello";
        String artist = "Adelle";
        String duration = "abc";
        String filename = "test";
        AddSong songAdder = new AddSong();
        Song newSong = songAdder.AddSongTest(title, artist, duration, filename, song);
        assertEquals(newSong, song.get(title.toLowerCase()));

    }

    /**
     * Adding a song to the playlist and checking
     */

    @Test
    public void addToPLaylistTest() {
        String filepath = "sample_song_data";
        FileManagement songFile = new FileManagement();
        HashST<String, Song> song = songFile.readFile(filepath);
        String songtitle = "Multiply";
        Song getSong = song.get(songtitle);
        PlayList playList = new PlayList();
        playList.addLast(getSong);
        Song firstSong = new Song();
        try {
            firstSong = playList.getFirst();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Playlist empty");
        }
        assertSame("should be same", firstSong, getSong);
    }

    /**
     * Adding a song then deleting it from the playlist
     * Result should return zero as there should be one element in the array
     */

    @Test
    public void removeFromPlaylist() {
        String filepath = "sample_song_data";
        FileManagement songFile = new FileManagement();
        HashST<String, Song> song = songFile.readFile(filepath);
        String songtitle = "Multiply";
        Song getSong = song.get(songtitle);
        PlayList playList = new PlayList();
        playList.addLast(getSong);
        Song firstSong = new Song();
        try {
            firstSong = playList.getFirst();
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
        assertEquals("playlist is not empty", 0, playList.size());

    }

    /**
     * Performs search and returns results
     */

    @Test
    public void searchSong(){
        String filepath = "sample_song_data";
        FileManagement songFile = new FileManagement();
        HashST<String, Song> song = songFile.readFile(filepath);
        ViewAllSongs searchTitle = new ViewAllSongs();
        String search="love";
        //Passing search criteria and returning results
        HashST<String,Song> result=searchTitle.titleSearch(search,song);
        assertNotNull(result);
        assertTrue(result.size()>0);
    }

    /**
     * Test to check if the function skips and does not add the same song again
     */

    @Test
    public void dublicateSongPlaylist(){
        String filepath = "sample_song_data";
        FileManagement songFile = new FileManagement();
        HashST<String, Song> song = songFile.readFile(filepath);
        //Empty playlist
        PlayList tempPlayList= new PlayList();
        String songtitle="Prana";
        ViewAllSongs checkPlaylist=new ViewAllSongs();
        //Adding song Prana
        Song addedSong=checkPlaylist.populatePlaylistTest(songtitle,song,tempPlayList);
        assertNotNull(addedSong);
        //Passing the song Prana again but adding the song to playlist
        tempPlayList.addLast(song.get(songtitle.toLowerCase()));
        addedSong=checkPlaylist.populatePlaylistTest(songtitle,song,tempPlayList);
        assertNull(addedSong);

    }

}
