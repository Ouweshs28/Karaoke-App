import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A helper class for quit function
 */
public class Quit {

    static Stage exitWindow;

    /**
     * @throws Exception in case file exception fails
     */

    public static void Exit() throws Exception {

        exitWindow = new Stage();
        exitWindow.setTitle("Exit");
        exitWindow.setResizable(false);
        exitWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane exitPane = new GridPane();
        exitPane.setAlignment(Pos.CENTER);
        exitPane.setVgap(30);

        Label exitMessage = new Label();
        exitMessage.setText("Are you sure you want to exit ?");
        exitMessage.setAlignment(Pos.CENTER);

        Button btnYes = new Button("Yes");
        btnYes.setMinWidth(175);
        btnYes.setFocusTraversable(false);
        btnYes.setOnAction(e -> System.exit(0));

        Button btnNo = new Button("No");
        btnNo.setMinWidth(175);
        btnNo.setFocusTraversable(false);
        btnNo.setOnAction(e -> exitWindow.close());

        exitPane.add(exitMessage, 0, 0);
        exitPane.add(btnYes, 0, 1);
        exitPane.add(btnNo, 1, 1);

        Scene scene = new Scene(exitPane, 600, 150);

        exitWindow.setScene(scene);
        exitWindow.showAndWait();

    }
}
