import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


/**
 * This is the main class extending it to application for GUI
 */
public class KaraokeApp extends Application {


    public String takeArguement(){
        final Parameters params = getParameters();
        final List<String> parameters = params.getRaw();
        String filepath="";
        String args[]= parameters.toArray(new String[0]);

        if(args.length==0){
            filepath="sample_song_data";
            return filepath;
        }else if(args.length==1){
            filepath=args[0];
            return filepath;
        }else{
            System.out.println("Too many arguments");
            System.exit(0);
        }
        return null;
    }

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

        final String filepath=takeArguement();

        FileManagement songFile = new FileManagement();
        HashST<String, Song> song=songFile.readFile(filepath);
        PlayList playlist=new PlayList();
        Quit End = new Quit();

        GridPane mainMenu = new GridPane();
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setVgap(30);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label programTitle = new Label();
        programTitle.setText("Karaoke Media Application");
        Text about = new Text("______________________________");
        vbox.getChildren().addAll(programTitle,about);


        Button btnAddSong = new Button("Add  a Song");
        btnAddSong.setAlignment(Pos.BASELINE_LEFT);
        btnAddSong.setMaxWidth(250);
        btnAddSong.setFocusTraversable(false);
        btnAddSong.setOnAction(e -> {

                AddSong addnew=new AddSong();
                Song addSong=addnew.InputSong();
                if(!addSong.getArtist().equalsIgnoreCase("")){
                    song.put(addSong.getTitle().toLowerCase(),addSong);
                }
                

        });

        Button btnMediaPlayer = new Button("Open Media Player");
        btnMediaPlayer.setAlignment(Pos.BASELINE_LEFT);
        btnMediaPlayer.setMaxWidth(250);
        btnMediaPlayer.setFocusTraversable(false);
        btnMediaPlayer.setOnAction(e -> {
            KaraokeMediaplayer player =new KaraokeMediaplayer();
            player.StartMediaPlayer(playlist);
        });

        Button btnAddtoPlaylist = new Button("Playlists/Songs");
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



        mainMenu.getChildren().add(vbox);
        mainMenu.add(btnAddSong, 0, 1);
        mainMenu.add(btnMediaPlayer, 0, 2);
        mainMenu.add(btnAddtoPlaylist, 0, 3);
        mainMenu.add(btnExit, 0, 4);
        mainMenu.setHgap(50);

        Scene scene = new Scene(mainMenu,600,500);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Karaoke App");
        primaryStage.setMaximized(false);
        primaryStage.setOnCloseRequest(e -> {
                e.consume();
                End.Exit();

        });
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
