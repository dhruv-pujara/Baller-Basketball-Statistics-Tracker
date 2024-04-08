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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainInterfaceController implements Initializable {
        public FileChooser fileChooser = new FileChooser();

        //CODE TO HAVE MULTIPLE SCENES
        @FXML
        private Stage stage;

        @FXML
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
                if (team != null) {
                        for (Player players : team.getPlayers()) {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddGame.fxml"));
                                Parent root = fxmlLoader.load();
                                addGameController controller = fxmlLoader.getController();

                                controller.initData(team, players);
                                controller.setMainInterfaceController(this);


                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setTitle("Baller: The Basketball Tracking Program v1.3");
                                stage.setScene(scene);
                                stage.show();

                        }
                } else {
                        ErrorText.setText("Please select a team before adding a game.");

                }
        }
        @FXML
        public void switchToMainInterface(ActionEvent e) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
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
        private ComboBox<StatsType> Stats;

        private String fileName;

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
        private Label descriptionText;


        @FXML
        void addGame(MouseEvent event) {

        }
        @FXML
        void statBest(MouseEvent event) {
                int value = 1;
                if (teams.isEmpty()) {
                        ErrorText.setText("There are no teams currently created.");
                        ErrorText.setFill(Color.RED);
                }else {
                        for (Team teamstoiterate : teams) {
                                if (!teamstoiterate.getPlayers().isEmpty()) {
                                        value = 0;
                                }
                        }
                        if (value == 0) {
                                descriptionText.setText(Evaluations.TopPlayerStat(teams, Stats.getValue()));
                        }else{
                                ErrorText.setText("There are no players currently made");
                                ErrorText.setFill(Color.RED);
                        }
                }
        }
        @FXML
        void allStar(MouseEvent event) {
                int value = 1;
                if (teams.isEmpty()){
                        ErrorText.setText("There are no teams currently created.");
                }else {
                        for (Team teamstoiterate : teams) {
                                if (!teamstoiterate.getPlayers().isEmpty()) {
                                        value = 0;
                                }
                        }
                        if (value == 0) {
                                descriptionText.setText("The current all star team for Offense and Defense " +
                                        "by position are:" + "\n" +
                                        "Centre: " +
                                        Evaluations.topOffTopDefForPosition(teams, Positions.Centre) + "\n" +
                                        "Small Forward: " +
                                        Evaluations.topOffTopDefForPosition(teams, Positions.SmallForward) + "\n" +
                                        "Power Forward: " +
                                        Evaluations.topOffTopDefForPosition(teams, Positions.PowerForward) + "\n" +
                                        "Shooting Guard: " +
                                        Evaluations.topOffTopDefForPosition(teams, Positions.ShootingGuard) + "\n" +
                                        "Point Guard: " +
                                        Evaluations.topOffTopDefForPosition(teams, Positions.PointGuard));
                        }else{
                                ErrorText.setText("There are no players currently made");
                                ErrorText.setFill(Color.RED);
                        }
                }
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
                Player player = playerSelect.getValue();
                descriptionText.setText(Evaluations.fullPrintedEvaluationAndLastGameStats(player.getPoints(), player.getAssists(), player.getSteals(), player.getBlocks(), player.getRebounds(), player.getGamecount(), player.getPriotgamecount()));
        }

        @FXML
        void viewPortfolio(ActionEvent event) {
                playerSelect.getItems().clear();
                for (Team teamstoadd : teams) {
                        if (teamstoadd.equals(teamSelect.getValue())) {
                                ArrayList<Player> players = teamstoadd.getPlayers();
                                for (Player playerstoadd : players) {
                                        if (playerstoadd.equals(playerSelect.getValue())) {
                                                descriptionText.setText(playerstoadd.toString());
                                        }
                                }
                        }
                }
        }

        public void load (ActionEvent e) {
                //Prompts a text dialog and asks user for file name to load from
                try {
                        File file = fileChooser.showOpenDialog(new Stage());
                        fileName = file.getName();
                        ArrayList<Team> newteams = CustomFileReader.loadDataFromFile(file);
                        teams.addAll(newteams);
                        setData4team();
                        ErrorText.setText("Sucessfully Loaded File!");
                        ErrorText.setFill(Color.BLACK);
                        }catch (FileNotFoundException f){
                                ErrorText.setText("File not found- please create a file and try again.");
                                ErrorText.setFill(Color.RED);
                        }catch (RuntimeException f){
                                ErrorText.setText("File invalid- please create a file and try again.");
                                ErrorText.setFill(Color.RED);
                        }
        }

        public void save (ActionEvent e) throws FileNotFoundException {
                if (!fileName.isEmpty()) {
                        fileChooser.setInitialFileName(fileName);
                }
                //Prompts a text dialog and asks user for a file name to save too
                File file = fileChooser.showSaveDialog(new Stage());
                if (file != null) {
                        try {
                                CustomFileReader.saveFile(file, teams);
                                ErrorText.setText("You have successfully saved your data to " + file.getName());
                        } catch (FileNotFoundException g) {
                                ErrorText.setText("Invalid File name input: try again");
                                ErrorText.setFill(Color.RED);
                        }
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
                fileChooser.setInitialDirectory(new File("C:\\users"));
                Stats.getItems().addAll(StatsType.POINTS, StatsType.ASSISTS, StatsType.BLOCKS, StatsType.REBOUNDS, StatsType.STEALS);
        }


}
