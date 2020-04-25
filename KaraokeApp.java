import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * This is the main class extending it to application for GUI
 */
public class KaraokeApp extends Application {

    /**
     * @param args Launches the main method
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage The main GUI Application
     *
     */
    @Override
    public void start(Stage primaryStage) {
        FileManagement songFile = new FileManagement();
        HashST<String, Song> song=songFile.readFile();
        OrderedSequentialSearchST<String,Song> playlist=new OrderedSequentialSearchST<String,Song>();
        Quit End = new Quit();

        GridPane mainMenu = new GridPane();
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setVgap(30);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label programTitle = new Label();
        programTitle.setText("Karaoke Media Application");
        vbox.getChildren().add(programTitle);


        Button btnAddSong = new Button("Add  a Song");
        btnAddSong.setAlignment(Pos.BASELINE_LEFT);
        btnAddSong.setMaxWidth(250);
        btnAddSong.setFocusTraversable(false);
        btnAddSong.setOnAction(e -> {

                AddSong addnew=new AddSong();
                Song addSong=addnew.InputSong();
                if(addSong.getArtist().equalsIgnoreCase("")){
                }else{
                    song.put(addSong.getTitle(),addSong);
                }
                

        });

        Button btnMediaPlayer = new Button("Play playlist");
        btnMediaPlayer.setAlignment(Pos.BASELINE_LEFT);
        btnMediaPlayer.setMaxWidth(250);
        btnMediaPlayer.setFocusTraversable(false);
        btnMediaPlayer.setOnAction(e -> {

        });

        Button btnAddtoPlaylist = new Button("Add to playlist");
        btnAddtoPlaylist.setAlignment(Pos.BASELINE_LEFT);
        btnAddtoPlaylist.setMaxWidth(250);
        btnAddtoPlaylist.setFocusTraversable(false);
        btnAddtoPlaylist.setOnAction(e -> {

                ViewAllSongs allSongs=new ViewAllSongs();
                allSongs.Table(song,playlist);


        });

        Button btnExit = new Button("Exit");
        btnExit.setAlignment(Pos.BASELINE_LEFT);
        btnExit.setMaxWidth(250);
        btnExit.setFocusTraversable(false);
        btnExit.setOnAction(e -> {

                End.Exit();


        });

        Text about = new Text("Ouwesh Seeroo");

        mainMenu.getChildren().add(vbox);
        mainMenu.add(btnAddSong, 0, 1);
        mainMenu.add(btnMediaPlayer, 0, 2);
        mainMenu.add(btnAddtoPlaylist, 0, 3);
        mainMenu.add(btnExit, 0, 6);
        mainMenu.add(about, 0, 8);
        mainMenu.setHgap(50);

        Scene scene = new Scene(mainMenu);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Karaoke App");
        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(e -> {
                e.consume();
                End.Exit();

        });
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}
