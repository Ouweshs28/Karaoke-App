import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A helper class that creates a message box by passing parameter of a string
 */
public class MessageBox {

    static Stage messageWindow;
    /**
     * @param message String to be used to display appropriate message
     *  A method design to display appropriate message boxes
     *
     */
    public static void box(String message){

        messageWindow = new Stage();

        messageWindow.setTitle("Message");
        messageWindow.setResizable(false);
        messageWindow.initModality(Modality.APPLICATION_MODAL);
        messageWindow.setAlwaysOnTop(true);

        GridPane messagePane = new GridPane();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.setVgap(30);

        Label displayMessage = new Label();
        displayMessage.setText(message);
        displayMessage.setAlignment(Pos.CENTER);

        Button btnOK = new Button("OK!");
        btnOK.setMinWidth(175);
        btnOK.setFocusTraversable(false);
        btnOK.setOnAction(e -> messageWindow.close());

        messagePane.add(displayMessage, 0, 0);
        messagePane.add(btnOK, 0, 1);

        Scene scene = new Scene(messagePane, 600, 150);

        messageWindow.setScene(scene);
        messageWindow.showAndWait();

    }
}
