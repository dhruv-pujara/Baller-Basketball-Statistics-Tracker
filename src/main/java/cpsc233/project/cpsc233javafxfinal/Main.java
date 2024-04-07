package cpsc233.project.cpsc233javafxfinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OldMain.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Baller");
        stage.setOnCloseRequest(e -> ExitProgram(e, stage));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void ExitProgram(javafx.event.Event event, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Baller is about to be closed");
        alert.setContentText("Would you like to save your file before exiting? If so click cancel and go save!");
        Optional<ButtonType> closeornot = alert.showAndWait();
        if (closeornot.isPresent() && (closeornot.get() == ButtonType.CANCEL || closeornot.get() == ButtonType.CLOSE)) {
            event.consume();
        }
    }
}