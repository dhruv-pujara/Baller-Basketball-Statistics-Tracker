package cpsc233.project.cpsc233javafxfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 * Controller class for adding game
 */
public class addGameController {

    /**
     * Method to initialize data for the controller
     *
     * @param team The team for which the game is being added.
     * @param player The player for whom the game statistics are being added.
     */
    public void initData(Team team, Player player) {
        this.team = team;
        this.player = player;
        teamText.setText("Adding game for Team " + team.getName());
        playerText.setText("Player: " + player.getName());
    }

    /**
     * Method to switch back to the main interface
     * @param event The ActionEvent triggered by the "Back to Main" button.
     */
    public void switchToMainInterface(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Variables and constants
    private Team team;
    private Player player;

    //FXML ID inserts
    @FXML
    private Text teamText;
    @FXML
    private Text playerText;
    @FXML
    private Label addGameErrorText;
    @FXML
    private TextField points;
    @FXML
    private TextField assists;
    @FXML
    private TextField steals;
    @FXML
    private TextField blocks;
    @FXML
    private TextField rebounds;

    /**
     * Method to save game statistics provided by user
     * @param event The ActionEvent triggered by the "Add Game" button.
     */
    @FXML
    public void saveGameStats(ActionEvent event) {
            try {
                // Parsing text fields to double
                double pointsStored = Double.parseDouble(points.getText());
                double assistsStored = Double.parseDouble(assists.getText());
                double stealsStored = Double.parseDouble(steals.getText());
                double blocksStored = Double.parseDouble(blocks.getText());
                double reboundsStored = Double.parseDouble(rebounds.getText());

                // Checking if all stats are positive
                if (pointsStored < 0 || assistsStored < 0 || stealsStored < 0 || blocksStored < 0 || reboundsStored < 0) {
                    addGameErrorText.setText("All Stats must be positive.");
                    addGameErrorText.setTextFill(Color.RED);
                    return;
                }

                // Updating player statistics
                player.getPoints().setStats(player.getPoints().getStats(), pointsStored);
                player.getAssists().setStats(player.getAssists().getStats(), assistsStored);
                player.getSteals().setStats(player.getSteals().getStats(), stealsStored);
                player.getBlocks().setStats(player.getBlocks().getStats(), blocksStored);
                player.getRebounds().setStats(player.getRebounds().getStats(), reboundsStored);
                player.addGamecount(player.getGamecount());

                // Switching back to the main interface
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
                Parent root = fxmlLoader.load();
                MainInterfaceController controller = fxmlLoader.getController();
                controller.setTeam(team);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

            } catch (NumberFormatException e) {
                // Handling invalid input format
                addGameErrorText.setText("Invalid input format - all inputs must be numbers.");
                addGameErrorText.setTextFill(Color.RED);
            } catch (Exception e) {
                // Handling unaccounted errors
                unaccountedError(e);
            }
    }

    /** Helps user is they do not know what to do during AddGame
     *
     * @param e
     */
    public void addGameHelp (ActionEvent e){
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Add Game Help");
        about.setHeaderText("""
                How to add a game:
                """);
        about.setContentText("""
                  In this menu, you can add a game to a team. You will be prompted to add several stats:
                  Points, Assists, Blocks, Steals, and Rebounds
                  
                  You will automatically return to the main menu when you have added stats for each teammate.
                  """);
        about.show();
    }
    /**
     * Handles unaccounted errors.
     * @param e The Exception object representing the error.
     */
    public void unaccountedError (Exception e){
        //Here just in case something else happens
        //Should never be triggered, but you never know!
        Alert leave = new Alert(Alert.AlertType.ERROR);
        leave.setTitle("An Error Has Occurred:" + e.toString());
        leave.setHeaderText("""
                An unexpected error has occurred. The program will now close.
                """);
        leave.showAndWait();
        leave.close();
        System.exit(1);
    }


}
