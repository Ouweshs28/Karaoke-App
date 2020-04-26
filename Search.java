import java.util.ArrayList;

/**
 * A helper class that performs search
 */
public class Search {
    public HashST <String, Song> titleSearch(String crteria, HashST<String, Song> songs) {

            HashST<String, Song> result = new HashST<String, Song>();

            for (String s : songs.keys()) {
                if (songs.get(s).getTitle().toLowerCase().contains(crteria)) {
                    result.put(songs.get(s).getTitle(), songs.get(s));
                }
            }
            return result;
        }


    public Song populatePlaylist(String crteria, HashST<String, Song> songs, OrderedSequentialSearchST<String,Song> playlist) {
        for(String k:playlist.keys())
            System.out.println(k);
        System.out.println(playlist.contains(crteria));

        if(playlist.contains(crteria.toLowerCase())){
                MessageBox.box("Song already in playlist");
            }
            else if (songs.contains(crteria.toLowerCase())) {
                    Song newSong = songs.get(crteria.toLowerCase());
                    return newSong;

            } else {
                MessageBox.box("Song not found enter another title");
            }

        return null;
    }


}
