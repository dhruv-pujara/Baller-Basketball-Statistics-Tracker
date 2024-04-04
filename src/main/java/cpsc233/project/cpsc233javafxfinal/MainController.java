package cpsc233.project.cpsc233javafxfinal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("CPSC 233 Final Stuff!");
    }
}