import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * A helper class that displays the data on table
 */
public class ViewAllSongs {

    static Stage tableWindow;
    static TableView tableSong;

    /**
     * @throws Exception in case file not found
     *                   Allows user input for sorting
     */


    /**
     *
     * @param  songs
     *
     */
    public void Table(HashST<String, Song> songs) {
        tableWindow = new Stage();
        tableWindow.setResizable(false);
        tableWindow.setTitle("View All songs");

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double columnWidth = screenWidth / 4;

        try {
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
            for (String s : songs.keys()) {

                tableSong.getItems().add(songs.get(s));
            }

            tableSong.getColumns().addAll(Title,Artist,Duration,File);
        } catch (Exception e) {
        }

        Button btnBack = new Button("Back");
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(200);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> tableWindow.close());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableSong, btnBack);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane bp = new BorderPane();
        bp.setCenter(vbox);

        Scene scene = new Scene(bp);
        tableWindow.setScene(scene);
        tableWindow.setMaximized(true);
        tableWindow.show();
    }
}
