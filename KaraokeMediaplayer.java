import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class KaraokeMediaplayer {

    static Stage mediaPlayerWindow;
    static Slider volumeSlider;
    static boolean muteState = false;

    public void StartMediaPlayer(OrderedSequentialSearchST<String,Song> playList) {



        mediaPlayerWindow = new Stage();
        mediaPlayerWindow.setResizable(false);
        mediaPlayerWindow.setTitle("Media Player");
        mediaPlayerWindow.setMaximized(true);

        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        GridPane gridPane = new GridPane();

        Text nowPlayingText = new Text();
        nowPlayingText.setText("Now playing: ");

        Text currentSong = new Text();

        Text nextPlayingText = new Text();
        nextPlayingText.setText("Next: ");

        Text nextSong = new Text();

        String path = "video/";

        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());
        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(screenHeight / 5 * 4);

        Button playBtn = new Button("Play");
        playBtn.setPadding(new Insets(10, 10, 10, 10));
        playBtn.setMinWidth(200);
        playBtn.setFocusTraversable(false);
        playBtn.setOnAction(e -> {
            MediaPlayer.Status status = mediaPlayer.getStatus();

            if (status == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
                playBtn.setText("Play");
            } else {
                mediaPlayer.play();
                playBtn.setText("Pause");
            }

        });


        Button stopBtn = new Button("Stop");
        stopBtn.setPadding(new Insets(10, 10, 10, 10));
        stopBtn.setMinWidth(200);
        stopBtn.setFocusTraversable(false);
        stopBtn.setOnAction(e -> {
            mediaPlayer.stop();
        });

        Button nextBtn = new Button("Next");
        nextBtn.setPadding(new Insets(10, 10, 10, 10));
        nextBtn.setMinWidth(200);
        nextBtn.setFocusTraversable(false);
        nextBtn.setOnAction(e -> {

        });

        Button muteBtn = new Button("Mute");
        muteBtn.setPadding(new Insets(10, 10, 10, 10));
        muteBtn.setMinWidth(200);
        muteBtn.setFocusTraversable(false);
        muteBtn.setOnAction(e -> {
            if (muteState) {
                mediaPlayer.setMute(true);
                muteBtn.setText("Unmute");
                muteState = !muteState;
            } else {
                mediaPlayer.setMute(false);
                muteBtn.setText("Mute");
                muteState = !muteState;
            }
        });

        volumeSlider = new Slider();
        volumeSlider.setFocusTraversable(false);
        volumeSlider.setMinWidth(200);
        volumeSlider.setMaxWidth(200);
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setPadding(new Insets(0, 20, 0, 20));
        mediaPlayer.setOnReady(() -> {
            volumeSlider.setValue(mediaPlayer.getVolume()*100);
        });
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (volumeSlider.isPressed()) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100);
                }
            }
        });



        Button btnBack = new Button("Close Media");
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(200);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> {
                    mediaPlayer.stop();
                    mediaPlayerWindow.close();
                }
        );

        HBox currentBox = new HBox(20);
        currentBox.setAlignment(Pos.CENTER);
        currentBox.getChildren().addAll(nowPlayingText, currentSong);

        HBox nextBox = new HBox(20);
        nextBox.setAlignment(Pos.CENTER);
        nextBox.getChildren().addAll(nextPlayingText, nextSong);

        //hbox 1
        HBox statusBox = new HBox(200);
        statusBox.setAlignment(Pos.CENTER);
        statusBox.getChildren().addAll(currentBox, nextBox);

        //hbox 2
        HBox mediaBox = new HBox();
        mediaBox.setAlignment(Pos.CENTER);
        mediaBox.setMinHeight(screenHeight / 5 * 4);
        mediaBox.getChildren().add(mediaView);

        //hbox 3
        HBox btnBox = new HBox(20);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.getChildren().addAll(playBtn, stopBtn, nextBtn, muteBtn, volumeSlider, btnBack);

        //final vbox (containing all 3hbox)
        VBox finalBox = new VBox(20);
        finalBox.setAlignment(Pos.CENTER);
        finalBox.getChildren().addAll(statusBox, mediaBox, btnBox);


        gridPane.add(finalBox, 0, 0);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane);

        mediaPlayerWindow.setScene(scene);
        mediaPlayerWindow.show();

    }
}

