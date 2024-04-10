package cpsc233.project.cpsc233javafxfinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Baller: The Basketball Tracking Program v1.3");
        stage.setOnCloseRequest(e -> ExitProgram(e, stage));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void ExitProgram(javafx.event.Event event, Stage stage) {
        Alert leave = new Alert(Alert.AlertType.CONFIRMATION);
        leave.setTitle("Close Application Confirmation");
        leave.setHeaderText("""
                You are about to close Baller: The Basketball Tracking Program v1.3!
                Please make sure you have saved your file before exiting.
                """);
        leave.setContentText("Click 'OK' to exit, or 'Cancel' to go back.");
        Optional<ButtonType> closeornot = leave.showAndWait();
        if (closeornot.isPresent() && (closeornot.get() == ButtonType.CANCEL || closeornot.get() == ButtonType.CLOSE)) {
            event.consume();
        }
    }
}