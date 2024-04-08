package cpsc233.project.cpsc233javafxfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainInterfaceController implements Initializable {

        //CODE TO HAVE MULTIPLE SCENES
        private Stage stage;
        private Scene scene;
        public void switchToAddPlayer(ActionEvent e) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddPlayer.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader.load());
                stage.setTitle("Baller: The Basketball Tracking Program v1.3");
                stage.setScene(scene);
                stage.show();
        }
        public void switchToAddGame(ActionEvent e) throws IOException {
                Team team = teamSelect.getValue();
                ArrayList<Player> players = team.getPlayers();
                for (Player playerstoaddgame : players) {
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddGame.fxml"));
                        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        scene = new Scene(fxmlLoader.load());
                        stage.setTitle("Baller: The Basketball Tracking Program v1.3");
                        stage.setScene(scene);
                        stage.show();
                }
        }
        public void switchToMainInterface(ActionEvent e) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(OldMain.class.getResource("Main.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader.load());
                stage.setTitle("Baller: The Basketball Tracking Program v1.3");
                stage.setScene(scene);
                stage.show();
        }
        public ArrayList<Team> teams = new ArrayList<>();
        @FXML
        private Text ErrorText;

        @FXML
        private ComboBox<Player> playerSelect;

        @FXML
        private TextField teamName;

        @FXML
        private ComboBox<Team> teamSelect;

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
        void addPlayer(ActionEvent event) {
                if(teamSelect.getValue() == null){
                        ErrorText.setText("Please select a team before adding a player");

                }else{
                        try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPlayer.fxml"));
                                Parent root = fxmlLoader.load();
//                                AddPlayerController addPlayerController = fxmlLoader.getController();
//                                addPlayerController.setParentController(this);
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.setTitle("Add Player");
                                stage.showAndWait();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }

        }
        public void setData4team(){
                teamSelect.getItems().clear();
                for(Team teamstoadd: teams){
                        teamSelect.getItems().add(teamstoadd);
                }
        }

        @FXML
        void addTeam(MouseEvent event) {
                ErrorText.setText("Welcome to Baller!");
                if (teamName.getText().isEmpty()) {
                        ErrorText.setText("Error: No team name given");
                        ErrorText.setFill(Color.RED);
                } else {
                        for (Team teamnames : teams) {
                                if (teamnames.getName().equals(teamName.getText())) {
                                        ErrorText.setText("Invalid team, team with this name already exists.");
                                        ErrorText.setFill(Color.RED);
                                }
                        }
                        if (!ErrorText.getText().equals("Invalid team, team with this name already exists.")) {
                                ArrayList<Player> players = new ArrayList<>();
                                Team team = new Team(players, teamName.getText());
                                ErrorText.setText("Team " + team.getName() + " has been added!");
                                ErrorText.setFill(Color.BLACK);
                                teams.add(team);
                                setData4team();
                        }
                }
        }

        @FXML
        void lastgameStats(MouseEvent event) {

        }

        @FXML
        void viewPortfolio(ActionEvent event) {

        }

        public void load (ActionEvent e) {
                //Prompts a text dialog and asks user for file name to load from
                TextInputDialog save = new TextInputDialog();
                save.setTitle("Load your work:");
                save.setHeaderText("Please enter a file name to open:");
                Optional<String> result = save.showAndWait();
                if (result.isPresent()) {
                        File fileName = new File(result.get() + ".txt");
                        //load Filename using CustomFileReader
                        //CustomFileReader.saveFile(fileName)
                }
        }

        public void save (ActionEvent e){
                //Prompts a text dialog and asks user for a file name to save too
                TextInputDialog save = new TextInputDialog();
                save.setTitle("Save your work:");
                save.setHeaderText("Please enter the name of the file you'd like to save too:");
                Optional<String> result = save.showAndWait();
                if (result.isPresent()) {
                        File fileName = new File(result.get() + ".txt");
                        //save fileName to CustiomFileReader
                        //CustomFileReader.loadDataFromFile(fileName)
                }
        }

        public void close (ActionEvent e){
                //Build alert to ask people if they ACTUALLY want to exit
                //By hitting OK, program ends naturally
                Alert leave = new Alert(Alert.AlertType.CONFIRMATION);
                leave.setTitle("Close Application Confirmation");
                leave.setHeaderText("""
                You are about to close Baller: The Basketball Tracking Program v1.3!
                Please make sure you have saved your file before exiting.
                """);
                leave.setContentText("Click 'OK' to exit, or 'Cancel' to go back.");
                Optional<ButtonType> result = leave.showAndWait();
                leave.show();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                        leave.close();
                        System.exit(0);
                } else {
                        leave.close();
                }
        }

        public void about (ActionEvent e){
                //Pops up Alert box under "Help -> About" menu
                //tells the user what they need to know!
                Alert about = new Alert(Alert.AlertType.INFORMATION);
                about.setTitle("About The Program");
                about.setHeaderText("""
                This program lets you save and load files for various basketball teams!
                You can set teams, players on each team, along with add games.
                You can even see hypothetical All-Star teams, along with top stats for players.
                """);
                about.setContentText("""
                    Author: Gavin Walters, JJ Nelson, Dhruv Pujara;
                    Emails: gavin.walters@ucalgary.ca, jj.nelson@ucalgary.ca, dhruv.pujara1@ucalgary.ca
                    UCID: 30142712, 30062571, 30210700
                    Tutorial: T13
                    TA: Shanna Hollingworth
                    Program Version: v1.3""");
                about.show();
        }
        @FXML
        void playerChange(ActionEvent event) {
                playerSelect.getItems().clear();
                for(Team teamstoadd: teams){
                        if(teamstoadd.equals(teamSelect.getValue())){
                                ArrayList<Player> players = teamstoadd.getPlayers();
                                for(Player playerstoadd: players){
                                        playerSelect.getItems().add(playerstoadd);
                                }
                        }
                }

        }

        private void openPlayerInputDialog() {
                try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPlayer.fxml"));
                        Parent root = fxmlLoader.load();
                        Stage stage = new Stage();
                        Scene scene = new Scene(fxmlLoader.load());
                        stage.setScene(scene);
                        stage.setTitle("Add Player");
                        stage.showAndWait();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
}
