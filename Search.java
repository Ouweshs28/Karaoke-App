import java.util.ArrayList;

/**
 * A helper class that performs search
 */
public class Search {
    public HashST <String, Song> titleSearch(String crteria, HashST<String, Song> songs, ArrayList<Song> playlist) {

            HashST<String, Song> result = new HashST<String, Song>();

            for (String s : songs.keys()) {
                if (songs.get(s).getTitle().toLowerCase().contains(crteria)) {
                    result.put(songs.get(s).getTitle(), songs.get(s));
                }
            }
            return result;
        }


    public ArrayList<Song> populatePlaylist(String crteria, HashST<String, Song> songs, ArrayList<Song> playlist) {

            if (songs.contains(crteria.toLowerCase())) {
                playlist.add(songs.get(crteria.toLowerCase()));
            } else {
                MessageBox.box("Song not found enter another title");
            }

        return playlist;
    }


}
