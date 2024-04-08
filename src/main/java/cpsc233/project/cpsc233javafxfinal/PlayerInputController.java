package cpsc233.project.cpsc233javafxfinal;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PlayerInputController {
    @FXML
    private TextField playerName;
    @FXML
    private TextField jerseyNumber;
    @FXML
    private ChoiceBox<String> playerPosition;

    private MainInterfaceController mainController;

    public void setMainController(MainInterfaceController mainController) {
        this.mainController = mainController;
    }
    @FXML
    public void initialize(){
        playerPosition.getItems().addAll(
                "Centre",
                "Small Forward",
                "Shooting Guard",
                "Power Forward",
                "Point Guard"
        );
    }


    @FXML
    public void addPlayer(){
        String name = playerName.getText();
        String jerseyNo = jerseyNumber.getText();
        String position = playerPosition.getValue();

        if(name.isEmpty() || jerseyNo.isEmpty() || position == null){
            showErrorAlert("Incomplete Information", "Please full in all the fields");
            return;
        }
        try{
            int jersey = Integer.parseInt(jerseyNo);
        } catch (NumberFormatException e){
            showErrorAlert("Invalid input", "Please enter a valid jersey number.");
            return;
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
