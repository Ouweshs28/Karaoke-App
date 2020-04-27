import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

/**
 * Helper Class for media player
 */

public class KaraokeMediaplayer {

    private Stage mediaPlayerWindow;
    private Slider volumeSlider;
    private Slider videoSlider;
    private boolean muteState = false;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Text nowPlayingText;
    private Text currentSong;
    private Text nextPlayingText;
    private Text nextSong;

    /**
     *
     * @param playlist takes playlist from main application page to play
     */

    public void StartMediaPlayer(PlayList playlist) {

        mediaPlayerWindow = new Stage();
        mediaPlayerWindow.setResizable(false);
        mediaPlayerWindow.setTitle("Media Player");
        mediaPlayerWindow.setMaximized(true);

        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        GridPane gridPane = new GridPane();

        if (playlist.size() == 0) {
            MessageBox.box("Playlist empty");
            return;
        }

        Song current = null;
        try {
            current = playlist.getFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }


        String currentSongTitle = null;
        try {
            currentSongTitle = playlist.getFirst().getTitle() + " - " + playlist.getFirst().getArtist();

        } catch (Exception e) {
            e.printStackTrace();
        }
        currentSong = new Text();
        currentSong.setText(currentSongTitle);

        nowPlayingText = new Text();
        nowPlayingText.setText("Now playing: ");


        nextPlayingText = new Text();
        nextPlayingText.setText("Next: ");

        nextSong = new Text();

        String nextSongTitle = null;
        try {
            if (playlist.size() == 1) {
                nextSongTitle = "No next song!";
            } else {
                nextSongTitle = playlist.getAt(1).getTitle() + " - " + playlist.getAt(1).getArtist();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        nextSong.setText(nextSongTitle);


        String filepath = current.getVideofile();


        String path = "video/";

        String fullpath = path + filepath;


        media = new Media(new File(fullpath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
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
            if(playlist.size()==0){
                MessageBox.box("Playlist empty add new songs");
            }

        });

        mediaPlayer.setOnEndOfMedia(() -> {
            NextSong(playlist,path);
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
            NextSong(playlist, path);
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
            volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        });
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (volumeSlider.isPressed()) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100);
                }
            }
        });


        Button btnBack = new Button("Close Player");
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

        HBox sliderBox= new HBox();
        sliderBox.setAlignment(Pos.CENTER);
        videoSlider= new Slider();
        videoSlider.setFocusTraversable(false);
        videoSlider.setMinWidth(screenHeight / 6 * 4);
        videoSlider.setMaxWidth(screenHeight / 6 * 4);
        videoSlider.setMin(0);
        videoSlider.setMax(100);
        sliderBox.getChildren().add(videoSlider);


        videoSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov)
            {
                    updatesValues();

                    if(videoSlider.isPressed()){
                        mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(videoSlider.getValue() / 100));
                    }
            }
        });




        //hbox 3
        HBox btnBox = new HBox(20);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.getChildren().addAll(playBtn, stopBtn, nextBtn, muteBtn, volumeSlider, btnBack);

        //final vbox (containing all 3hbox)
        VBox finalBox = new VBox(20);
        finalBox.setAlignment(Pos.CENTER);
        finalBox.getChildren().addAll(statusBox, mediaBox,sliderBox, btnBox);


        gridPane.add(finalBox, 0, 0);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane);

        mediaPlayerWindow.setScene(scene);
        mediaPlayerWindow.setOnCloseRequest(e -> {
            mediaPlayer.stop();
            mediaPlayerWindow.close();
            e.consume();


        });
        mediaPlayerWindow.show();

    }

    protected void updatesValues()
    {
        Platform.runLater(new Runnable() {
            public void run()
            {
                // Updating to the new time value
                // This will move the slider while running your video
                videoSlider.setValue(mediaPlayer.getCurrentTime().toMillis()/
                        mediaPlayer.getTotalDuration()
                                .toMillis()
                                * 100);
            }
        });
    }


    /**
     *
     * @param playList takes playlist and updates it accordingly
     * @param path takes path of folder
     */

    public void NextSong(PlayList playList, String path) {

        if ( playList.size() == 1) {
            try {
                playList.removeFirst();
            } catch (Exception e) {

            }
            MessageBox.box("No next song!");
            } else {
            try {
                playList.removeFirst();
                try {

                    String newSongFile = playList.getFirst().getVideofile();
                    currentSong.setText(playList.getFirst().getTitle() + " By " + playList.getFirst().getArtist());
                    if (playList.size() == 1) {
                        nextSong.setText("No next song available");
                    } else {
                        nextSong.setText(playList.getAt(1).getTitle() + " By " + playList.getAt(1).getArtist());
                    }
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                    media = new Media(new File(path + newSongFile).toURI().toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaView.setMediaPlayer(mediaPlayer);
                    mediaPlayer.play();
                    mediaPlayer.setOnEndOfMedia(() -> {
                        NextSong(playList,path);
                    });

                } catch (Exception e) {

                }
            } catch (Exception e) {
            }
        }

    }


}

