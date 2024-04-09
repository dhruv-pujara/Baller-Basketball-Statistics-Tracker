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

import java.io.IOException;

public class addGameController {

    public void initData(Team team, Player player) {
        this.team = team;
        this.player = player;
        teamText.setText("Adding game for team " + team.getName());
        playerText.setText("Player: " + player.getName());
    }

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

    @FXML
    public void saveGameStats(ActionEvent event) {
            try {
                double pointsStored = Double.parseDouble(points.getText());
                double assistsStored = Double.parseDouble(assists.getText());
                double stealsStored = Double.parseDouble(steals.getText());
                double blocksStored = Double.parseDouble(blocks.getText());
                double reboundsStored = Double.parseDouble(rebounds.getText());

                if (pointsStored < 0 || assistsStored < 0 || stealsStored < 0 || blocksStored < 0 || reboundsStored < 0) {
                    addGameErrorText.setText("All Stats must be positive.");
                    addGameErrorText.setTextFill(Color.RED);
                    return;
                }

                player.getPoints().setStats(player.getPoints().getStats(), pointsStored);
                player.getAssists().setStats(player.getAssists().getStats(), assistsStored);
                player.getSteals().setStats(player.getSteals().getStats(), stealsStored);
                player.getBlocks().setStats(player.getBlocks().getStats(), blocksStored);
                player.getRebounds().setStats(player.getRebounds().getStats(), reboundsStored);
                player.addGamecount(player.getGamecount());

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
                MainInterfaceController controller = fxmlLoader.getController();
                controller.setTeam(team);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } catch (NumberFormatException e) {
                addGameErrorText.setText("Invalid input format - all inputs must be numbers.");
                addGameErrorText.setTextFill(Color.RED);
                return;
            } catch (Exception e) {
                unaccountedError(e);
            }
    }
    public void unaccountedError (Exception e){
        //Here just in case something else happens
        //Should never be triggered, but you never know!
        Alert leave = new Alert(Alert.AlertType.ERROR);
        leave.setTitle("An Error Has Occurred :" + e);
        leave.setHeaderText("""
                An unexpected error has occurred. The program will now close.
                """);
        leave.showAndWait();
        leave.close();
        System.exit(1);
    }

}
