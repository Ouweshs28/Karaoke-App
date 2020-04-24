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
            try {
                AddSong addnew=new AddSong();
                Song addSong=addnew.InputSong();
                if(addSong.equals(new Song())){
                    System.out.println("default object");
                }else{
                    song.put(addSong.getTitle(),addSong);
                }
                
            } catch (Exception ex) {
            }
        });

        Button btnAddPlaylist = new Button("Add a song to playlist");
        btnAddPlaylist.setAlignment(Pos.BASELINE_LEFT);
        btnAddPlaylist.setMaxWidth(250);
        btnAddPlaylist.setFocusTraversable(false);
        btnAddPlaylist.setOnAction(e -> {
            try {
                
            } catch (Exception ex) {
            }
        });

        Button btnDisplayAll = new Button("Show all music");
        btnDisplayAll.setAlignment(Pos.BASELINE_LEFT);
        btnDisplayAll.setMaxWidth(250);
        btnDisplayAll.setFocusTraversable(false);
        btnDisplayAll.setOnAction(e -> {
            try {
                ViewAllSongs allSongs=new ViewAllSongs();
                allSongs.Table(song);
            } catch (Exception ex) {
            }

        });

        Button btnExit = new Button("Exit");
        btnExit.setAlignment(Pos.BASELINE_LEFT);
        btnExit.setMaxWidth(250);
        btnExit.setFocusTraversable(false);
        btnExit.setOnAction(e -> {
            try {
                End.Exit();

            } catch (Exception ex) {
            }
        });

        Text about = new Text("Ouwesh Seeroo");

        mainMenu.getChildren().add(vbox);
        mainMenu.add(btnAddSong, 0, 1);
        mainMenu.add(btnAddPlaylist, 0, 2);
        mainMenu.add(btnDisplayAll, 0, 3);
        mainMenu.add(btnExit, 0, 6);
        mainMenu.add(about, 0, 8);
        mainMenu.setHgap(50);

        Scene scene = new Scene(mainMenu);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Karaoke App");
        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(e -> {
            try {
                e.consume();
                End.Exit();

            } catch (Exception ex) {
            }
        });
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}
