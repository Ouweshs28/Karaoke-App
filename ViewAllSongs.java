import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.*;

/**
 * A helper class that displays the data on table
 */
public class ViewAllSongs {

    static Stage tableWindow;
    static TableView tableSong;

    /**
     *
     * @param  songs
     *
     */
    public ArrayList<Song> Table(HashST<String, Song> songs,ArrayList<Song> playlist) {
        tableWindow = new Stage();
        tableWindow.setResizable(false);
        tableWindow.setTitle("View All songs");

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double columnWidth = (screenWidth-20) / 4;

            TableColumn<Song, String> Title = new TableColumn<>("Song Title");
            Title.setCellValueFactory(new PropertyValueFactory<>("title"));
            Title.setMinWidth(columnWidth);
            Title.setResizable(false);
            Title.setSortable(true);

            TableColumn<Song, String> Artist = new TableColumn<>("Artist Name");
            Artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
            Artist.setMinWidth(columnWidth);
            Artist.setResizable(true);
            Artist.setSortable(true);

            TableColumn<Song, Double> Duration = new TableColumn<>("Song Duration");
            Duration.setCellValueFactory(new PropertyValueFactory<>("time"));
            Duration.setMinWidth(columnWidth);
            Duration.setSortable(true);
            Duration.setResizable(true);

            TableColumn<Song, String> File = new TableColumn<>("File Name");
            File.setCellValueFactory(new PropertyValueFactory<>("videofile"));
            File.setMinWidth(columnWidth);
            File.setSortable(false);
            File.setResizable(false);

            tableSong = new TableView<>();
            tableSong.setFocusTraversable(false);
            tableSong.setEditable(false);
            tableSong.prefHeightProperty().bind(tableWindow.heightProperty());
            tableSong.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            for (String s : songs.keys())
                tableSong.getItems().add(songs.get(s));

            tableSong.getColumns().addAll(Title,Artist,Duration,File);


        TextField searchField = new TextField();
        searchField.setPromptText("Enter Criteria");
        searchField.setMinWidth(200);
        searchField.setFocusTraversable(false);

        Button searchbtn = new Button("Search");
        searchbtn.setPadding(new Insets(10, 10, 10, 10));
        searchbtn.setMinWidth(200);
        searchbtn.setFocusTraversable(false);
        ArrayList<Song> finalPlaylist = playlist;
        searchbtn.setOnAction(e -> {
            if(checkField(searchField.getText())) {
                Search searchsong = new Search();
                HashST<String,Song> temp=searchsong.titleSearch(searchField.getText().toLowerCase(), songs, finalPlaylist);
                tableSong.getItems().clear();
                for (String s : temp.keys()) {
                    tableSong.getItems().add(temp.get(s));
                }
                


            }
        });
        Button addPlaylistbtn = new Button("Add to Playlist");
        addPlaylistbtn.setPadding(new Insets(10, 10, 10, 10));
        addPlaylistbtn.setMinWidth(200);
        addPlaylistbtn.setFocusTraversable(false);
        addPlaylistbtn.setOnAction(e -> {
            if(checkField(searchField.getText())) {
                Search addPlaylist = new Search();
                ArrayList<Song> Temp = addPlaylist.populatePlaylist(searchField.getText().toLowerCase(), songs, finalPlaylist);
            }
            });

        Button btnBack = new Button("Back");
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(200);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e ->
            tableWindow.close()
        );

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.setMinHeight(80);
        hBox.getChildren().addAll(searchField, searchbtn,addPlaylistbtn, btnBack);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableSong, hBox);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane bp = new BorderPane();
        bp.setCenter(vbox);

        Scene scene = new Scene(bp);
        tableWindow.setScene(scene);
        tableWindow.setMaximized(true);
        tableWindow.showAndWait();

        return finalPlaylist;
    }
    public boolean checkField(String s) {
        if (s.equals("")) {
            MessageBox.box("Please enter something");
            return false;
        } else {
            return true;
        }
    }

}
