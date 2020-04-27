import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * A helper class that displays the data on table
 *
 */
public class ViewAllSongs {

    private Stage tableWindow;
    private TableView tableSong, playlistTable;

    /**
     *
     * @param songs
     * @param playlist
     * @return
     */
    public PlayList Table(HashST<String, Song> songs, PlayList playlist) {
        tableWindow = new Stage();
        tableWindow.setResizable(false);
        tableWindow.setTitle("Songs / Playlist");

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double columnWidth = (screenWidth - 20) / 7;

        TableColumn<Song, String> Title = new TableColumn<>("Song Title");
        Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        Title.setMinWidth(columnWidth+ 30);
        Title.setResizable(false);
        Title.setSortable(true);

        TableColumn<Song, String> Artist = new TableColumn<>("Artist Name");
        Artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        Artist.setMinWidth(columnWidth+  30);
        Artist.setResizable(true);
        Artist.setSortable(true);

        TableColumn<Song, Integer> Duration = new TableColumn<>("Song Duration");
        Duration.setCellValueFactory(new PropertyValueFactory<>("time"));
        Duration.setMinWidth(columnWidth + 30);
        Duration.setSortable(true);
        Duration.setResizable(true);

        tableSong = new TableView<>();
        tableSong.setFocusTraversable(false);
        tableSong.setMinWidth((3 * columnWidth) + 120);
        tableSong.setEditable(false);
        tableSong.prefHeightProperty().bind(tableWindow.heightProperty());
        tableSong.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (String s : songs.keys())
            tableSong.getItems().add(songs.get(s));

        tableSong.getColumns().addAll(Title, Artist, Duration);




        TableColumn<Song, String> PlaylistTtitle = new TableColumn<>("Song Title");
        PlaylistTtitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        PlaylistTtitle.setMinWidth(columnWidth+ 30);
        PlaylistTtitle.setResizable(false);
        PlaylistTtitle.setSortable(true);

        TableColumn<Song, String> PlaylistArtist = new TableColumn<>("Artist Name");
        PlaylistArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        PlaylistArtist.setMinWidth(columnWidth +30);
        PlaylistArtist.setResizable(true);
        PlaylistArtist.setSortable(true);

        playlistTable = new TableView<>();
        playlistTable.setFocusTraversable(false);
        playlistTable.setMinWidth((2 * columnWidth) + 80);
        playlistTable.setEditable(false);
        playlistTable.prefHeightProperty().bind(tableWindow.heightProperty());
        playlistTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Song templist[]=playlist.convertToArray();
        for(Song p:templist) {

            playlistTable.getItems().add(new Song(p.getTitle(), p.getArtist()));
        }

        playlistTable.getColumns().addAll(PlaylistTtitle, PlaylistArtist);


        TextField searchField = new TextField();
        searchField.setPromptText("Type something to search");
        searchField.setMinWidth(200);
        searchField.setFocusTraversable(false);


        Button searchbtn = new Button("Search");
        searchbtn.setPadding(new Insets(10, 10, 10, 10));
        searchbtn.setMinWidth(200);
        searchbtn.setFocusTraversable(false);
        searchbtn.setOnAction(e -> {
            search(searchField,songs);

        });

        searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    search(searchField,songs);
                }
            }
        });


        Button showAllBtn = new Button("Show all music");
        showAllBtn.setPadding(new Insets(10, 10, 10, 10));
        showAllBtn.setMinWidth(200);
        showAllBtn.setFocusTraversable(false);
        showAllBtn.setOnAction(e -> {
            tableSong.getItems().clear();

            for (String s : songs.keys())
                tableSong.getItems().add(songs.get(s));

        });

        Button addPlaylistbtn = new Button("Add to Playlist");
        addPlaylistbtn.setPadding(new Insets(10, 10, 10, 10));
        addPlaylistbtn.setMinWidth(200);
        addPlaylistbtn.setFocusTraversable(false);
        addPlaylistbtn.setOnAction(e -> {
            if(tableSong.getSelectionModel().getSelectedItem()==null){
                MessageBox.box("Please select a song from table");
            }else {
                Song addSong= (Song) tableSong.getSelectionModel().getSelectedItem();
                Song newSong = populatePlaylist(addSong.getTitle().toLowerCase(), songs, playlist);
                if(newSong!=null){
                playlist.addLast(newSong);
                playlistTable.getItems().clear();
                    Song tempplay[]=playlist.convertToArray();
                    for(Song p:tempplay) {
                        playlistTable.getItems().add(new Song(p.getTitle(), p.getArtist()));
                    }
            }
            }
        });

        Button btnBack = new Button("Back");
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(200);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e ->
                tableWindow.close()
        );


        VBox songBox = new VBox();
        songBox.getChildren().add(tableSong);
        songBox.setMinWidth(3 * columnWidth);
        songBox.setAlignment(Pos.CENTER);
        Text about = new Text("Please select a song from table to add");
        VBox buttonBox = new VBox(20);
        buttonBox.getChildren().addAll(about,searchField, searchbtn, showAllBtn, addPlaylistbtn, btnBack);
        buttonBox.setAlignment(Pos.CENTER);

        VBox playlistBox = new VBox();
        playlistBox.getChildren().add(playlistTable);
        playlistBox.setMinWidth(2 * columnWidth);
        playlistBox.setAlignment(Pos.CENTER);


        HBox finalBox = new HBox(20);
        finalBox.setAlignment(Pos.CENTER);
        finalBox.setPadding(new Insets(20, 0, 20, 0));
        finalBox.getChildren().addAll(songBox, buttonBox, playlistBox);

        BorderPane bp = new BorderPane();
        bp.setCenter(finalBox);

        Scene scene = new Scene(bp);
        tableWindow.setScene(scene);
        tableWindow.setMaximized(true);
        tableWindow.showAndWait();

        return playlist;
    }

    /**
     *
     * @param
     * @return
     */

    public boolean checkField(String s) {
        if (s.equals("")) {
            MessageBox.box("Please enter something");
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param searchField search field
     * @param songs Song HASHST Library
     *   Performs search operation
     */

    public void search(TextField searchField,HashST<String,Song>songs){
        if (checkField(searchField.getText())) {
            HashST<String, Song> temp = titleSearch(searchField.getText().toLowerCase(), songs);
            tableSong.getItems().clear();

            for (String s : temp.keys()) {
                tableSong.getItems().add(temp.get(s));
            }


        }
    }
    public HashST<String, Song> titleSearch(String crteria, HashST<String, Song> songs) {

        HashST<String, Song> result = new HashST<String, Song>();
        StopWatch search =new StopWatch();
        System.out.println("Search started keyword "+crteria);
        search.start();
        for (String s : songs.keys()) {
            if (songs.get(s).getTitle().toLowerCase().contains(crteria)) {
                result.put(songs.get(s).getTitle(), songs.get(s));
            }
        }
        System.out.println("Search completed "+search.stop());
        return result;
    }

    /**
     Function that checks if the title exist in the playlist
     If it does not exist it will return a song to be added in the playlist
     else it will return null
     @return Song or null if already exists
     **/


    public Song populatePlaylist(String crteria, HashST<String, Song> songs, PlayList playlist) {
        Song temp[] = playlist.convertToArray();
        boolean exist = false;
        if (playlist.size() > 0) {
            for (Song songName : temp) {
                if (songName.getTitle().toLowerCase().equals(crteria.toLowerCase())) {
                    exist = true;
                    break;
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
