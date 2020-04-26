import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A helper class that performs search
 */
public class Search {
    public HashST<String, Song> titleSearch(String crteria, HashST<String, Song> songs) {

        HashST<String, Song> result = new HashST<String, Song>();

        for (String s : songs.keys()) {
            if (songs.get(s).getTitle().toLowerCase().contains(crteria)) {
                result.put(songs.get(s).getTitle(), songs.get(s));
            }
        }
        return result;
    }


    public Song populatePlaylist(String crteria, HashST<String, Song> songs, PlayList playlist) {
        Song temp[] = playlist.convertToArray();
        boolean exist = false;
        if (playlist.size() > 0) {

            for (Song songName : temp) {
                if (songName.getTitle().toLowerCase().equals(crteria.toLowerCase())) {
                    exist = true;
                }
            }

            if (exist) {
                MessageBox.box("Song already in playlist");
            } else {
                if (songs.contains(crteria.toLowerCase())) {
                    Song newSong = songs.get(crteria.toLowerCase());
                    return newSong;

                } else {
                    MessageBox.box("Song not found enter another title");
                }
            }

        } else {
            if (songs.contains(crteria.toLowerCase())) {
                Song newSong = songs.get(crteria.toLowerCase());
                return newSong;

            }
        }

        return null;
    }


}
