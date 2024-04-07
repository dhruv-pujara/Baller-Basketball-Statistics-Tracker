package cpsc233.project.cpsc233javafxfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PleaseProvideControllerClassName implements Initializable {
        @FXML
        private Text ErrorText;

        @FXML
        private ComboBox<?> playerSelect;

        @FXML
        private TextField teamName;

        @FXML
        private ComboBox<?> teamSelect;

        @FXML
        private Font x1;

        @FXML
        private Color x2;

        @FXML
        private Font x3;

        @FXML
        private Color x4;

        @FXML
        void addGame(MouseEvent event) {

        }

        @FXML
        void addPlayer(MouseEvent event) {

        }

        @FXML
        void addTeam(MouseEvent event) {
                if(teamName.getText().equals("")){
                        ErrorText.setText("Error: No team name given");
                }else {
                        ArrayList<Player> players = new ArrayList<>();
                        Team team = new Team(players, teamName.getText());
                        ErrorText.setText( "Team " + team.getName() + " has been added!");
                }
        }

        @FXML
        void lastgameStats(MouseEvent event) {

        }

        @FXML
        void viewPortfolio(ActionEvent event) {

        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
}
