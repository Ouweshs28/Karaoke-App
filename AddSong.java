


import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Helper Class to input Song
 */
public class AddSong {


    static Stage addWindow;




    public Song InputSong() {
        TextField inputSongTitle, inputSongArtist, inputSongDuration, inputSongFile;
        Song newSong= new Song();

        addWindow = new Stage();
        addWindow.setTitle("Add a new song");
        addWindow.setResizable(true);
        addWindow.initModality(Modality.APPLICATION_MODAL);

        Locale.setDefault(Locale.getDefault());


        GridPane addSong = new GridPane();
        addSong.setAlignment(Pos.CENTER);
        addSong.setVgap(30);

        Label addSongTitle = new Label();
        addSongTitle.setText("Add a new song to the library");
        addSongTitle.setAlignment(Pos.CENTER);
        addSongTitle.setPrefWidth(Double.MAX_VALUE);

        Text SongTitle = new Text("Enter title :");
        inputSongTitle = new TextField();
        inputSongTitle.setPromptText("enter Song title");
        inputSongTitle.setFocusTraversable(false);

        Text SongArtist = new Text("Enter Song Artist :");
        inputSongArtist = new TextField();
        inputSongArtist.setPromptText("enter Song artist");
        inputSongArtist.setFocusTraversable(false);

        Text SongDuration = new Text("Enter Song Duration:");
        inputSongDuration = new TextField();
        inputSongDuration.setPromptText("enter Song duration 1.20 (1 min 20 secs)");
        inputSongDuration.setFocusTraversable(false);

        Text SongFile = new Text("Enter Song File Name :");
        inputSongFile = new TextField();
        inputSongFile.setPromptText("enter video file name");
        inputSongFile.setFocusTraversable(false);


        Button btnAdd = new Button("Add Song");
        btnAdd.setMinWidth(185);
        btnAdd.setFocusTraversable(false);
        btnAdd.setOnAction(e -> {
            try {

               Song returnSong = AddSong(inputSongTitle.getText(),inputSongArtist.getText(),inputSongDuration.getText(),inputSongFile.getText());
                newSong.setTitle(returnSong.getTitle());
                newSong.setArtist(returnSong.getArtist());
                newSong.setTime(returnSong.getTime());
                newSong.setVideofile(returnSong.getVideofile());

            } catch (Exception ex) {
            }
        });

        Button btnCancel = new Button("Cancel");
        btnCancel.setMinWidth(185);
        btnCancel.setFocusTraversable(false);
        btnCancel.setOnAction(e -> addWindow.close());

        HBox Hbtns = new HBox(40);
        Hbtns.getChildren().addAll(btnAdd, btnCancel);
        Hbtns.setAlignment(Pos.CENTER);

        addSong.add(addSongTitle, 0, 0, 2, 1);
        addSong.add(SongTitle, 0, 2);
        addSong.add(inputSongTitle, 1, 2);
        addSong.add(SongArtist, 0, 3);
        addSong.add(inputSongArtist, 1, 3);
        addSong.add(SongDuration, 0, 4);
        addSong.add(inputSongDuration, 1, 4);
        addSong.add(SongFile, 0, 5);
        addSong.add(inputSongFile, 1, 5);
        addSong.add(Hbtns, 0, 6, 2, 1);
        addSong.setPadding(new Insets(10, 60, 10, 60));
        addSong.setHgap(50);

        Scene scene = new Scene(addSong);

        addWindow.setScene(scene);
        addWindow.setMaximized(true);
        addWindow.showAndWait();
        return newSong;

    }

    private Song AddSong(String SongTitle,String SongArtist,String SongDuration,String SongFile){
        /*
         *
         * durationcheck is a regex that verifies that a string has number and decimal
         * fileformat is a regex to check if file ends with .mov/.mkv/.avi/.mp4
         * */
        String durationcheck = "-?\\d+(\\.\\d+)?";
        String empty = "";
        String fileformat= "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.avi|.mp4|.mkv|.mov)$";


        if (SongTitle.equalsIgnoreCase(empty)) {
            String messageTitleEmpty = "Song Title cannot be empty !";
            MessageBox.box(messageTitleEmpty);
            return null;
        } else if (SongArtist.equalsIgnoreCase(empty)) {
            String messageArtistEmpty = "Artist field cannot be empty !";
            MessageBox.box(messageArtistEmpty);
            return null;
        } else if (SongDuration.equalsIgnoreCase(empty)) {
            String messageCodeDurationEmpty = "Duration field cannot be empty !";
            MessageBox.box(messageCodeDurationEmpty);
            return null;

        } else if (!SongDuration.matches(durationcheck) || SongDuration.equalsIgnoreCase("0")) {
            String messageErrorBill = "Error ! Duration be numbers. Try again! Correct format 1.20 (1 min 20 sec)";
            MessageBox.box(messageErrorBill);
            return null;

        }else if (SongFile.equalsIgnoreCase(empty)) {
            String messageEmptyFile = "Video File field cannot be empty !";
            MessageBox.box(messageEmptyFile);
            return null;
        } else if (!SongFile.matches(fileformat)) {
            String messageErrorBill = "Error ! Filename not right please use file.avi/mp4/mkv/mov";
            MessageBox.box(messageErrorBill);
            return null;
        }

        double b = Double.parseDouble(SongDuration);

       Song addsong=new Song(SongTitle,SongArtist,b,SongFile);
        MessageBox.box("Successfully added !");
        addWindow.close();
       return addsong;


    }

}
