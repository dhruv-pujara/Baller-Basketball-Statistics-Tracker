package cpsc233.project.cpsc233javafxfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddPlayerController {

    private MainInterfaceController parentController;

    public void setParentController(MainInterfaceController parentController) {
        this.parentController = parentController;
    }
    @FXML
    private TextField numPlayers;
    private int numPlayersToAdd;
    private MainInterfaceController mainController;

    @FXML
    public void OKButtonClicked() {

        try {
            numPlayersToAdd = Integer.parseInt(numPlayers.getText());
        } catch (NumberFormatException ebut ) {
            showErrorAlert("Invalid Input", "Please enter a valid number for the number of players");
            return;
        }
        if (numPlayersToAdd > 0) {
            for (int i = 0; i < numPlayersToAdd; i++) {
                openPlayerInputDialog(numPlayersToAdd);
            }
        } else {
            showErrorAlert("Invalid number of players", "Please enter a number greater than 0.");
        }
    }

    private void openPlayerInputDialog(int numPlayersToAdd) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerInputDialog.fxml"));
            Parent root = loader.load();

            PlayerInputController controller = loader.getController();
            controller.setMainController(mainController);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Player");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));

            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

